package com.java.oracle.base;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DateDao<T> {
	public int add(String sql, Object... objects) throws SQLException;

	public int delete(Serializable id, String sql) throws SQLException;

	public int update(String sql, Object... objects) throws SQLException;

	public ResultSet find(Serializable id, String sql) throws SQLException;

	public ResultSet Query(String sql) throws SQLException;

	public ResultSet findMore(String sql, Object... objects) throws SQLException;

	public ResultSet pageQuery(String sql, int pageSize, int page, Object... objects) throws SQLException;

}
