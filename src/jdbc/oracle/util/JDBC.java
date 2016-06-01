package jdbc.oracle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private static Connection con;
	private static Statement state;
	private static PreparedStatement prestate;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String dirverClass = ReadUtil.getMessage("driverClass");
		String url = ReadUtil.getMessage("url");
		String user = ReadUtil.getMessage("user");
		String pass = ReadUtil.getMessage("pass");
		if (tl.get() == null) {
			Class.forName(dirverClass);
			con = DriverManager.getConnection(url, user, pass);
			tl.set(con);
		}
		return con = tl.get();
	}

	public static Statement getStatement() {
		try {
			getConnection();
			state = con.createStatement();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			getConnection();
			prestate = con.prepareStatement(sql);
			return prestate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void close() {
		try {
			if (prestate != null)
				prestate.close();
			if (state != null)
				state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
