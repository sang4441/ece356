package ece356.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ece356.dbao.DoctorDBAO;
import ece356.dbao.PatientDBAO;
import ece356.entity.Doctor;
import ece356.entity.Patient;
import ece356.entity.Person;
import ece356.helpers.ServletHelper;

@WebServlet(urlPatterns = { "/", "/index", "/IndexServlet", "/ece356/Dashboard" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			String url = "/ece356/index.jsp";
			Person user = null;
			HttpSession session = request.getSession(false);

			if (session != null) {
				user = (Person) session.getAttribute("user");
			}

			if (user != null) {
				// get homepage
				// possibly forward to separate index pages.
				int role = user.getRoleID();
				switch (role) {
				// patient
				case 1:
					Patient patient = PatientDBAO.getPatientByPersonID(user
							.getId());
					request.setAttribute("patient", patient);
					url = "/patient/dashboard.jsp";
					break;
				// doctor
				case 2:
					Doctor doc = DoctorDBAO.getDocByPersonID(user.getId());
					request.setAttribute("doctor", doc);
					url = "/doctor/dashboard.jsp";
					break;
				// staff
				// case 3:
				// Staff staff = StaffDBAO.getStaffByPersonID(user.getId());
				//
				// url = String.format("/ece356/staff/dashboard.jsp",
				// user.getId());
				// break;
				// error
				default:
					throw new Exception("Role does not exist");
				}
				request.getRequestDispatcher(url).include(request, response);
				return;
			}
			ServletHelper.forward(request, response, url);
		} catch (SQLException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String test = "";
	}

}
