import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/gestioneitorv4_0/pisos.json");
		String result = target.request().get().readEntity(String.class);

		/*El código para acceder al servicio y luego tratar los datos.
		Los objetos de las clases JSONObjects (mapas) y JSONArray (arrays)
		 */
		//Imprimimos todo el flujo JSON recibido en formato cadena.
		System.out.println("-----------TODOS----------------");
		System.out.println(result);
		//Procesamos el texto JSON y lo pasamos a formato SIMPLE-JSON
		JSONArray pisos = (JSONArray) ((JSONObject) JSONValue.parse(result)).get("pisos");
		//Imprimimos el contacto tercero (2) transformándolo a formato cadena.
		System.out.println("----------- PISO ----------------");
		System.out.println(pisos.get(2).toString());
		System.out.println("----------- CIUDAD DE UN PISO ----------------");
		JSONObject unPiso = (JSONObject) JSONValue.parse(pisos.get(2).toString());
		String ciudad = (String) unPiso.get("ciudad");
		System.out.println(ciudad);
	}

	public Main() {
		super();
	}

}
