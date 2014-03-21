<%@page import="java.util.ArrayList"%>
<%@page import="ece356.Patient" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ECE 356 Deliverable</title>
</head>
<%
ArrayList<Patient> patientList;
patientList = (ArrayList<Patient>) request.getAttribute("patientList");
%>
<body>
    <header>
        <nav>
            <ul>
                <li>home</li>
            </ul>
        </nav>
        <a href="/ece356/login.jsp">login</a>
    </header>
	<%
            if (patientList != null) {
                        out.println("<h1>Patient Data</h1>");
                        out.println("<table border=1>");
                        out.println("<tr><th>id</th><th>person_id</th><th>default_doc</th><th>health_card</th><th>sin</th><th>current_health</th></tr>");
                        for (Patient em : patientList) {
                            out.println("<tr><td>");
                            out.print(em.getID());
                            out.print("</td><td>");
                            out.print(em.getPersonID());
                            out.print("</td><td>");
                            out.print(em.getDefaultDoc());
                            out.print("</td><td>");
                            out.print(em.getHealthCard());
                            out.print("</td><td>");
                            out.print(em.getSIN());
                            out.print("</td><td>");
                            out.print(em.getCurrentHealth());
                            out.println("</td></tr>");
                        }
                        out.println("</table>");
                    }
        %>
</body>
</html>