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
	 * ͨ��Ա��������ѯ���ڼ�¼
	 * @param empName
	 * @return ��Ա�����м��ڼ�¼�� map.key Ա������ 	map.value ���м��ڼ�¼List
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
	 * �������Ա�����ڼ�¼
	 * @return ����Ա�����ڼ�¼�� map.key Ա������ 	 map.value Ա�����ڼ�¼List
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
		sb.append("   order by NLSSORT(emp_name,'NLS_SORT = SCHINESE_PINYIN_M') asc,holiday_date desc "); //oracle ����ƴ������
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
		Map<String,List<HolidayVO>> empHolidayMap = dao.retriveHolidayByEmp("����ΰ");
		System.out.println(empHolidayMap);
	}

	
}
