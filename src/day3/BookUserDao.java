package day3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.oracle.util.JDBC;

public class BookUserDao implements Dao<BookUser> {

	@Override
	public int add(BookUser bookUser) {
		return 0;
	}

	@Override
	public int delete(BookUser bookUser) {
		try {
			String sql = "delete bookuser where id = ?";
			PreparedStatement prestatement = JDBC.getPreparedStatement(sql);
			prestatement.setInt(1, bookUser.getId());
			int flag = prestatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JDBC.close();
		}
	}

	@Override
	public int update(BookUser bookUser) {
		return 0;
	}

	@Override
	public BookUser find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BookUser> findMore(BookUser bookUser) {
		return null;
	}

	@Override
	public ArrayList<BookUser> query() {
		return null;
	}

	@Override
	public ArrayList<BookUser> pageQuery(int count, int page) {
		ResultSet result = new BookUser().paging(count, page);
		ArrayList<BookUser> bookusers = new ArrayList<BookUser>();
		try {
			while (result.next()) {
				BookUser bookuser = new BookUser();
				bookuser.setId(result.getInt(1));
				bookuser.setUsername(result.getString(2));
				bookuser.setUserpass(result.getString(3));
				bookuser.setEmail(result.getString(4));
				bookusers.add(bookuser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close();
		}
		return bookusers;
	}
}
