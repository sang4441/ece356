package ece356.helpers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHelper {
	private static Logger logger = Logger.getLogger("ece356.servlet");

	public static void logParameters(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			logger.log(Level.INFO, "Attribute Name - " + paramName
					+ ", Value - " + request.getParameter(paramName));
		}
	}

	public static <T> void log(T message) {
		String out = message == null ? "null" : message.toString();
		logger.log(Level.INFO, out);
	}

	public static <T> void log(T message, String key) {
		String out = key + ": ";
		out += message == null ? "null" : message.toString();
		logger.log(Level.INFO, out);
	}

	public static void redirect(HttpServletResponse response, String url)
			throws IOException {
		String urlWithSessionID = response.encodeRedirectURL(url);
		response.sendRedirect(urlWithSessionID);
	}

	public static void forward(HttpServletRequest request,
			HttpServletResponse response, String url) throws ServletException,
			IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	public static int getInt(HttpServletRequest request, String key) {
		String tmp = request.getParameter(key);
		return tmp == null || tmp.isEmpty() ? 0 : Integer.parseInt(tmp);
	}

	public static String getString(HttpServletRequest request, String key) {
		String tmp = request.getParameter(key);
		return tmp == null ? "" : tmp;
	}

	public static Date toDate(String date) {
		Date out = null;
		try {
			out = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
					.parse(date);
		} catch (ParseException e) {
			log(e);
			out = new Date();
		}
		return out;
	}

	public static void addErrorMessage(HttpServletRequest request,
			String message) {
		// request.setAttribute("ErrorMessage", message);
	}

}