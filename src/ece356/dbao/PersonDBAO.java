package ece356.dbao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ece356.entity.Patient;
import ece356.entity.Person;

public class PersonDBAO {
	public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
	private static final String user_id = "gdogrady";
	public static final String user = "user_" + user_id;
	public static final String pwd = "user_" + user_id;
	private static final Logger logger = Logger.getLogger("ece356");

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

	public static Person login(String username, String password)
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			// do they exist?
			// is it the right password?
			// ! TODO: sanitize input
			String query = String.format("SELECT * FROM person "
					+ "WHERE person.username = '%s' AND "
					+ "		person.password = '%s' LIMIT 1", username, password);
			logger.log(Level.INFO, query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				Person e = new Person(resultSet.getInt("id"),
						resultSet.getString("NameLast"),
						resultSet.getString("NameFirst"),
						resultSet.getString("Phone"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("street"),
						resultSet.getString("City"),
						resultSet.getString("Province"),
						resultSet.getString("PostalCode"),
						resultSet.getInt("RoleID"));
				return e;
			}
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<Patient> searchPatient(int id, int person_id,
			int default_doc, String health_card, int sin, String current_health)
			throws ClassNotFoundException, SQLException {

		Connection con = null;
		Statement stmt = null;
		ArrayList ret = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			String query = String.format("SELECT * " + "FROM patients "
					+ "WHERE (%d = 0 OR patients.id = %d) AND "
					+ "		(%d = 0 OR patients.PersonID = %d) AND "
					+ "		(%d = 0 OR patients.DefaultDoc = %d) AND "
					+ "		('%s' LIKE '' OR patients.HealthCard LIKE '%s') AND "
					+ "		(%d = 0 OR patients.SIN = %d) AND "
					+ "		('%s' LIKE '' OR patients.CurrentHealth LIKE '%s')",
					id, id, person_id, person_id, default_doc, default_doc,
					health_card, health_card, sin, sin, current_health,
					current_health);
			logger.log(Level.INFO, query);

			ResultSet resultSet = stmt.executeQuery(query);
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
}
