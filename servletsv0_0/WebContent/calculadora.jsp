<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Calculadora</title>
</head>
<body>
	<h1> Calculadora </h1>
	<form action='calculadora.jsp' action='post'>
		<input type='text' name='operando1' value='0' size='5'>
		<select name='operador'>
			<option value='+' selected>+</option>
			<option value='-'>-</option>
			<option value='*'>*</option>
			<option value='/'>/</option>
		</select>
		<input type='text' name='operando2' value='0' size='5'>
		<input type='submit' value='='>
	</form>
		<%
			String resultado = "";
			if (request.getParameter("operando1") != null) {
				Double temp = 0.0;
				String operando1 = request.getParameter("operando1");
				String operando2 = request.getParameter("operando2");
				String operador = request.getParameter("operador");
				if (operador.equals("+")) {
					temp = Double.parseDouble(operando1) + Double.parseDouble(operando2);
				} else if (operador.equals("-")) {
					temp = Double.parseDouble(operando1) - Double.parseDouble(operando2);
				} else if (operador.equals("*")) {
					temp = Double.parseDouble(operando1) * Double.parseDouble(operando2);
				} else if (operador.equals("/")) {
					temp = Double.parseDouble(operando1) / Double.parseDouble(operando2);
				}

				resultado = String.format("%f", temp);
			} else resultado = "";
		%>
		<h2>Resultado: <%=resultado%></h2>
</body>
</html>
