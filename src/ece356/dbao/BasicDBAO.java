package ece356.dbao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ece356.entity.Patient;

public class BasicDBAO {
	public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
	private static final String user_id = "gdogrady";
	public static final String user = "user_" + user_id;
	public static final String pwd = "user_" + user_id;

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pwd);
		Statement stmt = null;
		try {
			con.createStatement();
			stmt = con.createStatement();
			stmt.execute("USE ece356db_" + user_id);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return con;
	}

	public static ArrayList<Patient> getAllPatients()
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<Patient> ret = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM patients");
			ret = new ArrayList<Patient>();
			while (resultSet.next()) {
				Patient e = new Patient(resultSet.getInt("id"),
						resultSet.getInt("PersonID"),
						resultSet.getInt("DefaultDoc"),
						resultSet.getString("HealthCard"),
						resultSet.getInt("SIN"),
						resultSet.getString("CurrentHealth"));
				ret.add(e);
			}
			return ret;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	// public static ArrayList<Patient> query8(int empID, String empName,
	// String job, int deptID, int salary) throws ClassNotFoundException,
	// SQLException {
	//
	// Connection con = null;
	// Statement stmt = null;
	// ArrayList ret = null;
	// try {
	// con = getConnection();
	// stmt = con.createStatement();
	// ResultSet resultSet = stmt
	// .executeQuery(String
	// .format("SELECT * "
	// + "FROM Employee "
	// + "WHERE (%d = -1 OR Employee.empID = %d) AND "
	// + "		('%s' LIKE '' OR Employee.empName LIKE '%s') AND "
	// + "		('%s' LIKE '' OR Employee.job LIKE '%s') AND "
	// + "		(%d = -1 OR Employee.deptID = %d) AND "
	// + "		(%d = -1 OR Employee.salary = %d)",
	// empID, empID, empName, empName, job, job,
	// deptID, deptID, salary, salary));
	// ret = new ArrayList<Patient>();
	// while (resultSet.next()) {
	// Patient e = new Patient(resultSet.getInt("empID"),
	// resultSet.getString("empName"),
	// resultSet.getString("job"), resultSet.getInt("deptID"),
	// resultSet.getInt("salary"));
	// ret.add(e);
	// }
	// return ret;
	// } finally {
	// if (stmt != null) {
	// stmt.close();
	// }
	// if (con != null) {
	// con.close();
	// }
	// }
	// }
}
