package com.ebao.life.gavin.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.bo.StaffBO;
import com.ebao.life.gavin.vo.StaffVO;

@WebServlet("/contact")
public class ContactServlet extends RootServlet {
	private static final long serialVersionUID = 1L;

	private final StaffBO staffBO = new StaffBO();

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {

	}

	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sortBy = "name".equals(request.getParameter("sortBy"))?"EMP_NAME":"STAFF_CODE";
		String token = "desc".equals(request.getParameter("token"))?"desc":"asc";
		
		List<StaffVO> staffList = staffBO.retriveInServiceStaffs(sortBy, token);

		request.setAttribute("staffList", staffList);
	}
	
}
