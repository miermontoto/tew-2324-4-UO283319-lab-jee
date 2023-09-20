<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="tew.beans.Counter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Hola Mundo!!</title>
</head>
<body>
	<% if (request.getParameter("NombreUsuario") != null) { %>
		<h1>Hola <%=request.getParameter("NombreUsuario")%>!</h1>
		<br>
	<% } %>
	<br>
	<jsp:useBean id="contador" class="tew.beans.Counter" scope="application"/>
	<jsp:getProperty property="incrementedValue" name="contador"/> visitas
</body>
</html>
