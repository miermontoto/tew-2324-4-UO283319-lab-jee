package tew.beans;

import java.util.HashMap;

public class Carrito {
	private HashMap<String, Integer> carrito;

	public Carrito() {
		carrito = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getCarrito() {
		return carrito;
	}

	public Integer get(String producto) {
		Integer cantidad = carrito.get(producto);
		return cantidad == null ? 0 : cantidad;
	}
}
