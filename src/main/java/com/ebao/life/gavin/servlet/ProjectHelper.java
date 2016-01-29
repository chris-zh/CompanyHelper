package com.ebao.life.gavin.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebao.life.gavin.bo.HolidayBO;
import com.ebao.life.gavin.bo.NoteBO;
import com.ebao.life.gavin.bo.ProjectDisposal;
import com.ebao.life.gavin.bo.StaffBO;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.HolidayVO;
import com.ebao.life.gavin.vo.NoteVO;
import com.ebao.life.gavin.vo.StaffVO;

/**
 * Servlet implementation class ProjectHelper
 */

public class ProjectHelper extends HttpServlet {
	private static Logger logger = Logger.getLogger(ProjectHelper.class);  
	private static final long serialVersionUID = 1L;
	private static final String logPath = "D:/Gavin/Program/Tomcat7/apache-tomcat-7.0.55/logs/";
	//private static final String logPath = "D:/Gavin/Installed/apache-tomcat-7.0.55/logs/";
	private final ProjectDisposal disposal = new ProjectDisposal(); 

	private final StaffBO staffBO = new StaffBO();
	private final HolidayBO holidayBO = new HolidayBO();
	private final NoteBO noteBO = new NoteBO();

	/**
	 * @see HttpServlet#HttpServlet() 
	 */
	public ProjectHelper() {
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
			disposal.logUserQuest(request, response); // 记录用户请求信息
 
			String page = "bookmark";
			String action = request.getParameter("sAction");

			if (null != action && !"".equals(action)) {
				actionRequest(request, response, action);
			} else {
				pageRequest(request, response, page);
			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionMsg(e));
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

	private void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
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
		if ("saveNote".equals(action)) {
			String noteId = request.getParameter("noteId");
			String noteTitle = request.getParameter("noteTitle");
			String noteContent = request.getParameter("noteContent");
			//logger.debug(noteId + "-----" + noteTitle + "-----" + noteContent);
			String userIP = disposal.getUserIP(request);
			String resXML = disposal.saveNote(noteId, noteTitle, noteContent, userIP);
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(resXML); 
		} 
		if ("noteContent".equals(action)) {
			String noteId = request.getParameter("noteId");
			NoteVO noteVO =  noteBO.getNoteByID(noteId);
			String noteContent = "";
			if(!(noteVO == null) && !ToolUtil.isEmpty(noteVO.getNoteContent())){
				noteContent = noteVO.getNoteContent();
			}
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(noteContent); 
		} 
		if ("noteDelete".equals(action)) {
			String noteId = request.getParameter("noteId");
			int rowCnt =  noteBO.deleteNoteByID(noteId);
			String returnMsg = "N";
			if(rowCnt > 0){
				returnMsg = "Y";
			}
			response.setContentType("text/plain;charset=UTF-8");
			out = response.getWriter();
			out.write(returnMsg); 
		} 
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

	private void pageRequest(HttpServletRequest request, HttpServletResponse response, String page) throws Exception {

		String userIP = disposal.getUserIP(request);
		request.setAttribute("userIP", userIP);
		StaffVO staff = staffBO.retriveStaffByEmpIP(userIP);
		String empName = staff==null?"未获取":staff.getEmpName();
		request.setAttribute("empName", empName);

		if (page == null || "".equals(page) || "init".equals(page)) {
			// init(request, response);
			bookmark(request, response);
		} else if ("bookmark".equals(page)) {
			bookmark(request, response);
		} else if ("contact".equals(page)) { 
			contact(request, response);
		} else if ("holiday".equals(page)) {
			holiday(request, response);
		} else if ("hessian".equals(page)) {
			hessian(request, response);
		} else if ("xmlformat".equals(page)) {
			xmlformat(request, response); 
		} else if ("notepad".equals(page)) {
			notepad(request, response);
		} else if ("log".equals(page)) {
			log(request, response);
		}

		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
		rd.forward(request, response);
	}

	private void contact(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		List<StaffVO> staffList = staffBO.retriveInServiceStaffs();
//		request.setAttribute("staffList", staffList);
//		request.setAttribute("page", "contact");
	}

	private void holiday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String admin = request.getParameter("admin");
		String empName = (String) request.getAttribute("empName");
		Map<String, BigDecimal[]> sumHolidayMap = new HashMap<String, BigDecimal[]>();
		// if(配置IP权限，部分IP可获得所有人员)
		// Map<String,List<HolidayVO>> empHolidayMap =
		// holidayBO.retriveAllEmpHoliday();
		Map<String, List<HolidayVO>> empHolidayMap = new HashMap<String, List<HolidayVO>>();
		if ("liuzhenwei".equals(admin)) {
			empHolidayMap = holidayBO.retriveAllEmpHoliday();
		} else {
			empHolidayMap = holidayBO.retriveHolidayByEmp(empName);
		}
		for (String emp : empHolidayMap.keySet()) { // 兼容多个员工
			BigDecimal[] sumHoliday = holidayBO.getSumHoliday(empHolidayMap.get(emp));
			sumHolidayMap.put(emp, sumHoliday);
		}

		List<StaffVO> outStaffList = staffBO.retriveOutServiceStaffs();
		empHolidayMap = holidayBO.removeOutServiceEmp(empHolidayMap, outStaffList);
		empHolidayMap = holidayBO.sortEmpHoliday(empHolidayMap); // 去掉已使用

		request.setAttribute("empHolidayMap", empHolidayMap);
		request.setAttribute("sumHolidayMap", sumHolidayMap);

		request.setAttribute("page", "holiday");
	}

	private void hessian(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("page", "hessian");
	}

	private void xmlformat(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("page", "xmlformat");
	}

	private void notepad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userIP = disposal.getUserIP(request);
		List<NoteVO> noteList = noteBO.getNotesByIP(userIP);
		
		request.setAttribute("noteList", noteList);
		request.setAttribute("page", "notepad");
	}

	private void bookmark(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		request.setAttribute("page", "bookmark");
	}

	private void log(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("filenames", new File(logPath).list());
		
		request.setAttribute("page", "log"); 
	}
	
	public static void main(String[] args) throws IOException {
//		String logContent = "";
//		InputStreamReader read = new InputStreamReader(new FileInputStream(logPath+"log4j.log.2015-12-11-14-04"),"UTF-8");// 考虑到编码格式
//		//FileReader read = new FileReader(logPath+"log4j.log.2015-12-11-14-04");
//		BufferedReader bufferedReader = new BufferedReader(read);
//		
//		String lineTxt = null;
//		while ((lineTxt = bufferedReader.readLine()) != null) {
//			logContent += "\n" + lineTxt;
//		}
//		read.close();
//		bufferedReader.close();
//		System.out.println("日志内容是\n" + logContent);
		
	       // 将Map转换为JSONArray数据
//		Map map1 = new HashMap();
//		String s1 = "ssss";
//		String s2 = "tttt";
//		map1.put("test", 1231312);
//		map1.put("str1", "str2");
//		map1.put(121, 232);
//		map1.put(s1, s2);
//		
//        JSONArray ja = new JSONArray();
//        ja.put(map1);
//
//        System.out.println("JSONArray对象数据格式：");
//        System.out.println(ja.toString());
	}

}
