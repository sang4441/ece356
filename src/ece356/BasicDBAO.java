package ece356;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicDBAO {
	public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
	public static final String user = "user_gdogrady";
	public static final String pwd = "user_gdogrady";

	public static void testConnection() throws ClassNotFoundException,
			SQLException {
		Statement stmt;
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pwd);
		stmt = con.createStatement();
		con.close();
	}
}
