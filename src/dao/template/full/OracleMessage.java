package dao.template.full;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jdbc.oracle.util.JDBC;

public class OracleMessage {
	public static void main(String[] args) {
		try {
			Connection con = JDBC.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			System.out.println(meta.getDatabaseProductName());
			System.out.println(meta.getDatabaseProductVersion());

			PreparedStatement prestatement = JDBC.getPreparedStatement("select * from student order by id");
			ResultSet res = prestatement.executeQuery();
			ResultSetMetaData metaData = res.getMetaData();
			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++) {
				System.out.print(metaData.getColumnName(i));
				System.out.print(" " + metaData.getColumnTypeName(i));
				System.out.print(" " + metaData.getColumnDisplaySize(i));
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
