package com.ebao.life.gavin.servlet;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hessian")
public class XMLFormatServlet extends RootServlet {
	private static final long serialVersionUID = 1L;

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
		PrintWriter out = null;
		
		if ("formatXML".equals(action)) {
			String reqXML = request.getParameter("xmlPacket");
			// System.out.println("========Request XML========");
			// System.out.println(reqXML);
			String resXML = disposal.formatXML(reqXML);
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(resXML);
		}
		if ("hessianTest".equals(action)) {
			String reString = disposal.hessianQuest(request, response);
			// request.setAttribute("contents_res", reString);
			// String resXML = "success\n" + new Date();
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(reString);
		}
		
		if (out != null) {
			out.flush();
			out.close(); 
		} 
	}

	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
	
}
