package day3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.oracle.util.JDBC;

public class Jdbc_BookUser_demo_1 {
	public static void main(String[] args) {
		BookUser bookUser = new BookUser();
		BookUserDao bookuserDao = new BookUserDao();
		Connection con = null;
		try {
			con = JDBC.getConnection();
			con.setAutoCommit(false);
			bookUser.setId(4);
			bookuserDao.delete(bookUser);
			bookUser.setId(6);
			bookuserDao.delete(bookUser);
			ArrayList<BookUser> bookUsers = bookuserDao.pageQuery(16, 1);
			for (BookUser bookUser2 : bookUsers) {
				System.out.println(bookUser2);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBC.closeConnection();
		}
	}
}
