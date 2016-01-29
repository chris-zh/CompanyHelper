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
	 * action������ת
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
	 * �ѼӰ���ټ�¼��Ϊ�������͸��ϴ�
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
		String subject = version+"�汾��"+week+"�ܼӰ��������";
		StringBuffer content = new StringBuffer();
		content.append("���������лл��\r\n\r\n");
		content.append("�ʼ��Զ�����...");
		mailBO.sendMail(attachFilePath, from, to, subject, content.toString());
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("���ͳɹ���");
	}
	/**
	 * ���͸�ȫ��
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void sendMailToStaff(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String version = bo.nowVersionNumber();
		String week = bo.nowWeekNumberOfVersion(version);
		String from = Cache.getProperty("mail", "chris.zhang");
		String to = Cache.getProperty("mail", "ebao.core");
		String subject = version+"�汾��"+week+"�ܼӰ�������ͳ��";
		StringBuffer content = new StringBuffer();
		content.append("��Һã�<br>");
		content.append("����Ӱ���ٵ�ͬ���뷢�ʼ��������䣬��ʽΪ��");
		content.append("<br>");
		content.append("������");
		content.append("<br>");
		content.append("���ԭ��");
		content.append("<br>");
		content.append("<br>");
		mailBO.sendMail(null, from, to, subject, content.toString());
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("���ͳɹ���");
	}

	/**
	 * ��ʼ����ǰ�汾�ź���
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
	 * ������ټ�¼
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
		pw.write("����ɹ���");
	}
	/**
	 * ҳ����ת
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void pageRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	/**
	 * �Ƿ���Ȩ��
	 * @param request
	 * @param response
	 * @return true-��Ȩ�� false-��Ȩ��
	 * @throws Exception
	 */
	private boolean isAccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ip = disposal.getUserIP(request);
		String authority = Cache.getProperty("authority", "hostlist");
		List<String> authList = ToolUtil.stringsToList(authority, ",");
		if(!authList.contains(ip)){//��������ûȨ��
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write("��û��Ȩ�޽��д˲�������");
			return false;//��Ȩ��
		}
		return true;//��Ȩ��
		
	}
	/**
	 * ����
	 * @return
	 */
	private boolean isAccessCtrlOn(){
		return true;
	}
	//ebao_version_plan
}
