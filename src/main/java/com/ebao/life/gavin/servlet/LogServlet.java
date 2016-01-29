package com.ebao.life.gavin.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.util.ToolUtil;

@WebServlet("/log/*")
public class LogServlet extends RootServlet {
	private static final long serialVersionUID = 1L;
	private final static String logPath = System.getProperty("catalina.home") + "/log4j/";

	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
		PrintWriter out = null;
		
		if ("loadLog".equals(action)) {
			String logfilename = request.getParameter("log_file_name");
			String logContent = "";
			FileReader read = new FileReader(logPath+logfilename);
			//InputStreamReader read = new InputStreamReader(new FileInputStream(logPath+logfilename),"UTF-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null; 
			while ((lineTxt = bufferedReader.readLine()) != null) {
				logContent += "\n" + lineTxt;
			}
			bufferedReader.close();
			read.close(); 
			// hessian code test here
			// 测试异步
			// Thread.currentThread().sleep(2000); 
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(logContent);
		}
		if (out != null) {
			out.flush();
			out.close(); 
		} 
	}
	
	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String selectedFilename = ToolUtil.getUriParameter(request.getRequestURI(), 3);
		String logContent = "";
		
		if(!ToolUtil.isEmpty(selectedFilename)){
			File file = new File(logPath+selectedFilename);
			if(file.exists()){
				FileReader read = new FileReader(file);
				//InputStreamReader read = new InputStreamReader(new FileInputStream(logPath+logfilename),"UTF-8");// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null; 
				while ((lineTxt = bufferedReader.readLine()) != null) {
					logContent += "\n" + lineTxt;
				}
				bufferedReader.close();
				read.close(); 
			}
		}
		
		request.setAttribute("selectedFilename", selectedFilename);
		request.setAttribute("logContent", logContent);
		request.setAttribute("filenames", new File(logPath).list());
	}

}
