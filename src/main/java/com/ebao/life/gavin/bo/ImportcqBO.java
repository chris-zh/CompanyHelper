package com.ebao.life.gavin.bo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ebao.life.gavin.dao.ImportcqDAO;
import com.ebao.life.gavin.util.CsvWriter;
import com.ebao.life.gavin.util.ToolUtil;

import common.Logger;

public class ImportcqBO {
	private Logger logger = Logger.getLogger(this.getClass());
	private ImportcqDAO dao;
	public ImportcqBO(){
		dao = new ImportcqDAO();
	}
	/**
	 * 导出SR列表csv
	 * @param response
	 * @throws Exception
	 */
	public void downloadSR(HttpServletResponse response)throws Exception{
		List<Map<String, Object>> srList = dao.retriveSRlist();
		String displayColNames = "Project,Version,State,DuplicateSRID,Submit Date,SystemReqID,HeadLine,Description,Totally Effort,检查SRID字符数,检查Headline字符数";
		String matchColNames = "PROJECT,VERSION,STATE,DUPLICATESRID,SUBMIT_DATE,SYSTEMREQID,HEADLINE,DESCRIPTION,TOTALLY_EFFORT,SRID_COUNT,HEADLINE_COUNT";  
		String fileName = "bugbert.csv";  
		String content = CsvWriter.formatCsvData(srList, displayColNames, matchColNames);  
		try {
			CsvWriter.exportCsv(fileName, content, response);  

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionMsg(e));
		}
		
	}
}
