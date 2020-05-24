import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo")));
	}

	private static String responder_saludo(String nombre) {
		return "Hola "+nombre;
	}

	private static String hola() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/hola'>"
					+ "<label>Nombre:</label>"
					+ "<input type='text' name='nombre_saludo'>"
					+ "<input type='submit' value='Saluda'"
				+ "</body>"
			+ "</html>";
	}

}
