import static spark.Spark.*;
public class SparkUI implements UI{

	public void main() {
		get("/", (request, response) -> getCDRData());
		post("/CDRTarificado",(request, response) -> {
			String telf_origen=request.queryParams("telf_origen");
			String telf_destino=request.queryParams("telf_destino");
			String horaLlamada=request.queryParams("horaLlamada");
			String duracionLlamada=request.queryParams("duracionLlamada");
			return mostrarTarificado(telf_origen,telf_destino,horaLlamada,duracionLlamada);
		});
	}
	
	
	
	private static String mostrarTarificado(String telf_origen,String telf_destino,String horaLlamada,String duracionLlamada) {
		CDR cdr=new CDR(telf_origen,telf_destino,Integer.parseInt(horaLlamada),Double.parseDouble(duracionLlamada));
		Plan plan=new PlanPrepago();
		Linea linea=new Linea(telf_origen,"Pepe",plan);
		double tarifa=cdr.calcularTarifaParaLinea(linea);
		return "<html>"
				+ "<body>"
					+ "<label>telf_origen:</label>"
					+ "<label>  "+telf_origen+"</label>"
					+ "<br/>"
					+ "<label>telf_destino:</label>"
					+ "<label> "+telf_destino+"</label>"
					+ "<br/>"
					+ "<label>horaLlamada:</label>"
					+ "<label> "+horaLlamada+"</label>"
					+ "<br/>"
					+ "<label>duracionLlamada:</label>"
					+ "<label> "+duracionLlamada+"</label>"
					+ "<br/>"
					+ "<label>tarifaLlamada:</label>"
					+ "<label> "+String.valueOf(tarifa)+"</label>"
				+ "</body>"
			+ "</html>";
	}
	public static String getCDRData() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/CDRTarificado'>"
					+ "<label>telf_origen:</label>"
					+ "<input type='text' name='telf_origen'>"
					+ "<label>telf_destino:</label>"
					+ "<input type='text' name='telf_destino'>"
					+ "<label>horaLlamada:</label>"
					+ "<input type='number' name='horaLlamada'>"
					+ "<label>duracionLlamada:</label>"
					+ "<input type='number' step='0.01' name='duracionLlamada'>"
					+ "<input type='submit' value='Tarifar'"
				+ "</body>"
			+ "</html>";
	}
}
