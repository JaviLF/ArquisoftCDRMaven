package Controllers;

import static spark.Spark.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Tarificacion;
import Interactors.ObtenerTarificacionesUseCase;

import spark.ModelAndView;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class HomeController {

	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	public void main() {
		redirect.get("/","/archivo");
		
	    get("/:tipo", (request, response) -> {
		       Map<String, Object> viewObjects = new HashMap<String, Object>();
		           viewObjects.put("tipo", request.params(":tipo"));
		           return engine.render(new ModelAndView(viewObjects, "UploadLineas"));
		           
		});
		get("/:tipo/GetCDRFile", (request, response)->{
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	        viewObjects.put("title", "Welcome to Spark Project");
	        viewObjects.put("tipo", request.params(":tipo"));
	        return engine.render(new ModelAndView(viewObjects, "UploadCDRs"));
		});
		get("/:tipo/GetTarifications", (request, response)->{
			ObtenerTarificacionesUseCase tarificaciones=new ObtenerTarificacionesUseCase();
			List<Tarificacion> lista=tarificaciones.obtenerTarificaciones(request.params(":tipo"));
			Iterable<Tarificacion> Lista=lista;
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	        viewObjects.put("tarificaciones", Lista);
	        viewObjects.put("tipo", request.params(":tipo"));
	        return engine.render(new ModelAndView(viewObjects, "Tarifications"));
		});
		get("/:tipo/Configuration",(request,response)->{
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			viewObjects.put("tipo", request.params(":tipo"));
			return engine.render(new ModelAndView(viewObjects, "Configuration"));
		});
	}
 
}
