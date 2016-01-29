package com.ebao.life.gavin.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.JSONObject;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ebao.life.bean.pub.common.Tools;
import com.ebao.life.gavin.bo.NoteBO;
import com.ebao.life.gavin.bo.StaffBO;
import com.ebao.life.gavin.util.ToolUtil;
import com.ebao.life.gavin.vo.NoteVO;
import com.ebao.life.gavin.vo.StaffVO;

public class ProjectDisposal {
	private static Logger logger = Logger.getLogger(ProjectDisposal.class);  
	private NoteBO noteBO = new NoteBO();
	private StaffBO staffBO = new StaffBO();

	public String formatXML(String reqXML) throws Exception {
        JSONObject jo = new JSONObject();
        
		String reXML = reqXML;
		//reqXML = getXML();
		try{
			reXML = doc2String(string2Doc(reqXML));
			// reXML = new String(reXML.getBytes(),"UTF-8"); // 需转码
			jo.put("reXML", reXML);
			
		} catch(Exception e){
			logger.info(e.getMessage());
			String reErr = parseXMLExcpetion(e.getMessage());
			if(reErr != null){
				reErr =   "格式化错误：" + reErr;
			}else{
				//e.printStackTrace(System.out);
				//StringWriter sw = new StringWriter();
				//PrintWriter pw = new PrintWriter(sw);
				//e.printStackTrace(pw);
				//reXML = reXML + "\n\n" + sw.toString();
				reErr = "格式化错误：" + e.getMessage();
			}
			jo.put("msg", reErr);
		}
		//logger.info("========Return XML========");
		//logger.info(reXML);

		//logger.info(jo.toString());
		return jo.toString();
	}
	
	private static String parseXMLExcpetion(String errMessage){
		String reErr = null;
		String tagHook = "the matching end-tag";
		String lineHook = "Error on line ";
		if(errMessage.contains(tagHook)){
			reErr = "";
			int lineIndex = errMessage.indexOf(lineHook);
			if(lineIndex > -1) {
				reErr += "第" + errMessage.substring(lineIndex+lineHook.length(), errMessage.indexOf(":"))+"行有错误：";
			}
			int tagIndex = errMessage.indexOf("</");
			String tagName = errMessage.substring(tagIndex+2,errMessage.lastIndexOf(">"));
			reErr += "标签<" + tagName + ">没有正确闭合";
		}
		return reErr;
	}
	
