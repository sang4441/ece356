<%@page import="ece356.entity.Patient" %>
<%@page import="ece356.entity.Person" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ECE 356 Deliverable</title>
</head>
<body>
    <header>
        <%
        Person user = (Person)request.getSession().getAttribute("user");
        if(user != null) {
            out.println(String.format("<span>Hello, %s!</span>", user.getNameFirst()));
            out.println("<a href='/ece356/PersonServlet?logout'>logout</a>");
        } else {
            out.println("<a href='/ece3356/PersonServlet'>login</a>");
        }
        %>
        <nav>
            <ul>
                <li>home</li>
            </ul>
        </nav>
    </header>
    <h2>Patient Dashboard</h2>
    <p>Under Constructions</p>
    <a href="PatientServlet">patients</a>
</body>
</html> 