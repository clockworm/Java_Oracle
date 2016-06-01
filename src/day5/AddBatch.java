package day5;

import java.sql.PreparedStatement;

import jdbc.oracle.util.JDBC;

public class AddBatch {
	public static void main(String[] args) {
		try {
			PreparedStatement prestatement = JDBC.getPreparedStatement("insert into student values(?,?,?,?)");
			for (int i = 20; i < 1000; i++) {
				prestatement.setInt(1, i);
				prestatement.setString(2, "张三" + i);
				prestatement.setInt(3, i + 10);
				prestatement.setString(4, "男");
				prestatement.addBatch();
				if (i % 10 == 0) {
					prestatement.executeBatch();
					prestatement.clearBatch();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
			JDBC.closeConnection();
		}
	}
}
