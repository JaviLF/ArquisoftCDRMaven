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


import DTOs.InputLineaTelefonicaDTO;

import Interactors.GestionarConfiguracionPersistencia;
import Interactors.GuardarLineasTelefonicas;
import Interactors.ObtenerYValidarLineasTelefonicasDeArchivo;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import spark.utils.IOUtils;

public class LineaTelefonicaController {
	 
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	public void main() {
		
		post("/:tipo/uploadLinea", "multipart/form-data", (Request request, Response response) -> {
			ObtenerYValidarLineasTelefonicasDeArchivo obtenerLineas=new ObtenerYValidarLineasTelefonicasDeArchivo();
		 	
		 	String path = "lineasUpload/";
		 	request.raw().setAttribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		 	InputStream input = request.raw().getPart("upfile").getInputStream();
		 	String fileName = path + request.raw().getPart("upfile").getSubmittedFileName();
            OutputStream outputStream = new FileOutputStream(fileName);
            IOUtils.copy(input, outputStream);
            outputStream.close();
		 	
			Path filePath = Paths.get(fileName);
			
			List<InputLineaTelefonicaDTO> lineas=obtenerLineas.ObtenerLineasDeArchivo(filePath);
			
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	           viewObjects.put("lineasIngresadas", lineas);
	           viewObjects.put("tipo", request.params(":tipo"));
	           viewObjects.put("filePath", filePath.toString());
	           return engine.render(new ModelAndView(viewObjects, "LineasDeArchivo"));
	        });
		post("/:tipo/saveLineas",(request,response)->{
			ObtenerYValidarLineasTelefonicasDeArchivo obtenerLineas=new ObtenerYValidarLineasTelefonicasDeArchivo();
			GuardarLineasTelefonicas agregarLineasUseCase=new GuardarLineasTelefonicas();
			GestionarConfiguracionPersistencia configuracion=new GestionarConfiguracionPersistencia();
			
			String ruta=request.queryParams("filePath");
			Path filePath = Paths.get(ruta);
			
			configuracion.seleccionarPersistencia(request.params(":tipo"));
			
			List<InputLineaTelefonicaDTO> lineasLeidas=obtenerLineas.ObtenerLineasDeArchivo(filePath);
			List<InputLineaTelefonicaDTO> lineasIngresadas=agregarLineasUseCase.guardarLineasDesdeArchivo(lineasLeidas, configuracion.getPersistenciaLinea());
			
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			viewObjects.put("lineasIngresadas", lineasIngresadas);
			   viewObjects.put("cant", lineasIngresadas.size());
	           viewObjects.put("tipo", request.params(":tipo"));
	           return engine.render(new ModelAndView(viewObjects, "LineasIngresadas"));
			});
		
	}
}
