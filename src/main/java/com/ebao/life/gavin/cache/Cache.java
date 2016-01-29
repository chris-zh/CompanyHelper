package com.ebao.life.gavin.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import com.ebao.life.gavin.util.ToolUtil;
import common.Logger;
/**
 * ������
 * @author chris.zhang
 *
 */
public class Cache {
	/**
	 * ���Logger
	 */
	private Logger logger = Logger.getLogger(Cache.class);
	/**
	 * �����ļ�����·��
	 */
	private static String localUrl = getConfPath();;
	/**
	 * ��������
	 */
	private Map<Object, Object> cacheMap;
	/**
	 * ��������
	 */
	private static Cache cache = new Cache();
	/**
	 * ��ʼ������
	 */
	private Cache(){
		logger.info("����5B�޼ʵ�������������...");
		this.cacheMap = initProperties();
		logger.info("����5B�޼ʵ��������������...");
	}
	/**
	 * ��õ�������
	 * @return
	 */
	public static Cache getInstance(){
		return cache;
	}
	/**
	 * �洢����Ԫ��
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value){
		this.cacheMap.put(key, value);
	}
	/**
	 * ��ջ���
	 */
	public void clear(){
		this.cacheMap.clear();
	}
	/**
	 * ��ȡ�����ļ�
	 * @param name �����ļ���
	 * @return
	 */
	public static Properties getProperties(String name){
		String key = name + ".properties";
		return (Properties)Cache.getInstance().cacheMap.get(key);
	}
	/**
	 * ���ָ���ļ�������
	 * @param fileName
	 * @param propertityName
	 * @return
	 */
	public static String getProperty(String fileName, String propertityName){
		fileName = fileName + ".properties";
		Properties prop = (Properties)(Cache.getInstance().get(fileName));
		return (String)prop.get(propertityName);
	}
	
	
	/**
	 * ��ȡ����Ԫ��
	 * @param key
	 * @return
	 */
	public Object get(Object key){
		return this.cacheMap.get(key);
	}
	/**
	 * ��ʼ��properties����
	 */
	private Map<Object, Object> initProperties(){
		File confDict = new File(localUrl);
		InputStream in = null;
		Map<Object, Object> map = new ConcurrentHashMap<Object, Object>();
		if(confDict!=null){
			File[] configFiles = confDict.listFiles();
			for(File config: configFiles){
				try {
					Properties properties = new Properties();
					String fileName = config.getName();
					in = new FileInputStream(config.getAbsolutePath());
					properties.load(in);
					map.put(fileName, properties);;
				} catch (FileNotFoundException e) {
					logger.error(ToolUtil.getExceptionMsg(e));
				}catch(IOException e){
					logger.error(ToolUtil.getExceptionMsg(e));
				}
			}
		}
		return map;
	}
	/**
	 * ��������ļ�����·��
	 * @return
	 */
	private static String getConfPath(){
		String path = Cache.class.getResource("/").getPath();
		String[] separators = {"/","\\"};
		List<String> list = Arrays.asList(separators);
		if(path!=null&&list.contains(path.substring(0,1))){
			path = path.substring(1);
		}
		return path +  "conf";
	}
	public static void main(String[] args) {
		System.out.println(Cache.localUrl);
		Cache cache = Cache.getInstance();
		Properties properties = cache.getProperties("mail");
		String authorityStr = properties.getProperty("chris.zhang");
		System.out.println(authorityStr);
//		String[] authorityArr = authorityStr.split(",");
//		List list = Arrays.asList(authorityArr);
//		System.out.println(list.size());
	}
}
