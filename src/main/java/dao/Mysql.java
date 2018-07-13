package dao;

public class Mysql {
	private static final String dburl = "jdbc:mysql://localhost:3306/pjt2?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
	private static final String dbUser = "ksmoon";
	private static final String dbpasswd = "1234";

	public Mysql() {

	}

	public static String getDburl() {
		return dburl;
	}

	public static String getDbuser() {
		return dbUser;
	}

	public static String getDbpasswd() {
		return dbpasswd;
	}

}
