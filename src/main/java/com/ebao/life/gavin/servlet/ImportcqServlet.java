package com.ebao.life.gavin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.bo.ImportcqBO;

@WebServlet("/importcq")
public class ImportcqServlet extends RootServlet {
	private static final long serialVersionUID = 1L;
	private ImportcqBO bo = new ImportcqBO();

	/**
	 * action·½·¨Ìø×ª
	 * 
	 * @param request
	 * @param response
	 * @param action
	 * @throws Exception
	 */
	public void actionRequest(HttpServletRequest request,
			HttpServletResponse response, String action) throws Exception {
		if ("sr".equals(action)) {
			createSr(request, response);
		} else if ("design".equals(action)) {
			createDesign(request, response);
		} else if ("coding".equals(action)) {
			createCoding(request, response);
		} 
	}

	private void createCoding(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void createDesign(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void createSr(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bo.downloadSR(response);
	}
}
