package com.ebao.life.gavin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.ebao.life.gavin.cache.Cache;
import com.ebao.life.gavin.util.DBManager;
import com.ebao.life.gavin.util.EnvEnum;

/**
 * Servlet implementation class ProjectHelper
 */
public class LoadOnStartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static Logger logger = Logger.getLogger(LoadOnStartupServlet.class);

	/**
	 * @see HttpServlet#HttpServlet() 
	 */
	public LoadOnStartupServlet() {
		super();
	}
	

	@Override
	public void init() throws ServletException {
		//初始化缓存
		Cache.getInstance();
		// 在应用启动时初始化连接池
		logger.info("服务器启动，开始启动数据库连接池");
		for(EnvEnum env: EnvEnum.values()){
			DBManager.connectPool(env);
		}
		
		
		super.init();
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
}
