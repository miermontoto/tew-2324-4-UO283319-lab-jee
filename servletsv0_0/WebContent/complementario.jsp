<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="tew.beans.Carrito"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>CarritoCompraJSP</title>
</head>
<body>
	<h1> Carrito de la compra </h1>
	<form action='CarritoCompra' action='post'>
		<select name='producto' size='1'>
			<option value='010'>La trampa</option>
			<option value='020'>Los pájaros</option>
			<option value='030'>Matrix reloaded</option>
		</select>
		<input type='submit' value='Añadir al carrito'>
	</form>
	<br>
	<jsp:useBean id="carrito" class="tew.beans.Carrito" scope="session"/>
	<h2> Contenido del carrito </h2>
	<ul>
		<li>La trampa: <%= carrito.get("010")%></li>
		<li>Los pájaros: <%= carrito.get("020") %></li>
		<li>Matrix reloaded: <%= carrito.get("030") %></li>
	</ul>
</body>
</html>
