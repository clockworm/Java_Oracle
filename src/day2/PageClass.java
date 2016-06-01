package day2;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.oracle.util.JDBC;

public abstract class PageClass<T> {
	public ResultSet paging(int count, int page) {
		StringBuilder sql = new StringBuilder("select ");
		@SuppressWarnings("rawtypes")
		Class<? extends PageClass> c = this.getClass();
		Field[] fd = c.getDeclaredFields();
 		String id = fd[0].getName();
		for (int i = 0; i < fd.length; i++) {
			sql = i == fd.length - 1 ? sql.append(fd[i].getName()) : sql.append(fd[i].getName()).append(",");
		}
		sql.append(" from (select ");
		for (int i = 0; i < fd.length; i++) {
			sql.append(fd[i].getName()).append(",");
		}
		String tableName = c.getSimpleName().toLowerCase();
		sql.append(" rownum rn from ").append(tableName).append(" order by " + id + ") where rn between ? and ?");
		System.out.println(sql);
		PreparedStatement prestatement = JDBC.getPreparedStatement(sql.toString());
		int start = (page - 1) * count + 1;
		int end = page * count;
		try {
			prestatement.setInt(1, start);
			prestatement.setInt(2, end);
			ResultSet result = prestatement.executeQuery();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
