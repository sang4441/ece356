package ece356.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspWriter;

public class HtmlHelper {
	private static Logger logger = Logger.getLogger("ece356.html");

	public static <T> void log(T message) {
		String out = message == null ? "null" : message.toString();
		logger.log(Level.INFO, out);
	}

	public static void startTable(JspWriter out) throws java.io.IOException {
		out.println("<table>");
	}

	public static void endTable(JspWriter out) throws java.io.IOException {
		out.println("</table>");
	}

	public static void startTR(JspWriter out) throws java.io.IOException {
		out.println("<tr>");
	}

	public static void endTR(JspWriter out) throws java.io.IOException {
		out.println("</tr>");
	}

	public static void startTD(JspWriter out) throws java.io.IOException {
		out.println("<td>");
	}

	public static void endTD(JspWriter out) throws java.io.IOException {
		out.println("</td>");
	}

	public static void startTH(JspWriter out) throws java.io.IOException {
		out.println("<th>");
	}

	public static void endTH(JspWriter out) throws java.io.IOException {
		out.println("</th>");
	}

	public static void tableHeader(JspWriter out, String[] headers)
			throws java.io.IOException {
		out.println("<tr>");
		for (String header : headers) {
			out.println(String.format("<th>%s</th>", header));
		}
		out.println("</tr>");
	}

	public static <T> void printAttribute(JspWriter out, T attr)
			throws java.io.IOException {
		out.println("<td>" + attr.toString() + "</td>");
	}
}