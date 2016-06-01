package day5;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import jdbc.oracle.util.JDBC;

public class DateTime {
	public static void main(String[] args) {
		PreparedStatement prestatement = JDBC.getPreparedStatement("insert into datetime values(?)");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M月-yyyy");
		Date date = new Date();
		String s = sdf.format(date.getTime());
		System.out.println(s);
		try {
//			prestatement.setString(1, s);
			prestatement.setString(1, "03-3月-1989");
			prestatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
