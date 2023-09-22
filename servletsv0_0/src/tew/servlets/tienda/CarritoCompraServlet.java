package tew.servlets.tienda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CarritoCompra", urlPatterns = { "/CarritoCompra" })
public class CarritoCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
		}

		String producto = request.getParameter("producto");
		if (producto != null) {
			if (carrito.containsKey(producto)) {
				carrito.put(producto, carrito.get(producto) + 1);
			} else {
				carrito.put(producto, 1);
			}
		}

		request.getSession().setAttribute("carrito", carrito);

		out.println("<html><body>");
		out.println("<h1>Carrito de la compra</h1>");
		out.println("<form action='CarritoCompra' method='post'>");
		out.println("\t<select name='producto' size='1'>");
		out.println("\t\t<OPTION VALUE='010'>La trampa</OPTION>");
		out.println("\t\t<OPTION VALUE='020'>Los pjájaros</OPTION>");
		out.println("\t\t<OPTION VALUE='030'>Matrix reloaded</OPTION>");
		out.println("\t</select>");
		out.println("\t<input type='submit' value='Añadir al carrito'>");
		out.println("</form>");

		out.println("<h2>Contenido del carrito</h2>");
		out.println("<ul>");
		for (String p : carrito.keySet()) {
			out.println("<li> Del producto " + p + ", " +  carrito.get(p) + "unidades. </li>");
		}
		out.println("</ul>");
		out.println("</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
