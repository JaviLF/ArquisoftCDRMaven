package Controllers;

import static spark.Spark.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;


import DTOs.LineaDTO;
import Entities.LineaTelefonica;

import Interactors.GestionarConfiguracionPersistencia;
import Interactors.GuardarLineas;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivo;
import Interactors.ObtenerLineasTelefonicas;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.utils.IOUtils;

public class LineaController {
	
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	ObtenerYValidarLineasTelefonicasDeArchivo obtenerLineas;
	GuardarLineas agregarLineasUseCase;
	GestionarConfiguracionPersistencia configuracion;
	
	public void main() {
		
		post("/:tipo/uploadLinea", "multipart/form-data", (Request request, Response response) -> {
			
		 	obtenerLineas=new ObtenerYValidarLineasTelefonicasDeArchivo();
		 	
		 	String path = "lineasUpload/";
		 	request.raw().setAttribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		 	InputStream input = request.raw().getPart("upfile").getInputStream();
		 	String fileName = path + request.raw().getPart("upfile").getSubmittedFileName();
            OutputStream outputStream = new FileOutputStream(fileName);
            IOUtils.copy(input, outputStream);
            outputStream.close();
		 	
			Path out = Paths.get(fileName);
			
			List<LineaDTO> lineas=obtenerLineas.ObtenerLineasDeArchivo(out);
			
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	           viewObjects.put("lineasIngresadas", lineas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           viewObjects.put("filePath", out.toString());
	           return engine.render(new ModelAndView(viewObjects, "LineasDeArchivo"));
	        });
		post("/:tipo/saveLineas",(request,response)->{
			agregarLineasUseCase=new GuardarLineas();
			String ruta=request.queryParams("filePath");
			Path out = Paths.get(ruta);
			
			GestionarConfiguracionPersistencia configuracion=new GestionarConfiguracionPersistencia();
			configuracion.seleccionarPersistencia(request.params(":tipo"));
			
			List<LineaDTO> lineasLeidas=obtenerLineas.ObtenerLineasDeArchivo(out);
			List<LineaDTO> lineasIngresadas=agregarLineasUseCase.guardarLineasDesdeArchivo(lineasLeidas, configuracion.getPersistenciaLinea());
			
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			viewObjects.put("lineasIngresadas", lineasIngresadas);
			   viewObjects.put("cant", lineasIngresadas.size());
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
			});
		get("/:tipo/getLineas", (Request request, Response response) -> {
			ObtenerLineasTelefonicas obtenerLineasTelefonicas=new ObtenerLineasTelefonicas();
			Iterable<LineaTelefonica> lineaTelefonicas=obtenerLineasTelefonicas.getLineas(request.params(":tipo"));
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			   viewObjects.put("lineas", lineaTelefonicas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
		});
		
	}
}
