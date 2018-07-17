package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DB 연결을 관리하는 클래스
 * 
 * @author 문기선
 *
 */
public class DatabaseConnection {

	private static String dbUrl = "", dbUser = "", dbPasswd = "";
	private static Connection connection = null;
	private static Properties properties = null;

	private DatabaseConnection() {

	}

	/**
	 * 현재 연결된 connection이 없으면 DB와 연결
	 * 
	 * @return DB와 연결된 connection
	 */
	public static Connection getConnection() {
		if (connection == null) {
			InputStream inputStream = DatabaseConnection.class.getResourceAsStream("/project.properties");
			properties = new Properties();
			try {
				properties.load(inputStream);
				dbUrl = properties.getProperty("dbUrl");
				dbUser = properties.getProperty("dbUser");
				dbPasswd = properties.getProperty("dbPasswd");

				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			} catch (final IOException | SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * DB와 연결 종료
	 */
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connection = null;
	}

}
