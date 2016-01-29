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
 * 缓存类
 * @author chris.zhang
 *
 */
public class Cache {
	/**
	 * 获得Logger
	 */
	private Logger logger = Logger.getLogger(Cache.class);
	/**
	 * 配置文件所在路径
	 */
	private static String localUrl = getConfPath();;
	/**
	 * 缓存容器
	 */
	private Map<Object, Object> cacheMap;
	/**
	 * 单例缓存
	 */
	private static Cache cache = new Cache();
	/**
	 * 初始化方法
	 */
	private Cache(){
		logger.info("东风5B洲际弹道导弹加载中...");
		this.cacheMap = initProperties();
		logger.info("东风5B洲际弹道导弹加载完成...");
	}
	/**
	 * 获得单例缓存
	 * @return
	 */
	public static Cache getInstance(){
		return cache;
	}
	/**
	 * 存储缓存元素
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value){
		this.cacheMap.put(key, value);
	}
	/**
	 * 清空缓存
	 */
	public void clear(){
		this.cacheMap.clear();
	}
	/**
	 * 获取配置文件
	 * @param name 配置文件名
	 * @return
	 */
	public static Properties getProperties(String name){
		String key = name + ".properties";
		return (Properties)Cache.getInstance().cacheMap.get(key);
	}
	/**
	 * 获得指定文件的属性
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
	 * 获取缓存元素
	 * @param key
	 * @return
	 */
	public Object get(Object key){
		return this.cacheMap.get(key);
	}
	/**
	 * 初始化properties属性
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
	 * 获得配置文件所在路径
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
