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
	 * 下载请假记录Excel
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
	 * 根据当前版本号，获取当前日期为该版本的第几周
	 */
	public String nowWeekNumberOfVersion(String versionNumber)throws Exception{
		String week = Cache.getProperty("version", "week");
		return week;
	}
	/**
	 * 根据当前日期，获取当前版本号
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
	 * 查询当前周数据
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public WritableWorkbook sheet1(WritableWorkbook wb) throws Exception{
		String version = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		String[] titles = {"姓名","请假原因"};
		String[] columns = {"APPLY_NAME","APPLY_REASON"};
		String[] firstLine = {version+"版本第"+week+"周加班请假名单"};
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
	 * 全部统计
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public WritableWorkbook sheet2(WritableWorkbook wb) throws Exception{
		String[] titles = {"姓名","次数"};
		String[] columns = {"APPLY_NAME","CN"};
		String[] firstLine = {"2016年全部加班请假次数"};
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
	 * 下载Excel
	 * @throws Exception
	 */
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		OutputStream os = response.getOutputStream();
		String version = nowVersionNumber();
		String week = nowWeekNumberOfVersion(version);
		String fileName = "2015年"+version+"版本第"+week+"周加班请假名单.xls";
		response.setContentType("aplication/vnd.ms-excel;charset=UTF-8");
		response.addHeader("Content-Disposition", "inline; filename="+ fileName);
		WritableWorkbook wb = Workbook.createWorkbook(os);
		sheet1(wb);
		sheet2(wb);
		wb.write();
		wb.close();// 关闭输入输出流.
	}
	/**
	 * 在本地生成加班请假文件
	 * @param request
	 * @param response
	 * @return 返回加班请假文件的绝对路径
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
		wb.close();// 关闭输入输出流.
		return filePath;
	}
	

}
