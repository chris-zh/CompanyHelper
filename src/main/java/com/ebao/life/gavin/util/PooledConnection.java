package com.ebao.life.gavin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 * �ڲ�ʹ�õ����ڱ������ӳ������Ӷ������
 * 
 * ��������������Ա��һ�������ݿ�����ӣ���һ����ָʾ�������Ƿ�
 * 
 * ����ʹ�õı�־��
 */

public class PooledConnection {
	
	private Connection connection = null;// ���ݿ�����

	private boolean busy; // �������Ƿ�����ʹ�õı�־��Ĭ��û������ʹ��
	
	private long holdtime = new Date().getTime(); // �����ӱ�ռ�õ�ʱ��㣬��1970-01-01�ĺ���ʱ��
	
	protected final static long timeout = 30000; //���ӳ�ʱʱ��

	// ���캯��������һ�� Connection ����һ�� PooledConnection ����

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

	// ���ش˶����е�����

	public Connection getConnection() {

		return connection;

	}

	// ���ô˶���ģ�����

	protected void setConnection(Connection connection) {

		this.connection = connection;

	}

	// ��ö��������Ƿ�æ

	protected boolean isBusy() {

		return this.busy;

	}

	// ���ö������������æ

	protected void setBusy(boolean busy) {
		// System.out.println("set to busy:"+busy);
		if(busy) this.holdtime = new Date().getTime(); //ռ������ʱ������ռ��ʱ��� 
		this.busy = busy;

	}
	
	public long getHoldTime(){
		return this.holdtime;
	}

	public void close() {
		busy = false;
	}

}