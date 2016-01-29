package com.ebao.life.gavin.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.vo.HolidayVO;

public class HolidayDAO {

	public HolidayVO getAllHoliday() {

		return null;
	}

	/**
	 * 通过员工姓名查询假期记录
	 * @param empName
	 * @return 该员工所有假期记录， map.key 员工名称 	map.value 所有假期记录List
	 * @throws Exception
	 */
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
	
	/**
	 * 获得所有员工假期记录
	 * @return 所有员工假期记录， map.key 员工名称 	 map.value 员工假期记录List
	 * @throws Exception
	 */
	public Map<String,List<HolidayVO>> retriveAllEmpHoliday() throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" SELECT EMP_NAME,       ");
		sb.append("        to_char(HOLIDAY_DATE,'YYYY-MM-DD') HOLIDAY_DATE,   ");
		sb.append("        HOLIDAY_TYPE,   ");
		sb.append("        to_char(REFERENCE_DATE,'YYYY-MM-DD') REFERENCE_DATE, ");
		sb.append("        HOLIDAY_LONG,   ");
		sb.append("        INSERT_TIME,    ");
		sb.append("        COMMENTS        ");
		sb.append("   FROM ebao_tp_holiday ");
		sb.append("   order by NLSSORT(emp_name,'NLS_SORT = SCHINESE_PINYIN_M') asc,holiday_date desc "); //oracle 按照拼音排序
		List<Object> paraList = new ArrayList<Object>();
		Map<String,List<HolidayVO>> empHolidayMap = new LinkedHashMap<String,List<HolidayVO>>();
		@SuppressWarnings("unchecked")
		List<HolidayVO> allvoList = (List<HolidayVO>)DBManager.executeQuery(EnvEnum.tptstoper, sb.toString(), paraList, HolidayVO.class);
		
		for(HolidayVO vo:allvoList){
			String empName = vo.getEmpName();
			if(empHolidayMap.containsKey(vo.getEmpName())){
				empHolidayMap.get(empName).add(vo);
			}else{
				List<HolidayVO> empvoList = new ArrayList<HolidayVO>();
				empvoList.add(vo);
				empHolidayMap.put(empName, empvoList);
			}
		}

		return empHolidayMap;
	}
	
	public static void main(String[] args) throws Exception {
		HolidayDAO dao = new HolidayDAO();
		Map<String,List<HolidayVO>> empHolidayMap = dao.retriveHolidayByEmp("刘珍伟");
		System.out.println(empHolidayMap);
	}

	
}
