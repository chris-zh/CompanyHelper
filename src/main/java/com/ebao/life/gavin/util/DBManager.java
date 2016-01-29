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
		case tptstoper: // ���ɲ��Ի���
			if (tstdevconnectionPool == null) {
				tstdevconnectionPool = new ConnectionPool("oracle.jdbc.driver.OracleDriver", url, tstuser, tstpassword);
				firstflag = true;
			}
			break;
		case tpdev: // ���ɿ�������
			if (devconnectionPool == null) {
				devconnectionPool = new ConnectionPool("oracle.jdbc.driver.OracleDriver", url, devuser, devpassword);
				firstflag = true;
			}
			break;
		}

		if(firstflag){ // ���ν������ӳ�ʱ����keepalive�߳�
			// ��һ���̣߳� ÿ5����ѭ�����ӳ������е�connection, ִ��һ����䱣�����Ӳ���ʱ
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						logger.debug("��ʼˢ�����ӳ�����  " + env);
						ConnectionPool cp = DBManager.getConnectionPool(env);
						if (cp != null) {
							Vector<PooledConnection> connPool = cp.getConnections();
							for (PooledConnection conn : connPool) {
								logger.debug("hashcode:" + conn.hashCode() + ",busy:" + conn.isBusy() + ",holdtime:" + (new Date().getTime() - conn.getHoldTime()));
								if (conn.isBusy()) { // ��ռ�õ�connection,�ж���ʱ���Ƿ����ʱ���Ƿ�ʱ����ʱ��ֱ�ӹر�
									long curTime = new Date().getTime();
									if (curTime - conn.getHoldTime() > PooledConnection.timeout) { // ���ӳ���ʱ�䳬����ʱʱ��
										try { // �رյ�ǰ����
											Connection jcon = conn.getConnection();
											logger.debug("�г���30��δ�رյ����ݿ����� hashcode:" + jcon.hashCode() + ",conn.isClosed():" + jcon.isClosed());
											if (null != jcon && !jcon.isClosed())
												jcon.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										conn.setBusy(false);// ������Ϊ��ռ��
									}
								} else { // δ��ռ�õ�conn, ��������ˢ��,��ֹ��ʱ
									conn.setBusy(true);
									Connection jcon = conn.getConnection();
									try {
										if (null != jcon && !jcon.isClosed()) {
											Statement stmt = jcon.createStatement();
											stmt.execute("select 1 from dual");
											stmt.close(); // ����Ҫ ���رջᵼ�� java.sql.SQLException: ORA-01000: �������α�������
										}
									} catch (SQLException e) {
										logger.debug("����������Ч��ʧ��: " + ToolUtil.getExceptionMsg(e));
										try {
											jcon.close();
										} catch (SQLException e1) {
											logger.debug("�ر�����ʧ��: " + ToolUtil.getExceptionMsg(e));
										}
									}
									conn.setBusy(false);
								}
							}
						}
	
						try {
	
							Thread.sleep(5 * 60 * 1000); // ÿ5����ˢ��һ�����ӳ��е�����
	
						} catch (InterruptedException e) {
							// donothing
						}
	
					}
				}
			}).start();
		}

	}

	private DBManager() { // ������ ����ʵ��������
	}

	/**
	 * @author zhenwei.liu
	 * @date 2015-11-26
	 * @param env
	 *            ���ݿ⻷�� dev:���ɿ��� tst:���ɲ���
	 * @param preparedSql
	 *            ��ִ��sql
	 * @param paraList
	 *            sql�ж�Ӧ�Ĳ����б�</br> ��֧������ </br> java.lang.String</br>
	 *            java.math.BigDecimal</br> java.sql.Date</br>
	 *            java.sql.Timestamp</br> java.lang.Integer</br>
	 *            java.lang.Long</br>
	 * @param cb
	 *            �ص�����, select����ʹ��QueryCallBack</br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��дdisposeResultSet�������ƴ����ѯ���
	 * @return
	 * @throws Exception
	 */
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, QueryCallback cb) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql��?ռλ���ĸ���
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("�����б������sql����");
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
	 * @param env 			Ҫ���ӵ����ݿ⻷��
	 * @param preparedSql	Ԥ����sql
	 * @param paraList		�����б�
	 * @param beanClass		���ؽ������
	 * @return
	 * @throws Exception
	 */
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, @SuppressWarnings("rawtypes") Class beanClass) throws Exception {
		return executeQuery(env, preparedSql,paraList,beanClass, null );
	}
	
	/**
	 * @param env 			Ҫ���ӵ����ݿ⻷��
	 * @param preparedSql	Ԥ����sql
	 * @param paraList		�����б�
	 * @param beanClass		���ؽ������
	 * @param nameMappings 	ResultSet����Bean�в��ܶ�Ӧ�Ĳ�����ֵ��   tablefield:classField
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object executeQuery(EnvEnum env, String preparedSql, List<Object> paraList, Class beanClass, HashMap nameMappings ) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql��?ռλ���ĸ���
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("�����б������sql����");
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
	 *            ���ݿ⻷�� dev:���ɿ��� tst:���ɲ���
	 * @param preparedSql
	 *            ��ִ��sql
	 * @param paraList
	 *            sql�ж�Ӧ�Ĳ����б�</br> ��֧������ </br> java.lang.String</br>
	 *            java.math.BigDecimal</br> java.sql.Date</br>
	 *            java.sql.Timestamp</br> java.util.Date</br>
	 *            java.lang.Long</br> java.lang.Integer</br>
	 * @return
	 * @throws Exception
	 */
	public static int executeUpdate(EnvEnum env, String preparedSql, List<Object> paraList) throws Exception {
		int qMarkNum = getQMarkNum(preparedSql); // Sql��?ռλ���ĸ���
		int paraNum = paraList == null ? 0 : paraList.size();
		if (qMarkNum != paraNum) {
			throw new Exception("�����б������sql����");
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
		if (paraList == null || paraList.size() == 0) { // ����Ҫ���ò���
			return;
		} else {
			for (int i = 0; i < paraList.size(); i++) {
				Object para = paraList.get(i);
				int parameterIndex = i + 1;// sql�Ĳ������ô�1��ʼ
				if (para == null) {
					ps.setString(parameterIndex, null); // ���������ֶ�Ҳ���ô˷�����Ϊ��ֵ
					// throw new Exception("��ѯ�������п�ֵ");
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
					throw new Exception("��֧�ֵĲ�������,����λ��" + i);
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
		case tptstoper: // ���ɲ��Ի���
			if (tstdevconnectionPool == null) {
				connectPool(env);
			}
			cp = tstdevconnectionPool;
			break;
		case tpdev: // ���ɿ�������
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
