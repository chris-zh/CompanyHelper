package com.ebao.life.gavin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebao.life.gavin.bo.ProjectDisposal;
import com.ebao.life.gavin.bo.RightControl;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.ModuleVO;

/**
 * Servlet implementation class ProjectHelper
 */

@WebServlet("/unauth")
public class RootServlet extends HttpServlet {
	private static Logger defaultlogger = Logger.getLogger("default");  
	private static Logger accesslogger = Logger.getLogger("access");  
	
	private static final long serialVersionUID = 1L;
	public static final ProjectDisposal disposal = new ProjectDisposal(); 
	public static final RightControl rightControl = new RightControl();

	
	
	/**
	 * @see HttpServlet#HttpServlet() 
	 */
	public RootServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = null;
		try {    
			String[] ipAndName = disposal.getIPAndName(request);
			String userIP = ipAndName[0];
			String empName = ipAndName[1];
			/*
			logger.info("getContextPath is " + request.getContextPath());  //  /company
			logger.info("getPathInfo is " + request.getPathInfo()); 	// null
			logger.info("getPathTranslated is " + request.getPathTranslated()); // null
			logger.info("getRequestURI is " + request.getRequestURI()); // /company/xmlformat
			logger.info("getRequestURL is " + request.getRequestURL()); // http://10.7.219.132:8080/company/xmlformat
			*/
			
			String page = ToolUtil.getUriParameter(request.getRequestURI(), 2);
			
			if(ToolUtil.isEmpty(page)){
				page = "bookmark";
			}
			
			//String page = "xmlformat";
			String action = request.getParameter("sAction");
			accesslogger.info("IP: " + userIP + ", Name: " + empName + ", page: " + page+ ", sAction: " + action);
			
			List<ModuleVO>  allTitleList = rightControl.getTitleListByStaffCodeOrIp(userIP);
			List<ModuleVO> firstTitleList = rightControl.getFirstTitleList(allTitleList);
		 	Map<String,List<ModuleVO>> secondTitleMap = rightControl.getSecondTitleMap(allTitleList);
		 	request.setAttribute("firstTitleList", firstTitleList);
		 	request.setAttribute("secondTitleMap", secondTitleMap);
			request.setAttribute("userIP", userIP);

			if(rightControl.hasRight(allTitleList, page)){ // 检查用户权限
				if (ToolUtil.isEmpty(action)) { // 页面跳转
					request.setAttribute("empName", empName);
					request.setAttribute("page", page);
					pageRequest(request, response);
					forwardPage(request, response);
				} else { // Ajax动作请求
					defaultlogger.info("IP: " + userIP + ", Name: " + empName + ", page: " + page+ ", sAction: " + action);
					actionRequest(request, response, action);
				}
			}else{
				request.setAttribute("page", "unauth");
				forwardPage(request, response);
			}
		} catch (Exception e) {
			defaultlogger.error(ToolUtil.getExceptionMsg(e));
			response.setContentType("text/html;charset=UTF-8"); // 必须在response.getWriter()之前
			out = response.getWriter();
			out.write("<font color='red'>ooooppppssss! An Error Occured!</font>");
		} finally {
			if (out != null) {
				out.flush();
				out.close();  
			}
		} 
	}

	private void forwardPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * 处理异步请求
	 * @param request
	 * @param response
	 * @param action
	 * @throws Exception
	 */
	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception{
		
	}
	
	/**
	 * 进入页面， 填写页面数据使用该方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	};
	
}
