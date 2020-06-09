package Controllers;

import static spark.Spark.*;


import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;


import DTOs.LineaDTO;
import Entities.Linea;

import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Interactors.GuardarLineasUseCase;
import Interactors.ObtenerLineasTelefonicasDeArchivoUseCase;
import Interactors.ObtenerLineasUseCase;

import Repositories.CDRSqlRepository;
import Repositories.LineaSqlRepository;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class LineaController {
	PersistenciaCDR cdrs= new CDRSqlRepository();
	PersistenciaLinea lineas= new LineaSqlRepository();
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	public void main() { 
		
		post("/:tipo/uploadLinea", "multipart/form-data", (Request request, Response response) -> {
			
		 	GuardarLineasUseCase agregarLineasUseCase=new GuardarLineasUseCase();
		 	ObtenerLineasTelefonicasDeArchivoUseCase obtenerLineas=new ObtenerLineasTelefonicasDeArchivoUseCase();
		 	
		 	request.raw().setAttribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			String fName = request.raw().getPart("upfile").getSubmittedFileName();
			Path out = Paths.get(fName);
			
			List<String> lineas=obtenerLineas.ObtenerLineasDeArchivo(out);
			List<LineaDTO> lineasIngresadas=agregarLineasUseCase.guardarLineasDesdeArchivo(lineas, request.params(":tipo"));
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	           viewObjects.put("cant", lineasIngresadas.size());
	           Iterable <LineaDTO> LineasIngresadas=lineasIngresadas;
	           viewObjects.put("lineasIngresadas", LineasIngresadas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
	        });
		get("/:tipo/getLineas", (request, response) -> {
			ObtenerLineasUseCase obtenerLineas=new ObtenerLineasUseCase();
			Iterable<Linea> lineas=obtenerLineas.getLineas(request.params(":tipo"));
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			   viewObjects.put("lineas", lineas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
		});
		
	}
}
