package jdbc.oracle.util;

import java.util.Properties;

public class ReadUtil {
	private static Properties pro = new Properties();
	static {
		try {
			pro.load(ReadUtil.class.getResourceAsStream("/datebase.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMessage(String propertiesName) {
		return pro.getProperty(propertiesName);
	}
}
