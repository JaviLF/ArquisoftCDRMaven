//<<<<<<< HEAD:src/main/java/ViewModels/SparkUiViewModel.java
package ViewModels;

import static spark.Spark.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import Controllers.CDRController;
import Controllers.HomeController;
import Controllers.LineaController;
import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.GuardarLineasUseCase;
import Interactors.ObtenerLineasTelefonicasDeArchivoUseCase;
import Presenters.UiPresenter; 
import Repositories.CDRSqlRepository;
import Repositories.LineaSqlRepository;

import TemplateEngine.FreeMarkerEngine;
import spark.ModelAndView;
  
//import spark.template.velocity.VelocityTemplateEngine;

public class SparkUIViewModel implements UiPresenter{

	public void main() {
		/*PersistenciaCDR cdrs= new CDRSqlRepository();
		PersistenciaLinea lineas= new LineaSqlRepository();
		LineaController lineaController=new LineaController();
		CDRController cdrController=new CDRController();
		HomeController homeController=new HomeController();
		homeController.main();
		lineaController.main();
		cdrController.main();*/
		//staticFiles.location("/html_views");
		get("/", (request, response) -> hola());
		post("/Upload", (request, response) -> {
			ObtenerLineasTelefonicasDeArchivoUseCase useCase =new ObtenerLineasTelefonicasDeArchivoUseCase();
			String path;
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
		        path=is.toString();
		        Paths.get(path);
		    }
		    return useCase.ObtenerLineasDeArchivo(Paths.get(path)).isEmpty();
		});
		post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo")));
		
	}

	private static String responder_saludo(String nombre) {
		return "Hola "+nombre;
	}

	private static String hola() {
		return "<html>"
			+ "<body>"
			//+ "<form method='post' action='/hola'>"
			//+ "<label>Nombre:</label>"
			//+ "<input type='text' name='nombre_saludo'>"
			//+ "<input type='submit' value='Saluda'"
			+"<form method='post' enctype='multipart/form-data' action='/Upload'>"
			+"<input type='file' name='uploaded_file'>"
			+"<button>Upload picture</button>"
			+"</form>"
			+ "</body>"
			+ "</html>";
	}

}
