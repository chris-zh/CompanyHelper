package com.ebao.life.gavin.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.bo.OverTimeBO;
import com.ebao.life.gavin.bo.SendMailBO;
import com.ebao.life.gavin.cache.Cache;
import com.ebao.life.gavin.util.ToolUtil;

@WebServlet("/overtime")
public class OverTimeServlet extends RootServlet {
	private static final long serialVersionUID = 1L;
	private OverTimeBO bo = new OverTimeBO();
	private SendMailBO mailBO = new SendMailBO();
	private Cache cache = Cache.getInstance();

	/**
	 * action方法跳转
	 * @param request
	 * @param response
	 * @param action
	 * @throws Exception
	 */
	public void actionRequest(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
		if(isAccess(request,response)){
			if("save".equals(action)){
				addOverTimeLeaveRecord(request, response);
			}else if("export".equals(action)){
				exportOverTimeLeaveRecord(request, response);
			}else if(action == null||"init".equals(action)){
				initVersionAndWeek(request, response);
			}else if("mailToBoss".equals(action)){
				sendMailToBoss(request, response);
			}else if("mailToStaff".equals(action)){
				sendMailToStaff(request, response);
			}
		}
	}
	/**
	 * 把加班请假记录作为附件发送给老大
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void sendMailToBoss(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String version = bo.nowVersionNumber();
		String week = bo.nowWeekNumberOfVersion(version);
		String attachFilePath = bo.generateExcel(request, response);
		String from = Cache.getProperty("mail", "chris.zhang");
		String to = Cache.getProperty("mail", "haidong.han");
		String subject = version+"版本第"+week+"周加班请假名单";
		StringBuffer content = new StringBuffer();
		content.append("详见附件，谢谢！\r\n\r\n");
		content.append("邮件自动发送...");
		mailBO.sendMail(attachFilePath, from, to, subject, content.toString());
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("发送成功！");
	}
	/**
	 * 发送给全组
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void sendMailToStaff(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String version = bo.nowVersionNumber();
		String week = bo.nowWeekNumberOfVersion(version);
		String from = Cache.getProperty("mail", "chris.zhang");
		String to = Cache.getProperty("mail", "ebao.core");
		String subject = version+"版本第"+week+"周加班请假情况统计";
		StringBuffer content = new StringBuffer();
		content.append("大家好：<br>");
		content.append("今晚加班请假的同事请发邮件至我邮箱，格式为：");
		content.append("<br>");
		content.append("姓名：");
		content.append("<br>");
		content.append("请假原因：");
		content.append("<br>");
		content.append("<br>");
		mailBO.sendMail(null, from, to, subject, content.toString());
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("发送成功！");
	}

	/**
	 * 初始化当前版本号和周
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void initVersionAndWeek(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String version = bo.nowVersionNumber();
		String week = bo.nowWeekNumberOfVersion(version);
		Map map = new HashMap();
		map.put("version", version);
		map.put("week", week);
		String jsonStr = ToolUtil.mapToJson(map);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonStr);
	}
	/**
	 * 导出请假记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void exportOverTimeLeaveRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		bo.exportOverTimeLeaveRecord(request, response); 
	}

	private void addOverTimeLeaveRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String reason = request.getParameter("reason");
		bo.addOverTimeLeaveRecord(name, reason);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("保存成功！");
	}
	/**
	 * 页面跳转
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	/**
	 * 是否有权限
	 * @param request
	 * @param response
	 * @return true-有权限 false-无权限
	 * @throws Exception
	 */
	private boolean isAccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ip = disposal.getUserIP(request);
		String authority = Cache.getProperty("authority", "hostlist");
		List<String> authList = ToolUtil.stringsToList(authority, ",");
		if(!authList.contains(ip)){//不包含，没权限
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write("你没有权限进行此操作！！");
			return false;//无权限
		}
		return true;//有权限
		
	}
	/**
	 * 开关
	 * @return
	 */
	private boolean isAccessCtrlOn(){
		return true;
	}
	//ebao_version_plan
}
