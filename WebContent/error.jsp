<%@page import="java.io.PrintWriter" %>
<%@page import="java.io.StringWriter" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	<jsp:declaration>
		String errorMessage;
		String stackTrace;
	</jsp:declaration>
	
	<jsp:scriptlet>
		Exception myException = (Exception)request.getAttribute("exception");
		errorMessage = myException.getMessage();
		StringWriter errorWriter = new StringWriter();
		myException.printStackTrace(new PrintWriter(errorWriter));
		stackTrace = errorWriter.toString();
	</jsp:scriptlet>
	
	<h2>Oops!</h2>
	<pre>
		<%= errorMessage %>
	</pre>
	<h3>Exception Stack Trace</h3>
	<pre>
		<%= stackTrace %>
	</pre>
	
</body>
</html>