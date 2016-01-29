package com.ebao.life.gavin.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ToolUtil {
	
	public static List<String> stringsToList(String strs, String separator){
		if(strs == null){
			return null;
		}
		String[] strArr = strs.split(separator);
		List<String> list = Arrays.asList(strArr);
		return list;
	}
	
	public static String nullToEmpty(String s){
		return s == null?"":s;
	}
	
	public static boolean isEmpty(String s){
		return s==null||"".equals(s);
	}
	
	public static String format(Object number){
		NumberFormat nf = NumberFormat.getInstance();
		return null==number?null:nf.format(number);
	}
	
	public static String formatDate(Date src, String format){
		String formatDate = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			formatDate = sdf.format(src);
		}catch(Exception e){
			// date format error!
		}
		return formatDate;
		
	}
	
	/**
	 * 获取Throwable的Stack Trace
	 *
	 * @param e Throwable
	 * @return Stack Trace
	 */
	public static String getExceptionMsg(Throwable e) {
		String result = null;
		StringWriter out = new StringWriter();
		try {
			e.printStackTrace(new PrintWriter(out));
			result = out.toString();
		} catch(Exception e1) {
		} finally {
			try {
				out.close();
			} catch(Exception e2) {
			}
		}
		return result;
	}
	
//	public static VersionPlanVO versionPlan(String year) throws Exception{
//		
//	}
	
	public static Object listOne(List list)throws Exception{
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	/**
	 * 获取指定日期在的周数
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static int weekNumberOfDate(Date input) throws Exception{
//		String format = "yyyyMMdd";
//		SimpleDateFormat df = new SimpleDateFormat(format);
//		Date date = df.parse(input);
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
	/**
	 * map转换成json，key和value分别对应
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		if(map.isEmpty()){
			return null;
		}
		sb.append("{");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append("").append(entry.getKey()).append("").append(":").append("").append(entry.getValue()).append("");
			sb.append(",");
		}
		String result = sb.substring(0, sb.length()-1);
		result += "}";
		return result;
	}
	
	/**
	 * 分解URI，从URI中分离参数
	 * URI 例：/company/log/xx.log
	 * @param uri URI字串
	 * @param index 地址中的位置
	 * @return
	 */
	public static String getUriParameter(String uri, int index) {
		String para = "";
		if(!isEmpty(uri)){
			String[] uriParas = uri.split("/");
			if(uriParas.length > index){
				para = uriParas[index];
			}
		}
		return para;
	}
}
