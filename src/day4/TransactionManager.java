package day4;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.oracle.util.JDBC;

public class TransactionManager {
	private static Connection con = null;

	public static void start() {
		try {
			con = JDBC.getConnection();
			con.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
