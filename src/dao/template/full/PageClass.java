package dao.template.full;

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
