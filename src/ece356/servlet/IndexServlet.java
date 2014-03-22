package ece356.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ece356.entity.Person;
import ece356.helpers.ServletHelper;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/")
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
		// TODO Auto-generated method stub
		String url = "/";
		Person user = (Person) request.getSession().getAttribute("user");
		if (user != null) {
			// get homepage
			// possibly forward to separate index pages. bingo
			url += user.getRoleID() == 1 ? "patient/index.jsp" : user
					.getRoleID() == 2 ? "doctor/index.jsp"
					: user.getRoleID() == 3 ? "staff/index.jsp" : "index.jsp";
		}
		ServletHelper.forward(request, response, url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
