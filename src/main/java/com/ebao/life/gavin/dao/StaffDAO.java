package com.ebao.life.gavin.dao;

import java.util.ArrayList;
import java.util.List;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.vo.StaffVO;

public class StaffDAO {

	public StaffVO retriveStaffByName(String empName) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" select EMP_ID,             ");
		sb.append("        EMP_NAME,           ");
		sb.append("        ROLE,               ");
		sb.append("        STAFF_CODE,         ");
		sb.append("        STAFF_TYPE,         ");
		sb.append("        PROF_LEVEL,         ");
		sb.append("        REPORTO,            ");
		sb.append("        CHARGE_MODULE,      ");
		sb.append("        TP_CARD_CODE,       ");
		sb.append("        EXT,                ");
		sb.append("        MOBILE,             ");
		sb.append("        EMAIL,              ");
		sb.append("        to_char(WORK_DATE,'YYYY-MM-DD') WORK_DATE,          ");
		sb.append("        to_char(IN_EBAO_DATE,'YYYY-MM-DD') IN_EBAO_DATE,      ");
		sb.append("        to_char(IN_TP_DATE,'YYYY-MM-DD') IN_TP_DATE  ,      ");
		sb.append("        to_char(LEFT_DATE,'YYYY-MM-DD')  LEFT_DATE,        ");
		sb.append("        IS_INVER,           ");
		sb.append("        IS_SA,              ");
		sb.append("        GENDER,             ");
		sb.append("        SEAT_NO,            ");
		sb.append("        IS_CORE,            ");
		sb.append("        to_char(LS_WORK_DATE,'YYYY-MM-DD')  LS_WORK_DATE,     ");
		sb.append("        IP,                 ");
		sb.append("        ID_CARD,            ");
		sb.append("        NOTEBOOK_PC_MODEL,  ");
		sb.append("        NOTEBOOK_PC_NUM,    ");
		sb.append("        HOST_PC_NUM,        ");
		sb.append("        SCREEN_PC_NUM,      ");
		sb.append("        BAOXIAO,            ");
		sb.append("        DESCRIPT            ");
		sb.append("   FROM ebao_tp_staff       ");
		sb.append("  where EMP_NAME = ?        ");

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(empName);
		@SuppressWarnings("unchecked")
		List<StaffVO> voList = (List<StaffVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, StaffVO.class);

		return (voList!=null&&voList.size()>0)?voList.get(0):null;
		
	}

	public StaffVO retriveStaffByEmpIP(String ip) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		sb.append(" select EMP_ID,             ");
		sb.append("        EMP_NAME,           ");
		sb.append("        ROLE,               ");
		sb.append("        STAFF_CODE,         ");
		sb.append("        STAFF_TYPE,         ");
		sb.append("        PROF_LEVEL,         ");
		sb.append("        REPORTO,            ");
		sb.append("        CHARGE_MODULE,      ");
		sb.append("        TP_CARD_CODE,       ");
		sb.append("        EXT,                ");
		sb.append("        MOBILE,             ");
		sb.append("        EMAIL,              ");
		sb.append("        to_char(WORK_DATE,'YYYY-MM-DD') WORK_DATE,          ");
		sb.append("        to_char(IN_EBAO_DATE,'YYYY-MM-DD') IN_EBAO_DATE,      ");
		sb.append("        to_char(IN_TP_DATE,'YYYY-MM-DD') IN_TP_DATE,        ");
		sb.append("        to_char(LEFT_DATE,'YYYY-MM-DD')  LEFT_DATE ,       ");
		sb.append("        IS_INVER,           ");
		sb.append("        IS_SA,              ");
		sb.append("        GENDER,             ");
		sb.append("        SEAT_NO,            ");
		sb.append("        IS_CORE,            ");
		sb.append("        to_char(LS_WORK_DATE,'YYYY-MM-DD')  LS_WORK_DATE,     ");
		sb.append("        IP,                 ");
		sb.append("        ID_CARD,            ");
		sb.append("        NOTEBOOK_PC_MODEL,  ");
		sb.append("        NOTEBOOK_PC_NUM,    ");
		sb.append("        HOST_PC_NUM,        ");
		sb.append("        SCREEN_PC_NUM,      ");
		sb.append("        BAOXIAO,            ");
		sb.append("        DESCRIPT            ");
		sb.append("   FROM ebao_tp_staff       ");
		sb.append("  where IP = ?        	   ");
		sb.append("    AND LEFT_DATE > DATE '5555-05-05' "); //过滤掉离职员工

		List<Object> paraList = new ArrayList<Object>();
		paraList.add(ip);
		@SuppressWarnings("unchecked")
		List<StaffVO> voList = (List<StaffVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, StaffVO.class);

		return (voList!=null&&voList.size()>0)?voList.get(0):null;
	}

	public List<StaffVO> retriveInServiceStaffs(String sortBy, String token) throws Exception {

		StringBuffer sb = new StringBuffer(300);
		List<Object> paraList = new ArrayList<Object>();
		sb.append(" select EMP_ID,             ");
		sb.append("        EMP_NAME,           ");
		sb.append("        ROLE,               ");
		sb.append("        STAFF_CODE,         ");
		sb.append("        STAFF_TYPE,         ");
		sb.append("        PROF_LEVEL,         ");
		sb.append("        REPORTO,            ");
		sb.append("        CHARGE_MODULE,      ");
		sb.append("        TP_CARD_CODE,       ");
		sb.append("        EXT,                ");
		sb.append("        MOBILE,             ");
		sb.append("        EMAIL,              ");
		sb.append("        to_char(WORK_DATE,'YYYY-MM-DD') WORK_DATE,          ");
		sb.append("        to_char(IN_EBAO_DATE,'YYYY-MM-DD') IN_EBAO_DATE,      ");
		sb.append("        to_char(IN_TP_DATE,'YYYY-MM-DD') IN_TP_DATE,        ");
		sb.append("        to_char(LEFT_DATE,'YYYY-MM-DD')  LEFT_DATE,        ");
		sb.append("        IS_INVER,           ");
		sb.append("        IS_SA,              ");
		sb.append("        GENDER,             ");
		sb.append("        SEAT_NO,            ");
		sb.append("        IS_CORE,            ");
		sb.append("        to_char(LS_WORK_DATE,'YYYY-MM-DD')  LS_WORK_DATE,     ");
		sb.append("        IP,                 ");
		sb.append("        ID_CARD,            ");
		sb.append("        NOTEBOOK_PC_MODEL,  ");
		sb.append("        NOTEBOOK_PC_NUM,    ");
		sb.append("        HOST_PC_NUM,        ");
		sb.append("        SCREEN_PC_NUM,      ");
		sb.append("        BAOXIAO,            ");
		sb.append("        DESCRIPT            ");
		sb.append("   FROM ebao_tp_staff   t   ");
		sb.append("  WHERE T.LEFT_DATE > DATE '5555-05-05' ");
		if("STAFF_CODE".equals(sortBy)){
			sb.append("  ORDER BY LPAD(T.STAFF_CODE,7,'0') " + token + ",T.IN_EBAO_DATE asc       ");
		}else{
			sb.append("  ORDER BY NLSSORT(T.EMP_NAME,'NLS_SORT = SCHINESE_PINYIN_M') " + token + ",T.IN_EBAO_DATE asc       ");
		}


		@SuppressWarnings("unchecked")
		List<StaffVO> voList = (List<StaffVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, StaffVO.class);

		return voList;

	}

	public List<StaffVO> retriveOutServiceStaffs() throws Exception {
		StringBuffer sb = new StringBuffer(300);
		sb.append(" select EMP_ID,             ");
		sb.append("        EMP_NAME,           ");
		sb.append("        ROLE,               ");
		sb.append("        STAFF_CODE,         ");
		sb.append("        STAFF_TYPE,         ");
		sb.append("        PROF_LEVEL,         ");
		sb.append("        REPORTO,            ");
		sb.append("        CHARGE_MODULE,      ");
		sb.append("        TP_CARD_CODE,       ");
		sb.append("        EXT,                ");
		sb.append("        MOBILE,             ");
		sb.append("        EMAIL,              ");
		sb.append("        to_char(WORK_DATE,'YYYY-MM-DD') WORK_DATE,          ");
		sb.append("        to_char(IN_EBAO_DATE,'YYYY-MM-DD') IN_EBAO_DATE,      ");
		sb.append("        to_char(IN_TP_DATE,'YYYY-MM-DD') IN_TP_DATE,        ");
		sb.append("        to_char(LEFT_DATE,'YYYY-MM-DD')  LEFT_DATE,        ");
		sb.append("        IS_INVER,           ");
		sb.append("        IS_SA,              ");
		sb.append("        GENDER,             ");
		sb.append("        SEAT_NO,            ");
		sb.append("        IS_CORE,            ");
		sb.append("        to_char(LS_WORK_DATE,'YYYY-MM-DD')  LS_WORK_DATE,     ");
		sb.append("        IP,                 ");
		sb.append("        ID_CARD,            ");
		sb.append("        NOTEBOOK_PC_MODEL,  ");
		sb.append("        NOTEBOOK_PC_NUM,    ");
		sb.append("        HOST_PC_NUM,        ");
		sb.append("        SCREEN_PC_NUM,      ");
		sb.append("        BAOXIAO,            ");
		sb.append("        DESCRIPT            ");
		sb.append("   FROM ebao_tp_staff  t     ");
		sb.append("  WHERE T.LEFT_DATE < DATE '5555-05-05' ");
		sb.append("  ORDER BY NLSSORT(T.EMP_NAME,'NLS_SORT = SCHINESE_PINYIN_M') asc,T.IN_EBAO_DATE asc       ");

		List<Object> paraList = new ArrayList<Object>();
		
		@SuppressWarnings("unchecked")
		List<StaffVO> voList = (List<StaffVO>)DBManager.executeQuery(EnvEnum.tpdev, sb.toString(), paraList, StaffVO.class);

		return voList;
	}
	
	public static void main(String[] args) throws Exception {
		StaffDAO dao = new StaffDAO();
		dao.retriveOutServiceStaffs();
	}

}
