<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.lang.Integer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Mi Tienda!</title>
</head>
<body>
	<h1>Tienda Virtual</h1>
	<form action="tienda.jsp" method="post">
		<table>
			<tr>
				<td>Escoja el artículo que desea:</td>
			</tr>
			<tr>
				<td>
					<select name="producto" size="1">
						<option value="010" ${param.producto == '010' ? 'selected' : ''}>la trampa</option>
						<option value="345" ${param.producto == '345' ? 'selected' : ''}>los pájaros</option>
						<option value="554" ${param.producto == '554' ? 'selected' : ''}>matrix reloaded</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Añadir al carrito">
				</td>
			</tr>
		</table>
	</form>

	<%
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
		}

		// Añadimos el producto recibido al carrito de la compra (en caso de que no sea nulo!)
		String producto = request.getParameter("producto");

		if (producto != null) {
			Integer cantidad = (Integer) carrito.get(producto);
			if (cantidad == null) cantidad = new Integer(1);
			else cantidad = new Integer (cantidad.intValue() + 1);
			// Y añadimos el producto al carrito
			carrito.put(producto, cantidad);
		}

		// Añadimos el carrito a la sesión
		request.getSession().setAttribute("carrito", carrito);
	%>
	<h2>Carrito de la compra</h2>
	<ul>
		<c:forEach var="entry" items="${carrito}">
			<li> <c:out value="Del producto ${entry.key}, ${entry.value} unidades"/> </li>
		</c:forEach>
	</ul>
</body>
<html>
