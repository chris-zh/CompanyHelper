package com.ebao.life.gavin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.util.QueryCallback;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.LeaveVO;
import com.ebao.life.gavin.vo.VersionPlanVO;

public class OverTimeDAO {


	public void exportOverTimeLeaveRecord() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 添加一条记录
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int addOverTimeLeaveRecord(LeaveVO vo) throws Exception {
		String name = vo.getName();
		String reason = vo.getReason();
		String version = vo.getVersion();
		version = version.replace(".","");
		String week = vo.getWeek();
		StringBuffer sql = new StringBuffer(300);
		sql.append(" INSERT INTO EBAO_LEAVE    ");
		sql.append("   (APPLY_ID,              ");
		sql.append("    APPLY_NAME,            ");
		sql.append("    APPLY_REASON,          ");
		sql.append("    APPLY_DATE,            ");
		sql.append("    OVERTIME_DATE,         ");
		sql.append("    CURRENT_VERSION,       ");
		sql.append("    WEEK_NUMBER)           ");
		sql.append(" VALUES                    ");
		sql.append("   ((SELECT MAX(APPLY_ID)+ 1 FROM EBAO_LEAVE),");
		sql.append("   ?,?,SYSDATE,SYSDATE,?,?) ");
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(name); 
		paraList.add(reason);
		paraList.add(version);
		paraList.add(week);
		int rowCnt = DBManager.executeUpdate(EnvEnum.tptstoper, sql.toString(), paraList);
		return rowCnt;
	}
	/**
	 * 指定版本的版本计划条目
	 * @param versionNumber
	 * @return
	 * @throws Exception
	 */
	public VersionPlanVO versionPlanItem(String versionNumber) throws Exception{
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT * FROM ebao_version_plan T WHERE 1=1 ");
		sb.append(" AND T.VERSION_NUMBER = ? ");
		
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(versionNumber);
		@SuppressWarnings("unchecked")
		List<VersionPlanVO> list = (List<VersionPlanVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, VersionPlanVO.class);
		return (VersionPlanVO)ToolUtil.listOne(list);
	}
	/**
	 * 获得当前日期的版本号
	 * @return
	 * @throws Exception
	 */
	public String nowVersion() throws Exception{
		StringBuffer sb = new StringBuffer(100);
		sb.append(" SELECT T.VERSION_NUMBER FROM EBAO_VERSION_PLAN T WHERE  SYSDATE > t.dev_start_time  AND SYSDATE < t.dev_end_time + 1 ");
		String nowVersion = (String)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), null, new QueryCallback(){
			@Override
			public Object disposeResultSet(ResultSet rs) throws SQLException {
				String nowVersion = null;
				while(rs.next()){
					nowVersion = rs.getString("VERSION_NUMBER");
				}
				
				return nowVersion;
			}
		});
		return nowVersion;
	}
	/**
	 * 通过版本号和周查询请假记录
	 * @param version
	 * @param week
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> leaveListForVersionAndWeek(String version, String week)throws Exception{
		StringBuffer sb = new StringBuffer(100);
		List<Object> paraList = new ArrayList<Object>();
		version = version.replace(".","");
		paraList.add(version);
		paraList.add(week); 
		sb.append(" SELECT T.APPLY_NAME,T.APPLY_REASON FROM EBAO_LEAVE T WHERE 1=1 AND T.IS_LEFT = 0 AND T.CURRENT_VERSION = ? AND T.WEEK_NUMBER = ? ");
		List<Map<String, String>> reList = (List<Map<String, String>>) DBManager.executeQuery(EnvEnum.tptstoper, sb.toString(), paraList, new QueryCallback(){
			@Override
			public Object disposeResultSet(ResultSet rs) throws SQLException {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				while(rs.next()){
					Map<String, String> map = new HashMap<String, String>();
					map.put("APPLY_NAME",rs.getString("APPLY_NAME"));
					map.put("APPLY_REASON", rs.getString("APPLY_REASON"));
					list.add(map);
				}
				return list;
			}
			
		});
		return reList;
	}
	/**
	 * 统计2015年请假记录
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> allSumCount()throws Exception{
		StringBuffer sql = new StringBuffer(100);
		List<Object> paraList = new ArrayList<Object>();
		sql.append(" SELECT A.APPLY_NAME, COUNT(*) CN                             ");
		sql.append("   FROM EBAO_LEAVE A                                          ");
		sql.append("  WHERE A.APPLY_DATE > TO_DATE('2016-01-01', 'YYYY-MM-DD')    ");
		sql.append("    AND A.IS_LEFT = 0                                         ");
		sql.append("  GROUP BY A.APPLY_NAME                                       ");
		sql.append("  ORDER BY CN DESC                                            ");
		List<Map<String, String>> reList = (List<Map<String, String>>) DBManager.executeQuery(EnvEnum.tptstoper, sql.toString(), null, new QueryCallback(){
			@Override
			public Object disposeResultSet(ResultSet rs) throws SQLException {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				while(rs.next()){
					Map<String, String> map = new HashMap<String, String>();
					map.put("APPLY_NAME", rs.getString("APPLY_NAME"));
					map.put("CN", rs.getString("CN"));
					list.add(map);
				}
				return list;
			}
		});
		return reList;
	}

}
