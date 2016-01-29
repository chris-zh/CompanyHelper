package com.ebao.life.gavin.bo;

import java.util.List;

import com.ebao.life.gavin.dao.StaffDAO;
import com.ebao.life.gavin.vo.StaffVO;


public class StaffBO {
	
	private final StaffDAO dao = new StaffDAO();

	public StaffVO retriveStaffByName(String empName) throws Exception {
		return dao.retriveStaffByName(empName);
	}
	

	public StaffVO retriveStaffByEmpIP(String ip) throws Exception {
		return dao.retriveStaffByEmpIP(ip);
	}
	
	public List<StaffVO> retriveInServiceStaffs(String sortBy, String token) throws Exception {
		return dao.retriveInServiceStaffs(sortBy, token);
	}
	
	public List<StaffVO> retriveOutServiceStaffs() throws Exception {
		return dao.retriveOutServiceStaffs();
	}
	

}
