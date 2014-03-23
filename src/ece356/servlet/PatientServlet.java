package ece356.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ece356.dbao.PatientDBAO;
import ece356.entity.Patient;
import ece356.entity.Visit;
import ece356.helpers.ServletHelper;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/Patient/*")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PatientServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	protected void getPatient(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		int id = Integer.parseInt(request.getPathInfo().split("/")[1]);
		Patient patient = PatientDBAO.getByID(id);
		request.setAttribute("patient", patient);
		ArrayList<Visit> visits = PatientDBAO.getVisitsByPatientID(id);
		request.setAttribute("visits", visits);

		// visits, "visits");
		ServletHelper.forward(request, response, "/patient/index.jsp");

	}

	protected void searchPatients(HttpServletRequest request,
			HttpServletResponse response) throws SQLException,
			ClassNotFoundException, IOException, ServletException {
		int id = ServletHelper.getInt(request, "id");
		int person_id = ServletHelper.getInt(request, "person-id");
		int default_doc = ServletHelper.getInt(request, "default-doc");
		String health_card = ServletHelper.getString(request, "health-card");
		int sin = ServletHelper.getInt(request, "sin");
		String current_health = ServletHelper.getString(request,
				"current-health");

		request.setAttribute("search", new Patient(id, person_id, default_doc,
				health_card, sin, current_health));

		ArrayList<Patient> patients = PatientDBAO.searchPatient(id, person_id,
				default_doc, health_card, sin, current_health);
		request.setAttribute("patientList", patients);
		ServletHelper.forward(request, response, "/patients.jsp");

	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url;
		try {
			ServletHelper.logParameters(request);
			String pathInfo = request.getPathInfo() == null ? "" : request
					.getPathInfo();
			log(pathInfo);
			// TODO: better url routing
			// parse url path
			String[] path = pathInfo.split("/");
			log(String.format("%d", path.length));
			if (path.length > 1) {
				if (path.length > 2) {
					searchPatients(request, response);
				} else {
					getPatient(request, response);
				}
			} else {
				searchPatients(request, response);
			}

			return;

		} catch (ClassNotFoundException ex) {
			request.setAttribute("exception", ex);
			url = "/error.jsp";
		} catch (SQLException ex) {
			request.setAttribute("exception", ex);
			url = "/error.jsp";
		} catch (Exception ex) {
			request.setAttribute("exception", ex);
			url = "/error.jsp";
		}
		ServletHelper.forward(request, response, url);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
