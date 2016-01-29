package com.ebao.life.gavin.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryCallback{
 
	public abstract Object disposeResultSet(ResultSet rs) throws SQLException;

}