	private static String getXML()  throws Exception {
        String encoding="UTF-8";
        File file=new File("D:/sample.xml");
        String s = "";
        logger.info("===========源文件===========");
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	s+=lineTxt;
                logger.info(lineTxt);
            }
            read.close();
        }
        return s;
        
	}
	
	public static void main(String[] args) throws Exception{
		ProjectDisposal p = new ProjectDisposal();
		p.formatXML("");
	}
	
    /**   
     * 字符串转换为DOCUMENT   
     *    
     * @param xmlStr 字符串   
     * @return doc JDOM的Document   
     * @throws Exception   
     */    
    public static Document string2Doc(String xmlStr) throws Exception {     
    	
        java.io.Reader in = new StringReader(xmlStr);
        //InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(xmlStr.getBytes()), "UTF-8");
        Document doc = (new SAXBuilder()).build(in);            
        return doc;     
    }
    
    /**   
     * Document转换为字符串   
     *    
     * @param xmlFilePath XML文件路径   
     * @return xmlStr 字符串   
     * @throws Exception   
     */    
    public static String doc2String(Document doc) throws Exception {     
        Format format = Format.getPrettyFormat();     
        format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题     
        XMLOutputter xmlout = new XMLOutputter(format);     
        StringWriter sw = new StringWriter();
        //ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlout.output(doc, sw);    
        return sw.toString();     
        //return xmlout.output(doc);
    } 
    
	public String getUserIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public String[] getIPAndName(HttpServletRequest request) throws Exception {
		String ip = getUserIP(request);
		StaffVO staff = staffBO.retriveStaffByEmpIP(ip);
		String empName = staff == null ? "未获取" : staff.getEmpName();
		String[] ipAndName = {ip,empName};
		
		return ipAndName;
	}

	public String saveNote(String noteId, String noteTitle, String noteContent, String userIP) throws Exception {
		int rowCnt = 0;
		String flag = "N"; 
		if(!ToolUtil.isEmpty(noteId) ){
			NoteVO vo = new NoteVO();
			vo.setNoteId(noteId);
			vo.setNoteTitle(noteTitle);
			vo.setSaverIp(userIP);
			vo.setNoteContent(noteContent);
			rowCnt = noteBO.saveNote(vo);
		}
		flag = rowCnt>0?"Y":"N";
		return flag; // if success
	}

	public String hessianQuest(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
		String contents_req = Tools.toGB(request.getParameter("contents_req"));
		String hessian_url = request.getParameter("hessian_url");
		String hessian_interface = request.getParameter("hessian_interface");
		String hessian_method = request.getParameter("hessian_method");
		logger.info("=====================Start invoke hessian interface at " + fmt.format(new Date()) +" =====================");
		logger.info("======== hessian_url ========" + hessian_url);
		logger.info("======== hessian_interface ========" + hessian_interface);
		logger.info("======== hessian_method ========" + hessian_method);
		logger.info("======== contents_req start ========" + contents_req);
		logger.info("======== contents_req end ========");
		// Element request_e = ElementProc.ElementRoot(contents_req);
		Map dataMap = new HashMap();
		String reString = "";
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			StringReader stringIn = new StringReader(contents_req);
			Document document = saxBuilder.build(stringIn);
			dataMap = Dom2Map(document);
			logger.info("======== Request Map start ========\n" + mapToString(dataMap));
			logger.info("======== Request Map end ========");

			HessianProxyFactory factory = new HessianProxyFactory();
			Object hessianInterface = null;
			Class clz = Class.forName(hessian_interface);
			hessianInterface = factory.create(clz, hessian_url);
			Method method = hessianInterface.getClass().getMethod(hessian_method, new Class[] { Map.class });
			Map reMap = (HashMap) method.invoke(hessianInterface, new Object[] { dataMap });
			reString =  mapToString(reMap);
		} catch (Exception e) {
			reString = getExceptionMsg(e);
			logger.info("occur exception =============== " + e.toString());
			
		} finally {
			//request.setAttribute("contents_res", reString.replace("},", "},\r\n"));
			reString = reString.replace("},", "},\r\n");
			
			logger.info("======== Return Message start ========\n " + reString);
			logger.info("======== Return Message end ========");
			logger.info("=====================End invoke hessian interface at " + fmt.format(new Date()) +" =====================\n\n");

		}
		return reString;
	}
	
	public static Map<String, Object> Dom2Map(Document doc) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();

		List<Element> children = root.getChildren();
		for (Element child : children) {
			map.put(child.getName(), getElementValue(map, child));
		}

		return map;
	}
	
	/**
	 * 通过递归获得节点值
	 * 默认 type="Map" 节点下的子节点的标签名和值对应map中的key和value
	 * 默认 type="List" 节点下的子节点都放入一个map中，List中的多个值对应多个同名type="List"节点
	 * 
	 * @param map 上层节点map
	 * @param node  子节点
	 * @return
	 * @throws ParseException
	 */
	private static Object getElementValue(Map map, Element node) throws ParseException {
		String type = node.getAttributeValue("type");
		String nodeText = node.getText();
		Object o = null;
		if ("Map".equalsIgnoreCase(type)) {
			Map subMap = new HashMap();
			for (Element mapChild : (List<Element>) node.getChildren()) {
				subMap.put(mapChild.getName(), getElementValue(subMap, mapChild));
			}
			o = subMap;
		} else if ("List".equalsIgnoreCase(type)) {
			String listKey = node.getName();
			List subList;
			if (map.containsKey(listKey)) {
				subList = (List) map.get(listKey);
			} else {
				subList = new ArrayList();
				map.put(listKey, subList);
			}
			Map listMap = new HashMap();
			for (Element listChild : (List<Element>) node.getChildren()) {
				listMap.put(listChild.getName(), getElementValue(listMap, listChild));
			}
			subList.add(listMap);
			o = subList;
		} else if ("Long".equalsIgnoreCase(type)) {
			o = Long.valueOf(nodeText);
		} else if ("Date".equalsIgnoreCase(type)) {
			o = ToolUtil.isEmpty(nodeText) ? null : Tools.toDate(nodeText);
		} else if ("Int".equalsIgnoreCase(type)) {
			o = ToolUtil.isEmpty(nodeText) ? null : Integer.valueOf(nodeText);
		} else if ("BigDecimal".equalsIgnoreCase(type)) {
			o = ToolUtil.isEmpty(nodeText) ? null : new BigDecimal(nodeText);
		} else if ("String[]".equalsIgnoreCase(type)) {
			o = ToolUtil.isEmpty(nodeText) ? null : nodeText.split("\\|");
		} else {
			o = nodeText;
		}
		return o;
	}

	
	private static String mapToString(Map map){
		String mapStr = "{\n";
		for(Object key: map.keySet()){
			mapStr = mapStr + key + "=" + map.get(key) + ",\n";
		}
		if(mapStr.endsWith(",\n"))
			mapStr = mapStr.substring(0, mapStr.length()-2);
		mapStr += "\n}";
		return mapStr;
	}
	
	public String getExceptionMsg(Throwable e) {
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

	public void logUserQuest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		StaffBO staffBO = new StaffBO();
		String userIP = getUserIP(request);
		StaffVO staff = staffBO.retriveStaffByEmpIP(userIP);
		String empName = staff==null?"未获取":staff.getEmpName();
		request.setAttribute("empName", empName); 
		
		String page = request.getParameter("page");
		String action = request.getParameter("sAction"); 
		
		logger.info("IP: " + userIP + ", Name: " + empName + ", page: " + page + ", sAction: " + action);
		
		// TODO 记录日志
		// 表结构 logId page action userip username insertime 
		
	}

}
