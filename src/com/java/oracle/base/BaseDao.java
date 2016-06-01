package com.java.oracle.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.oracle.util.JDBC;

public class BaseDao<T> implements DateDao<T> {
	private PreparedStatement preStatement;

	@Override
	public ResultSet find(Serializable id, String sql) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		preStatement.setObject(1, id);
		ResultSet resultset = preStatement.executeQuery();
		return resultset;
	}

	@Override
	public int add(String sql, Object... objects) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		for (int i = 0; i < objects.length; i++) {
			preStatement.setObject(i + 1, objects[i]);
		}
		int flag = preStatement.executeUpdate();
		return flag;
	}

	@Override
	public int delete(Serializable id, String sql) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		preStatement.setObject(1, id);
		int flag = preStatement.executeUpdate();
		return flag;
	}

	@Override
	public int update(String sql, Object... objects) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		for (int i = 0; i < objects.length; i++) {
			preStatement.setObject(i + 1, objects[i]);
		}
		int flag = preStatement.executeUpdate();
		return flag;
	}

	@Override
	public ResultSet Query(String sql) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		return preStatement.executeQuery();
	}

	@Override
	public ResultSet findMore(String sql, Object... objects) throws SQLException {
		preStatement = JDBC.getPreparedStatement(sql);
		for (int i = 0; i < objects.length; i++) {
			preStatement.setObject(i + 1, objects[i]);
		}
		return preStatement.executeQuery();
	}

	@Override
	public ResultSet pageQuery(String sqls, int pageSize, int page, Object... objects) throws SQLException {
		if (sqls == null || "".equals(sqls)) {
			StringBuilder sql = new StringBuilder("select ");
			@SuppressWarnings("rawtypes")
			Class c = objects[0].getClass();
			Field[] fd = c.getDeclaredFields();
			String id = fd[0].getName();
			for (int i = 0; i < fd.length - 1; i++) {
				sql.append(fd[i].getName()).append(",");
			}
			sql.append(fd[fd.length - 1].getName()).append(" from (select ");
			for (int i = 0; i < fd.length; i++) {
				sql.append(fd[i].getName()).append(",");
			}
			String[] tableName = c.getName().toLowerCase().split("\\.");
			sql.append(" rownum rn from ").append(tableName[tableName.length - 1])
					.append(" order by " + id + " asc) where rn between ? and ?");
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql.toString());
			int start = (page - 1) * pageSize + 1;
			int end = page * pageSize;
			try {
				prestatement.setInt(1, start);
				prestatement.setInt(2, end);
				ResultSet result = prestatement.executeQuery();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			PreparedStatement prestatement = JDBC.getPreparedStatement(sqls);
			int start = (page - 1) * pageSize + 1;
			int end = page * pageSize;
			try {
				for (int i = 0; i < objects.length; i++) {
					prestatement.setObject(i + 1, objects[i]);
				}
				prestatement.setObject(2, start);
				prestatement.setObject(3, end);
				ResultSet result = prestatement.executeQuery();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

}
