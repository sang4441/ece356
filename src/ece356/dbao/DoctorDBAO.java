package ece356.dbao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import ece356.entity.Doctor;
import ece356.entity.Patient;
import ece356.helpers.ServletHelper;

public class DoctorDBAO {
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

	public static ArrayList<Doctor> getAllDoctors()
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<Doctor> ret = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM doctor");
			ret = new ArrayList<Doctor>();
			while (resultSet.next()) {
				Doctor e = new Doctor(resultSet.getInt("id"),
						resultSet.getInt("PersonID"));
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

	public static Doctor getByID(int id) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		Doctor doctor = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			String query = String.format("SELECT * FROM doctor "
					+ "WHERE doctor.id = '%d' " + "LIMIT 1", id);

			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				doctor = new Doctor(resultSet.getInt("id"),
						resultSet.getInt("PersonID"));
			}

			return doctor;
		} catch (ClassNotFoundException e) {
			ServletHelper.log(e);
		} catch (Exception e) {
			ServletHelper.log(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return doctor;
	}

	public static ArrayList<Patient> getPatientsOfDoctor(int id)
			throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			String query = String
					.format("SELECT patients.* FROM patients "
							+ "INNER JOIN PatientDoctor pd ON pd.PatientID = patients.id"
							+ "WHERE PatientDoctor.DoctorID = %d", id);

			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				Patient patient = new Patient(resultSet.getInt("id"),
						resultSet.getInt("PersonID"),
						resultSet.getInt("DefaultDoc"),
						resultSet.getString("HealthCard"),
						resultSet.getInt("SIN"),
						resultSet.getString("CurrentHealth"));
				patients.add(patient);
			}

			return patients;
		} catch (ClassNotFoundException e) {
			ServletHelper.log(e);
		} catch (Exception e) {
			ServletHelper.log(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return null;
	}

	public static Doctor getDocByPersonID(int personID)
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			String query = String.format("SELECT * FROM doctor "
					+ "WHERE doctor.PersonID = %d " + "LIMIT 1", personID);

			ResultSet resultSet = stmt.executeQuery(query);
			Doctor doctor = null;
			while (resultSet.next()) {
				doctor = new Doctor(resultSet.getInt("id"),
						resultSet.getInt("PersonID"));
			}

			return doctor;
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
