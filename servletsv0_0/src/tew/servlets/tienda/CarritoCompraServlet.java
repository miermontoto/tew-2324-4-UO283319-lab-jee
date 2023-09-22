package tew.servlets.tienda;

import tew.beans.Carrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CarritoCompra", urlPatterns = { "/CarritoCompra" })
public class CarritoCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
		if (carrito == null) carrito = new Carrito();
		HashMap<String, Integer> carritoMap = carrito.getCarrito();

		String producto = request.getParameter("producto");
		if (producto != null) {
			if (carritoMap.containsKey(producto)) {
				carritoMap.put(producto, carritoMap.get(producto) + 1);
			} else {
				carritoMap.put(producto, 1);
			}
		}

		request.getSession().setAttribute("carrito", carrito);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/complementario.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
