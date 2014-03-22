package ece356.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ece356.dbao.PersonDBAO;
import ece356.entity.Person;

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

	private void logParameters(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			logger.log(Level.INFO, "Attribute Name - " + paramName
					+ ", Value - " + request.getParameter(paramName));
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/login.jsp";
		getServletContext().getRequestDispatcher(url)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) login attempt
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url;

		try {
			logParameters(request);

			// !TODO: check if they are already logged in with the session
			// variable

			String username = getString(request, "username");
			String password = getString(request, "password");
			// find user/pass combo
			Person user = PersonDBAO.login(username, password);
			// set user in session data
			if (user == null) {
				// failed login
				url = "/login.jsp";
				// add error messages for output
				request.setAttribute("ErrorMessage",
						"username / password combination not found.");
			} else {
				// success
				request.getSession().setAttribute("user", user);
				url = "/index.jsp";
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
		getServletContext().getRequestDispatcher(url)
				.forward(request, response);
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
