package ece356.session;

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
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("ece356");

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check if logout
		if (request.getParameterMap().containsKey("logout")) {
			// logout
			request.getSession(false).invalidate();
			ServletHelper.redirect(response, "index.jsp");
		} else {
			request.getRequestDispatcher("login.jsp")
					.include(request, response);
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
				url = "login.jsp";
				// add error messages for output
				ServletHelper.addErrorMessage(request,
						"username / password combination not found.");
			} else {
				// success
				request.getSession().setAttribute("user", user);
				// get homepage
				ServletHelper.redirect(response, "/ece356/index.jsp");
				return;
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

		ServletHelper.forward(request, response, url);
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
