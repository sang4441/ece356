<%@page import="ece356.entity.Patient" %>
<%@page import="ece356.entity.Person" %>
<%@page import="ece356.entity.Visit" %>
<%@page import="ece356.helpers.HtmlHelper" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Date" %>
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
    <%
	 /*
	    List of all past visits
	 */
	ArrayList<Visit> visits = (ArrayList)request.getAttribute("visits");
    if (visits != null) {
        out.println("<h1>Past Visits</h1>");
        HtmlHelper.startTable(out);
        HtmlHelper.tableHeader(out, new String[]{"id",
            "patient id", 
            "date",
            "length",
            "prescription",
            "diagnosis",
            "comment",
            "doctor id",
            "date modified",
            "initial id"
        });
        for (Visit visit : visits) {
        	HtmlHelper.startTR(out);
        	HtmlHelper.printAttribute(out, visit.getId());
        	HtmlHelper.printAttribute(out, visit.getPatient_id());
        	HtmlHelper.printAttribute(out, visit.getDate());
        	HtmlHelper.printAttribute(out, visit.getLength());
        	HtmlHelper.printAttribute(out, visit.getPrescription());
        	HtmlHelper.printAttribute(out, visit.getDiagnosis());
        	HtmlHelper.printAttribute(out, visit.getComment());
        	HtmlHelper.printAttribute(out, visit.getDoctor_id());
        	HtmlHelper.printAttribute(out, visit.getDate_modified());
        	HtmlHelper.printAttribute(out, visit.getInitial_id());
        	HtmlHelper.endTR(out);
        }
        HtmlHelper.endTable(out);
    }
%>
    
</body>
</html> 