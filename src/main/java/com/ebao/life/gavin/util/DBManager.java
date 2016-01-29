package com.ebao.life.gavin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ebao.life.gavin.cache.Cache;

public class DBManager {
	private static Logger logger = Logger.getLogger(DBManager.class);
	private static PooledConnection conn;
	private static ConnectionPool devconnectionPool;
	private static ConnectionPool tstdevconnectionPool;
	private static final String propertyName = "db";
	private static final String url = Cache.getProperty(propertyName, "dburl");
	private static final String devuser = Cache.getProperty(propertyName, "devuser");
	private static final String devpassword = Cache.getProperty(propertyName, "devpassword");
	private static final String tstuser = Cache.getProperty(propertyName, "tstuser");
	private static final String tstpassword = Cache.getProperty(propertyName, "tstpassword");

	public void close() {
		try {
			devconnectionPool.closeConnectionPool();
			tstdevconnectionPool.closeConnectionPool();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps, PooledConnection con) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (con != null) {
			con.close();

		}
	}

	public static void connectPool(final EnvEnum env) {
		
		boolean firstflag = false;
		
		switch (env) {
		case tptstoper: // 主干测试环境
			if (tstdevconnectionPool == null) {
				tstdevconnectionPool = new ConnectionPool("oracle.jdbc.driver.OracleDriver", url, tstuser, tstpassword);
				firstflag = true;
			}
			break;
		case tpdev: // 主干开发环境
			if (devconnectionPool == null) {
				devconnectionPool = new ConnectionPool("oracle.jdbc.driver.OracleDriver", url, devuser, devpassword);
				firstflag = true;
			}
			break;
		}

		if(firstflag){ // 初次建立连接池时，打开keepalive线程
			// 开一个线程， 每5分钟循环连接池中所有的connection, 执行一条语句保持连接不超时
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						logger.debug("开始刷新连接池连接  " + env);
						ConnectionPool cp = DBManager.getConnectionPool(env);
						if (cp != null) {
							Vector<PooledConnection> connPool = cp.getConnections();
							for (PooledConnection conn : connPool) {
								logger.debug("hashcode:" + conn.hashCode() + ",busy:" + conn.isBusy() + ",holdtime:" + (new Date().getTime() - conn.getHoldTime()));
								if (conn.isBusy()) { // 被占用的connection,判断其时间是否持有时间是否超时，超时则直接关闭
									long curTime = new Date().getTime();
									if (curTime - conn.getHoldTime() > PooledConnection.timeout) { // 连接持有时间超过超时时间
										try { // 关闭当前连接
											Connection jcon = conn.getConnection();
											logger.debug("有超过30秒未关闭的数据库连接 hashcode:" + jcon.hashCode() + ",conn.isClosed():" + jcon.isClosed());
											if (null != jcon && !jcon.isClosed())
												jcon.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										conn.setBusy(false);// 重新置为非占用
									}
								} else { // 未被占用的conn, 进行连接刷新,防止超时
									conn.setBusy(true);
									Connection jcon = conn.getConnection();
									try {
										if (null != jcon && !jcon.isClosed()) {
											Statement stmt = jcon.createStatement();
											stmt.execute("select 1 from dual");
											stmt.close(); // ！重要 不关闭会导致 java.sql.SQLException: ORA-01000: 超出打开游标的最大数
										}
									} catch (SQLException e) {
										logger.debug("测试连接有效性失败: " + ToolUtil.getExceptionMsg(e));
										try {
											jcon.close();
										} catch (SQLException e1) {
											logger.debug("关闭连接失败: " + ToolUtil.getExceptionMsg(e));
										}
									}
									conn.setBusy(false);
								}
							}
						}
	
						try {
	
							Thread.sleep(5 * 60 * 1000); // 每5分钟刷新一次连接池中的连接
	
						} catch (InterruptedException e) {
							// donothing
						}
	
					}
				}
			}).start();
		}

	}

	private DBManager() { // 工厂， 不可实例化对象
	}

	/**
	 * @author zhenwei.liu
	 * @date 2015-11-26
	 * @param env
	 *            数据库环境 dev:主干开发 tst:主干测试
	 * @param preparedSql
	 *            待执行sql
	 * @param paraList
	 *            sql中对应的参数列表</br> 现支持类型 </br> java.lang.String</br>
	 *            java.math.BigDecimal</br> java.sql.Date</br>
	 *            java.sql.Timestamp</br> java.lang.Integer</br>
	 *            java.lang.Long</br>
	 * @param cb
	 *            回调函数, select操作使用QueryCallBack</br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重写disposeResultSet方法定制处理查询结果
	 * @return
	 * @throws Exception
	 */
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, QueryCallback cb) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql中?占位符的个数
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("参数列表个数与sql不符");
		}

		ResultSet rs = null;
		PreparedStatement ps = null;
		PooledConnection conn = null;
		Object result = null;
		try {
			conn = DBManager.getConnection(env);
			ps = conn.prepareStatement(preparedSql);
			setStatementPara(ps, paraList);
			rs = ps.executeQuery();
			result = cb.disposeResultSet(rs);
		} catch (SQLException e) {
			logger.error("Exception happened while handle sql \n" + preparedSql + "\n" + ToolUtil.getExceptionMsg(e));
			throw e;
		} finally {
			try {
				DBManager.close(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}

		return result;
	}

	/**
	 * @param env 			要连接的数据库环境
	 * @param preparedSql	预处理sql
	 * @param paraList		参数列表
	 * @param beanClass		返回结果类型
	 * @return
	 * @throws Exception
	 */
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, @SuppressWarnings("rawtypes") Class beanClass) throws Exception {
		return executeQuery(env, preparedSql,paraList,beanClass, null );
	}
	
	/**
	 * @param env 			要连接的数据库环境
	 * @param preparedSql	预处理sql
	 * @param paraList		参数列表
	 * @param beanClass		返回结果类型
	 * @param nameMappings 	ResultSet中与Bean中不能对应的参数名值对   tablefield:classField
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, Class beanClass, HashMap nameMappings ) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql中?占位符的个数
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("参数列表个数与sql不符");
		}

		ResultSet rs = null;
		PreparedStatement ps = null;
		PooledConnection conn = null;
		Object result = null;
		try {
			conn = DBManager.getConnection(env);
			ps = conn.prepareStatement(preparedSql);
			setStatementPara(ps, paraList);
			rs = ps.executeQuery();
			result = BeanUtil.getBeans(beanClass, rs, nameMappings);
		} catch (SQLException e) {
			logger.error("Exception happened while handle sql \n" + preparedSql + "\n" + ToolUtil.getExceptionMsg(e));
			throw e;
		} finally {
			try {
				DBManager.close(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}

		return result;
	}

	/**
	 * @author zhenwei.liu
	 * @date 2015-12-15
	 * @param env
	 *            数据库环境 dev:主干开发 tst:主干测试
	 * @param preparedSql
	 *            待执行sql
	 * @param paraList
	 *            sql中对应的参数列表</br> 现支持类型 </br> java.lang.String</br>
	 *            java.math.BigDecimal</br> java.sql.Date</br>
	 *            java.sql.Timestamp</br> java.util.Date</br>
	 *            java.lang.Long</br> java.lang.Integer</br>
	 * @return
	 * @throws Exception
	 */
	public static int executeUpdate(EnvEnum env, String preparedSql, List<Object> paraList) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql中?占位符的个数
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("参数列表个数与sql不符");
		}

		ResultSet rs = null;
		PreparedStatement ps = null;
		PooledConnection conn = null;
		int rowCnt = 0;
		try {
			conn = DBManager.getConnection(env);
			ps = conn.prepareStatement(preparedSql);
			setStatementPara(ps, paraList);
			rowCnt = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception happened while handle sql \n" + preparedSql + "\n" + ToolUtil.getExceptionMsg(e));
			throw e;
		} finally {
			try {
				DBManager.close(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}

		return rowCnt;
	}

	private static void setStatementPara(PreparedStatement ps, List<Object> paraList) throws Exception {
		if (paraList == null || paraList.size() == 0) { // 不需要设置参数
			return;
		} else {
			for (int i = 0; i < paraList.size(); i++) {
				Object para = paraList.get(i);
				int parameterIndex = i + 1;// sql的参数设置从1开始
				if (para == null) {
					ps.setString(parameterIndex, null); // 其他类型字段也能用此方法置为空值
					// throw new Exception("查询条件中有空值");
				}
				if (para instanceof java.lang.String) {
					ps.setString(parameterIndex, (String) para);
				} else if (para instanceof java.math.BigDecimal) {
					ps.setBigDecimal(parameterIndex, (java.math.BigDecimal) para);
				} else if (para instanceof java.sql.Date) {
					ps.setDate(parameterIndex, (java.sql.Date) para);
				} else if (para instanceof java.sql.Timestamp) {
					ps.setTimestamp(parameterIndex, (java.sql.Timestamp) para);
				} else if (para instanceof java.util.Date) {
					ps.setTimestamp(parameterIndex, new Timestamp(((java.sql.Timestamp) para).getTime()));
				} else if (para instanceof java.lang.Integer) {
					ps.setInt(parameterIndex, (Integer) para);
				} else if (para instanceof java.lang.Long) {
					ps.setLong(parameterIndex, (Long) para);
				} else {
					throw new Exception("不支持的参数类型,参数位置" + i);
				}
			}
		}

	}

	public static PooledConnection getConnection(EnvEnum env) {
		try {
			conn = getConnectionPool(env).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	static ConnectionPool getConnectionPool(EnvEnum env) {

		ConnectionPool cp = null;
		switch (env) {
		case tptstoper: // 主干测试环境
			if (tstdevconnectionPool == null) {
				connectPool(env);
			}
			cp = tstdevconnectionPool;
			break;
		case tpdev: // 主干开发环境
			if (devconnectionPool == null) {
				connectPool(env);
			}
			cp = devconnectionPool;
			break;
		}
		return cp;
	}

	private static int getQMarkNum(String prepareSql) {
		int cnt = 0, start = 0;
		while (start != prepareSql.length()) {
			int i = prepareSql.indexOf("?", start);
			if (i != -1) {
				cnt++;
				start = i + 1;
			} else
				break;
		}
		return cnt;
	}

	public static void main(String[] args) {
	}

}
