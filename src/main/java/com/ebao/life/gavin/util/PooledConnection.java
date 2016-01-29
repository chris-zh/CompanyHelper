package com.ebao.life.gavin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 * 内部使用的用于保存连接池中连接对象的类
 * 
 * 此类中有两个成员，一个是数据库的连接，另一个是指示此连接是否
 * 
 * 正在使用的标志。
 */

public class PooledConnection {
	
	private Connection connection = null;// 数据库连接

	private boolean busy; // 此连接是否正在使用的标志，默认没有正在使用
	
	private long holdtime = new Date().getTime(); // 该连接被占用的时间点，距1970-01-01的毫秒时间
	
	protected final static long timeout = 30000; //连接超时时间

	// 构造函数，根据一个 Connection 构告一个 PooledConnection 对象

	protected PooledConnection(Connection connection) {

		this.connection = connection;

	}

	public PreparedStatement prepareStatement(String sql) throws SQLException{
		return connection.prepareStatement(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}

	public int executeUpdate(String sql) throws SQLException {
		return connection.createStatement().executeUpdate(sql);
	}

	// 返回此对象中的连接

	public Connection getConnection() {

		return connection;

	}

	// 设置此对象的，连接

	protected void setConnection(Connection connection) {

		this.connection = connection;

	}

	// 获得对象连接是否忙

	protected boolean isBusy() {

		return this.busy;

	}

	// 设置对象的连接正在忙

	protected void setBusy(boolean busy) {
		// System.out.println("set to busy:"+busy);
		if(busy) this.holdtime = new Date().getTime(); //占用连接时，更新占用时间点 
		this.busy = busy;

	}
	
	public long getHoldTime(){
		return this.holdtime;
	}

	public void close() {
		busy = false;
	}

}