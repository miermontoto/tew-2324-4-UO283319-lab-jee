package com.tew.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
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
		HttpSession session = request.getSession();
			
		String nombre = (String) request.getParameter("NombreUsuario");
		Vector listado = (Vector) session.getAttribute("listado");
		Integer contador = (Integer) getServletContext().getAttribute("contador");
		
		if (listado == null) listado = new Vector();
		
		if (contador == null) contador = new Integer(0);
		getServletContext().setAttribute("contador", new Integer(contador.intValue() + 1));
		
		if (nombre != null) {
			listado.addElement(nombre);
		}
		session.setAttribute("listado", listado);
		
		RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("HolaMundoVista");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
