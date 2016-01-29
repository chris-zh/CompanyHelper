package com.ebao.life.gavin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.util.QueryCallback;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.HolidayVO;

public class ImportcqDAO {
	/**
	 * 查询待处理的SR
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> retriveSRlist() throws Exception{
		StringBuffer sb = new StringBuffer();
		String version = "V3.90";
		sb.append("SELECT DISTINCT 'Taiping' PROJECT,                                                  ");
		sb.append("                ? AS VERSION,                                                    ");
		sb.append("                'Confirmed' STATE,                                                ");
		sb.append("                '' DUPLICATESRID,                                                         ");
		sb.append("                TO_CHAR(SYSDATE,'YYYY-MM-DD') SUBMIT_DATE,                                             ");
		sb.append("                ENV.SR_NO SYSTEMREQID,                                                  ");
		sb.append("                REPLACE(SUBSTR(ENV.SR_HEAD_LINE, 10), ' ', '') HEADLINE,             ");
		sb.append("                REPLACE(SUBSTR(ENV.SR_HEAD_LINE, 10), ' ', '') DESCRIPTION,             ");
		sb.append("                '40' TOTALLY_EFFORT,                                                       ");
		sb.append("                LENGTH(REPLACE(SUBSTR(ENV.SR_HEAD_LINE, 10), ' ', '')) SRID_COUNT,     ");
		sb.append("                LENGTH(REPLACE(SUBSTR(ENV.SR_HEAD_LINE, 10), ' ', '')) HEADLINE_COUNT     ");
		sb.append("  FROM EBAO_NEXT_VERSION ENV                                                ");
		sb.append(" WHERE 1 = 1                                                                ");
		sb.append("   AND ENV.IS_DONE = 0                                                     ");
		
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(version);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> result = (List<Map<String, Object>>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, new QueryCallback(){
			@Override
			public Object disposeResultSet(ResultSet rs) throws SQLException {
				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				while(rs.next()){
					Map<String, Object> rowMap = new HashMap<String, Object>();
					rowMap.put("PROJECT", rs.getString("PROJECT"));
					rowMap.put("VERSION", rs.getString("VERSION"));
					rowMap.put("STATE", rs.getString("STATE"));
					rowMap.put("DUPLICATESRID", ToolUtil.nullToEmpty(rs.getString("DUPLICATESRID")));
					rowMap.put("SUBMIT_DATE", rs.getString("SUBMIT_DATE"));
					rowMap.put("SYSTEMREQID", rs.getString("SYSTEMREQID"));
					rowMap.put("HEADLINE", rs.getString("HEADLINE"));
					rowMap.put("DESCRIPTION", rs.getString("DESCRIPTION"));
					rowMap.put("TOTALLY_EFFORT", rs.getString("TOTALLY_EFFORT"));
					rowMap.put("SRID_COUNT", rs.getString("SRID_COUNT"));
					rowMap.put("HEADLINE_COUNT", rs.getString("HEADLINE_COUNT"));
					result.add(rowMap);
				}
				return result;
			}
		});
		return result;
	}
	
	public Map<String,List<HolidayVO>> retriveHolidayByEmp(String empName) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" SELECT EMP_NAME,       ");
		sb.append("        to_char(HOLIDAY_DATE,'YYYY-MM-DD') HOLIDAY_DATE,   ");
		sb.append("        HOLIDAY_TYPE,   ");
		sb.append("        to_char(REFERENCE_DATE,'YYYY-MM-DD') REFERENCE_DATE, ");
		sb.append("        HOLIDAY_LONG,   ");
		sb.append("        INSERT_TIME,    ");
		sb.append("        COMMENTS        ");
		sb.append("   FROM ebao_tp_holiday ");
		sb.append("  where emp_name = ?    ");
		sb.append("   order by holiday_date desc ");
		
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(empName);
		Map<String,List<HolidayVO>> empHolidayMap = new LinkedHashMap<String,List<HolidayVO>>();
		@SuppressWarnings("unchecked")
		List<HolidayVO> voList = (List<HolidayVO>)DBManager.executeQuery(EnvEnum.tptstoper, sb.toString(), paraList, HolidayVO.class);
		
		empHolidayMap.put(empName, voList);

		return empHolidayMap;
	}

}
