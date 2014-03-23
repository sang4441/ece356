<%@page import="ece356.helpers.PageTemplate"%>
<%@page import="ece356.helpers.HtmlHelper"%>
<%@page import="ece356.entity.Patient" %>
<%@page import="ece356.entity.Person" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	PageTemplate.html(out, request, "Doctor Dashboard");
	PageTemplate.header(out, request);
%>



    <h2>Doctor Dashboard</h2>
    <a href="/ece356/Patient">My Patients</a>
    
<%PageTemplate.closeHtml(out, request); %>