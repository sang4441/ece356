<%@page import="java.util.ArrayList"%>
<%@page import="ece356.entity.Patient" %>
<%@page import="ece356.entity.Person" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ECE 356 Deliverable</title>

<link rel="stylesheet" type="text/css" href="/ece356/css/style.css">
</head>
<%
ArrayList<Patient> patientList;
patientList = (ArrayList<Patient>) request.getAttribute("patientList");
Patient search;
search = (Patient)(request.getAttribute("search") == null ? new Patient() : request.getAttribute("search"));
%>
<body>
    <header>
        <%
        Person user = (Person)request.getSession().getAttribute("user");
        if(user != null) {
            out.println(String.format("<span>Hello, %s!</span>", user.getNameFirst()));
        }
        %>
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
    <form id="patient-search" method="post" action="PatientServlet">
        <span><label>ID</label><input type="text" id="id" name="id" value="<% out.print(search.getID()); %>"/></span>
        <span><label>Person ID</label><input type="text" id="person-id" name="person-id" value="<% out.print(search.getPersonID()); %>"/></span>
        <span><label>Default Doctor ID</label><input type="text" id="default-doc" name="default-doc" value="<% out.print(search.getDefaultDoc()); %>"/></span>
        <span><label>Health Card</label><input type="text" id="health-card" name="health-card" value="<% out.print(search.getHealthCard()); %>"/></span>
        <span><label>SIN</label><input type="text" id="sin" name="sin" value="<% out.print(search.getSIN()); %>"/></span>
        <span><label>Current Health</label><input type="text" id="current-health" name="current-health" value="<% out.print(search.getCurrentHealth()); %>"/></span>
        <button type="submit">search</button>
    </form>
        
</body>
</html>