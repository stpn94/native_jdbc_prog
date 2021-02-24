package native_jdbc_programing.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

public class JdbcUtil {
/*	public static void main(String[] args) {
		Connection con = JdbcUtil.getConnection();
		System.out.println(con);
	}*/
	public static Connection getConnection() {
		String propertiesPath = "db.properties";
		Connection con = null;
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) {
			Properties prop = new Properties();
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			/*
			 * System.out.println("prop > " + prop); for( Entry<Object, Object> e :
			 * prop.entrySet()) { System.out.printf("%s -> %s%n",e.getKey(),e.getValue()); }
			 * for(Object key : prop.keySet()) { System.out.print(key+">>>");
			 * System.out.println(prop.get(key)); };
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}	
}