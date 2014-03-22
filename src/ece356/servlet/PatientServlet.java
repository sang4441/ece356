package ece356.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ece356.dbao.PatientDBAO;
import ece356.entity.Patient;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("ece356");

	/**
	 * Default constructor.
	 */
	public PatientServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	private void logParameters(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			logger.log(Level.INFO, "Attribute Name - " + paramName
					+ ", Value - " + request.getParameter(paramName));
		}
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url;

		try {
			logParameters(request);

			int id = getInt(request, "id");
			int person_id = getInt(request, "person-id");
			int default_doc = getInt(request, "default-doc");
			String health_card = getString(request, "health-card");
			int sin = getInt(request, "sin");
			String current_health = getString(request, "current-health");

			request.setAttribute("search", new Patient(id, person_id,
					default_doc, health_card, sin, current_health));

			ArrayList<Patient> patients = PatientDBAO.searchPatient(id,
					person_id, default_doc, health_card, sin, current_health);
			request.setAttribute("patientList", patients);

			url = "/patients.jsp";
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
		getServletContext().getRequestDispatcher(url)
				.forward(request, response);
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

	private int getInt(HttpServletRequest request, String key) {
		String tmp = request.getParameter(key);
		return tmp == null || tmp.isEmpty() ? 0 : Integer.parseInt(tmp);
	}

	private String getString(HttpServletRequest request, String key) {
		String tmp = request.getParameter(key);
		return tmp == null ? "" : tmp;
	}

}
