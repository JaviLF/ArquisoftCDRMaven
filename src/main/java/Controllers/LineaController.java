package Controllers;

import static spark.Spark.post;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.GuardarLineasUseCase;
import Presenters.UiPresenter;
import Repositories.CDRSqlRepository;
import Repositories.LineaSqlRepository;

public class LineaController implements UiPresenter {
	PersistenciaCDR cdrs= new CDRSqlRepository();
	PersistenciaLinea lineas= new LineaSqlRepository();

	@Override 
	public void main() {
		post("/uploadLinea", "multipart/form-data", (request, response) -> {
		 	GuardarLineasUseCase agregarLineasUseCase=new GuardarLineasUseCase();
			//- Servlet 3.x config
			String location = "/aaa/bbb";  
			long maxFileSize = 100000000;  
			long maxRequestSize = 100000000; 
			int fileSizeThreshold = 1024;  
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
			request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
			Collection<Part> parts = request.raw().getParts();
			String fName = request.raw().getPart("upfile").getSubmittedFileName();
			Part uploadedFile = request.raw().getPart("upfile");
			Path out = Paths.get(fName);
			int lineasIngresadas=agregarLineasUseCase.agregarLineasDesdeArchivo(out);
			response.redirect("/");
			return null;
		});
		post("/addLinea", (request, response) -> addLinea());
		post("/SaveLinea",(request, response) ->{
			String telf=request.queryParams("telefono");
			String usuario=request.queryParams("usuario");
			String tipoPlan=request.queryParams("tipo");
			Plan plan=new PlanPrepago();
			if(Integer.parseInt(tipoPlan)==2)
				plan=new PlanPostpago();
			if(Integer.parseInt(tipoPlan)==3)
				plan=new PlanWow();
			Linea linea=new Linea(telf,usuario,plan);
			lineas.guardarLinea(linea);
			return addCDR();
		});
		
	}
	
	public static String addLinea() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/SaveLinea'>"//
					+ "<label>telefono:</label>"
					+ "<input type='text' name='telefono'>"
					+ "<br/>"
					+ "<label>Usuario:</label>"
					+ "<input type='text' name='usuario'>"
					+ "<br/>"
					+ "<b>Tipo:</b>"
					+ "<select name='tipo'  id='tipo'>"
                    +"<option value='3'>Plan Wow</option>"
                    +"<option value='1'>Plan PrePago</option>"
                    +"<option value='2'>Plan PostPago</option>"
                    +"</select>"
					+ "<br/>"
					+ "<input type='submit' value='Guardar Linea'"
				+ "</body>"
			+ "</html>";
	}
	public static String addCDR() {
		return "<html>"
				+ "<body>"
					+ "<form method='post' action='/SaveCDR'>"
					+ "<label>telf_origen:</label>"
					+ "<input type='text' name='telf_origen'>"
					+ "<br/>"
					+ "<label>telf_destino:</label>"
					+ "<input type='text' name='telf_destino'>"
					+ "<br/>"
					+ "<label>horaLlamada:</label>"
					+ "<input type='number' name='horaLlamada'>"
					+ "<br/>"
					+ "<label>duracionLlamada:</label>"
					+ "<input type='number' step='0.01' name='duracionLlamada'>"
					+ "<br/>"
					+ "<input type='submit' value='Guardar y Tarifar'"
					//+ "</form>"
				+ "</body>"
			+ "</html>";
	}
}
