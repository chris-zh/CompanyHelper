package com.ebao.life.gavin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectHelper
 */
@WebServlet("/checktool")
public class ChecktoolServlet extends RootServlet {
	private static final long serialVersionUID = 1L;

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {

	}

	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("page", "unauth"); // not complete
	}
	
}