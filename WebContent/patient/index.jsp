<%@page import="ece356.entity.Patient" %>
<%@page import="ece356.entity.Person" %>
<%@page import="ece356.entity.Visit" %>
<%@page import="ece356.helpers.HtmlHelper" %>
<%@page import="ece356.helpers.PageTemplate" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Date" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

PageTemplate.html(out, request, "ECE 356 Deliverable 2");
PageTemplate.header(out, request); 

%>
    
    <h2>Patient Dashboard</h2>
    <%
	 /*
	    List of all past visits
	 */
	ArrayList<Visit> visits = (ArrayList)request.getAttribute("visits");
    if (visits != null && visits.size() > 0) {
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
    } else {
    	out.println("<h1>No Past Visits</h1>");
    }
    
    PageTemplate.closeHtml(out, request);
%>