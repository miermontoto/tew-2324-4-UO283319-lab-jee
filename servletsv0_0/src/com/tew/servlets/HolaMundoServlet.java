package com.tew.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HolaMundo", urlPatterns = { "/HolaMundoCordial" })
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre = (String) request.getParameter("NombreUsuario");
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Vector listado = (Vector) session.getAttribute("listado");
		if (listado == null) listado = new Vector();
		
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");
		
		if (nombre != null) {
			out.println("<br>Hola " + nombre + ",<br>");
			listado.addElement(nombre);
		}
		session.setAttribute("listado", listado);
		
		out.println("Bienvenido a mi primera página web!");
		out.println("<br><br>Contigo, hoy me han visitado:");
		for(int i = 0; i < listado.size(); i++) {
			out.println("<br>" + listado.get(i).toString());
		}
		
		out.println("<br><br><a href=\"index.html\">Volver</a>");
		out.println("</BODY></HTML>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
