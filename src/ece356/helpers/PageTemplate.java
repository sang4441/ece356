package ece356.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import ece356.entity.Person;

public class PageTemplate {

	public static void html(JspWriter out, HttpServletRequest request,
			String title) {
		try {
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
			out.println("<html>");
			out.println("\t<head>");
			out.println("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
			out.println(String.format("\t\t<title>%s</title>", title));
			out.println("\t</head>");
			out.println("\t<body>");
		} catch (Exception e) {
			HtmlHelper.log(e);
		}
	}

	public static void header(JspWriter out, HttpServletRequest request) {
		try {
			out.println("<header>");
			Person user = null;
			HttpSession session = request.getSession(false);

			if (session != null) {
				user = (Person) session.getAttribute("user");
			}

			if (user != null) {
				out.println(String.format("<span>Hello, %s!</span>",
						user.getNameFirst()));
				out.println("<a href='/ece356/PersonServlet?logout'>logout</a>");
			} else {
				out.println("<a href='/ece3356/PersonServlet'>login</a>");
			}
			out.println("<nav><ul><li>home</li></ul></nav></header>");
		} catch (Exception e) {
			HtmlHelper.log(e);
		}
	}

	public static void closeHtml(JspWriter out, HttpServletRequest request) {
		try {
			out.println("\t</body>");
			out.println("</html>");
		} catch (Exception e) {
			HtmlHelper.log(e);
		}
	}
}