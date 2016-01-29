package com.ebao.life.gavin.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import com.ebao.life.gavin.cache.Cache;
import com.ebao.life.gavin.dao.OverTimeDAO;
import com.ebao.life.gavin.util.ExcelPartFactory;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.LeaveVO;
import com.ebao.life.gavin.vo.SheetVo;
import com.ebao.life.gavin.vo.VersionPlanVO;

import common.Logger;

public class OverTimeBO {
	private Logger logger = Logger.getLogger(this.getClass());
	private OverTimeDAO dao;
	public OverTimeBO(){
		dao = new OverTimeDAO();
	}
	/**
	 * 
	 * @param name
	 * @param reason
	 * @throws Exception
	 */
	public void addOverTimeLeaveRecord(String name, String reason) throws Exception{
		LeaveVO vo = new LeaveVO();
		String version  = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		vo.setName(name);
		vo.setReason(reason);
		vo.setVersion(version);
		vo.setWeek(week);
		dao.addOverTimeLeaveRecord(vo);
	}
	/**
	 * ������ټ�¼Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void exportOverTimeLeaveRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			downloadExcel(request, response);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionMsg(e));
			throw e;
		}
	}
	/**
	 * ���ݵ�ǰ�汾�ţ���ȡ��ǰ����Ϊ�ð汾�ĵڼ���
	 */
	public String nowWeekNumberOfVersion(String versionNumber)throws Exception{
		String week = Cache.getProperty("version", "week");
		return week;
	}
	/**
	 * ���ݵ�ǰ���ڣ���ȡ��ǰ�汾��
	 * @return
	 * @throws Exception
	 */
	public String nowVersionNumber() throws Exception{
		String version = Cache.getProperty("version", "version");
		return version;
	}
	
//	public static void main(String[] args) throws Exception {
//		String start = "2015-11-30";
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date startDate = df.parse(start);
//		String now = "2015-12-16";
//		Date nowDate = df.parse(now);
//		System.out.println("startDate: "+startDate);
//		System.out.println("nowDate: "+nowDate);
//		long weekNumber = ToolUtil.weekNumberOfDate(nowDate) - ToolUtil.weekNumberOfDate(startDate);
//		System.out.println("78--"+weekNumber+1);
//		
//	}
	
	/**
	 * ��ѯ��ǰ������
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public WritableWorkbook sheet1(WritableWorkbook wb) throws Exception{
		String version = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		String[] titles = {"����","���ԭ��"};
		String[] columns = {"APPLY_NAME","APPLY_REASON"};
		String[] firstLine = {version+"�汾��"+week+"�ܼӰ��������"};
		int[] widths = {20,70};
		Map<String,String> map = new HashMap<String,String>();
		map.put("version", version);
		map.put("week", week);
		List<Map<String,String>> list = dao.leaveListForVersionAndWeek(version, week);
		SheetVo vo = new SheetVo();
		vo.setTitles(titles);
		vo.setColumns(columns);
		vo.setFirstLine(firstLine);
		vo.setWb(wb);
		vo.setWidths(widths);
		vo.setList(list);
		vo.setSheetName(firstLine[0]);
		vo.setSheetNum(1);
		ExcelPartFactory.addSheet(vo);
		return wb;
	}
	/**
	 * ȫ��ͳ��
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public WritableWorkbook sheet2(WritableWorkbook wb) throws Exception{
		String[] titles = {"����","����"};
		String[] columns = {"APPLY_NAME","CN"};
		String[] firstLine = {"2016��ȫ���Ӱ���ٴ���"};
		int[] widths = {20,20};
		List<Map<String,String>> list =  dao.allSumCount();
		SheetVo vo = new SheetVo();
		vo.setTitles(titles);
		vo.setColumns(columns);
		vo.setFirstLine(firstLine);
		vo.setWb(wb);
		vo.setWidths(widths);
		vo.setList(list);
		vo.setSheetName(firstLine[0]);
		vo.setSheetNum(2);
		ExcelPartFactory.addSheet(vo);
		return wb;
	}
	/**
	 * ����Excel
	 * @throws Exception
	 */
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		OutputStream os = response.getOutputStream();
		String version = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		String fileName = "2015��"+version+"�汾��"+week+"�ܼӰ��������.xls";
		response.setContentType("aplication/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename="+ fileName);
		WritableWorkbook wb = Workbook.createWorkbook(os);
		sheet1(wb);
		sheet2(wb);
		wb.write();
		wb.close();// �ر����������.
	}
	/**
	 * �ڱ������ɼӰ�����ļ�
	 * @param request
	 * @param response
	 * @return ���ؼӰ�����ļ��ľ���·��
	 * @throws Exception
	 */
	public String generateExcel(HttpServletRequest request, HttpServletResponse response)throws Exception{
//		OutputStream os = response.getOutputStream();
		String basePath = "C:\\tmp";
		File root = new File(basePath);
		if(!root.exists()){
			root.mkdir();
		}
		String version = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		String fileName = "leave_record"+version+week+".xls";
		String filePath = basePath+File.separator+fileName;
		File file = new File(filePath);
		OutputStream os = new FileOutputStream(file);
		response.setContentType("aplication/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename="+ fileName);
		WritableWorkbook wb = Workbook.createWorkbook(os);
		sheet1(wb);
		sheet2(wb);
		wb.write();
		wb.close();// �ر����������.
		return filePath;
	}
	

}
