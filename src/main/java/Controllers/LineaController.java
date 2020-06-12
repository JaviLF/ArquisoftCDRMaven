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
import Entities.Linea;

import Interactors.GestionarConfiguracionPersistenciaUseCase;
import Interactors.GuardarLineasUseCase;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivoUseCase;
import Interactors.ObtenerLineasUseCase;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.utils.IOUtils;

public class LineaController {
	
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	ObtenerYValidarLineasTelefonicasDeArchivoUseCase obtenerLineas;
	GuardarLineasUseCase agregarLineasUseCase;
	GestionarConfiguracionPersistenciaUseCase configuracion;
	
	public void main() {
		
		post("/:tipo/uploadLinea", "multipart/form-data", (Request request, Response response) -> {
			
		 	obtenerLineas=new ObtenerYValidarLineasTelefonicasDeArchivoUseCase();
		 	
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
			agregarLineasUseCase=new GuardarLineasUseCase();
			String ruta=request.queryParams("filePath");
			Path out = Paths.get(ruta);
			
			GestionarConfiguracionPersistenciaUseCase configuracion=new GestionarConfiguracionPersistenciaUseCase();
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
			ObtenerLineasUseCase obtenerLineas=new ObtenerLineasUseCase();
			Iterable<Linea> lineas=obtenerLineas.getLineas(request.params(":tipo"));
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			   viewObjects.put("lineas", lineas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
		});
		
	}
}
