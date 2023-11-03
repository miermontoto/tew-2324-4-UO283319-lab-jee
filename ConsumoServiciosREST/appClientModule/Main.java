import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
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

		SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new NoOpTrustManager()}, null);

		Client emtusa = ClientBuilder.newBuilder().sslContext(sslContext).build();
		WebTarget emtusaTarget = emtusa.target("https://opendata.gijon.es/descargar.php?id=941&tipo=JSON");
		String emtusaResult = emtusaTarget.request().get().readEntity(String.class);

		System.out.println("-----------TODOS----------------");
		System.out.println(emtusaResult);

		JSONArray datos = (JSONArray) ((JSONObject) JSONValue.parse(emtusaResult)).get("datos");
		JSONArray dato = (JSONArray) ((JSONObject) JSONValue.parse(datos.get(0).toString())).get("dato");
		System.out.println("----------- LINEAS ----------------");
		System.out.println(dato.get(0).toString());
		// Exception in thread "main" java.lang.ClassCastException: org.json.simple.JSONObject cannot be cast to org.json.simple.JSONArray
		// at Main.main(Main.java:50)!!!
	}

	public Main() {
		super();
	}

}

class NoOpTrustManager implements X509TrustManager {
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(X509Certificate[] certs, String authType) {
	}

	public void checkServerTrusted(X509Certificate[] certs, String authType) {
	}
}
