package com.ebao.life.gavin.dao;

import java.util.ArrayList;
import java.util.List;

import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;
import com.ebao.life.gavin.vo.ModuleVO;

public class RightControlDAO {
	
	public List<ModuleVO> getTitleListByStaffCodeOrIp(String staffCodeOrIp) throws Exception {

		StringBuffer preparedSql = new StringBuffer(300);
		preparedSql.append("SELECT MODULE_ID,                  ");
		preparedSql.append("       MODULE_NAME,                ");
		preparedSql.append("       MODULE_DESC,                ");
		preparedSql.append("       MODULE_TYPE,                ");
		preparedSql.append("       PARENT_MODULE,              ");
		preparedSql.append("       MODULE_ORDER,               ");
		preparedSql.append("       NEED_RIGHT,                 ");
		preparedSql.append("       INSERT_TIME                 ");
		preparedSql.append("  FROM T_GAVIN_MODULE T            ");
		preparedSql.append(" WHERE T.NEED_RIGHT = 'N'          ");
		preparedSql.append("UNION ALL                          ");
		preparedSql.append("SELECT MODULE_ID,                  ");
		preparedSql.append("       MODULE_NAME,                ");
		preparedSql.append("       MODULE_DESC,                ");
		preparedSql.append("       MODULE_TYPE,                ");
		preparedSql.append("       PARENT_MODULE,              ");
		preparedSql.append("       MODULE_ORDER,               ");
		preparedSql.append("       NEED_RIGHT,                 ");
		preparedSql.append("       INSERT_TIME                 ");
		preparedSql.append("  FROM T_GAVIN_MODULE T            ");
		preparedSql.append(" WHERE T.NEED_RIGHT = 'Y'          ");
		preparedSql.append("   AND T.MODULE_ID IN              ");
		preparedSql.append("   (SELECT RC.MODULE_ID            ");
		preparedSql.append("     FROM T_GAVIN_RIGHT_CONTROL RC ");
		preparedSql.append("    WHERE RC.STAFF_CODE_OR_IP = ?  ");
		preparedSql.append("      AND RC.HAS_RIGHT = 'Y')      ");
		preparedSql.append(" ORDER BY MODULE_ORDER	       	   ");
		
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(staffCodeOrIp);
		 
		@SuppressWarnings("unchecked")
		List<ModuleVO> listVO = (List<ModuleVO>)DBManager.executeQuery(EnvEnum.tpdev, preparedSql.toString(), paraList, ModuleVO.class);

		return listVO;
	}

}
