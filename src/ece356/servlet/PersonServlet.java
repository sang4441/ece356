package ece356.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ece356.dbao.PersonDBAO;
import ece356.entity.Person;
import ece356.helpers.ServletHelper;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("ece356");

	/**
	 * Default constructor.
	 */
	public PersonServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = "login.jsp";

		// check if logout
		if (request.getParameterMap().containsKey("logout")) {
			// logout by removing session variable
			request.getSession().removeAttribute("user");
			ServletHelper.redirect(response, "/ece356/index.jsp");
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) login attempt
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url;
		boolean success = false;

		try {
			ServletHelper.logParameters(request);

			// !TODO: check if they are already logged in with the session
			// variable
			String username = ServletHelper.getString(request, "username");
			String password = ServletHelper.getString(request, "password");

			// find user/pass combo
			Person user = PersonDBAO.login(username, password);

			// set user in session data
			if (user == null) {
				// failed login
				url = "/ece356/login.jsp";
				// add error messages for output
				request.setAttribute("ErrorMessage",
						"username / password combination not found.");
			} else {
				// success
				request.getSession().setAttribute("user", user);
				// get homepage
				switch (user.getRoleID()) {
				// patient
				case 1:
					url = String.format("/ece356/patient/%d", user.getId());
					break;
				// doctor
				case 2:
					url = String.format("/ece356/doctor/%d", user.getId());
					break;
				// staff
				case 3:
					url = String.format("/ece356/staff/%d", user.getId());
					break;
				// error
				default:
					throw new Exception("Role does not exist");

				}
				success = true;
			}

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
		if (success) {
			ServletHelper.redirect(response, url);
		} else {
			getServletContext().getRequestDispatcher(url).forward(request,
					response);
		}
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
