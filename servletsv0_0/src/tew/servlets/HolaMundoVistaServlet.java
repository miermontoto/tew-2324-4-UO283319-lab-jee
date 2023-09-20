package tew.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HolaMundoVista", urlPatterns = { "/HolaMundoVista" })
public class HolaMundoVistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = (String) request.getParameter("NombreUsuario");
		Vector listado = (Vector) request.getSession().getAttribute("listado");
		Integer contador = (Integer) getServletContext().getAttribute("contador");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");

		out.println("<br>Hola " + nombre + ",<br>");
		out.println("Bienvenido a mi primera p�gina web!");

		if (listado.size() == 0) {
			out.println("<br><br>Eres la primera persona en visitar esta p�gina!");
		} else {
			out.println("<br><br>Contigo, hoy me han visitado:");
			for (int i = 0; i < listado.size(); i++) {
				out.println("<br> - " + listado.get(i).toString());
			}
		}


		out.println("<br><br>Visitas: " + contador);
		out.println("<br><a href=\"index.html\">Volver</a>");
		out.println("</BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
