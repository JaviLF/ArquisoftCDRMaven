package Controllers;

import static spark.Spark.post;
import static spark.Spark.get;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;


import Entities.CDR;

import Entities.Tarificacion;

import Interactors.AgregarTarificacionUseCase;
import Interactors.ObtenerCDRsDesdeArchivoUseCase;
import Interactors.ObtenerCDRsSegunTarificacionUseCase;
import Interactors.ObtenerTarificacionUseCase;
import Interactors.TarificarYGuardarCDRsUseCase;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class CDRController {
	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

	public void main() {
		
		post("/:tipo/UploadCDR",(Request request,Response response) ->{
			ObtenerCDRsDesdeArchivoUseCase obtenerCDR=new ObtenerCDRsDesdeArchivoUseCase();
			TarificarYGuardarCDRsUseCase tarificarYGuardar = new TarificarYGuardarCDRsUseCase();
			AgregarTarificacionUseCase agregarTarificacion=new AgregarTarificacionUseCase();
			Tarificacion tarificacion=agregarTarificacion.agregarTarificacion(request.params(":tipo"));
			 
			request.raw().setAttribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			String fName = request.raw().getPart("upfile").getSubmittedFileName();
			Path out = Paths.get(fName);
			
			List<String> CDRs=obtenerCDR.ObtenerCDRsDeArchivo(out);
			List<CDR> CDRsIngresados=tarificarYGuardar.agregarCDRDesdeArchivo(CDRs, tarificacion.getTipo(), tarificacion.getId());
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	        viewObjects.put("cant", CDRsIngresados.size());
	        Iterable <CDR> CDRSIngresados=CDRsIngresados;
	        viewObjects.put("CDRSIngresados", CDRSIngresados);
	        viewObjects.put("tipo", request.params(":tipo"));
	        return engine.render(new ModelAndView(viewObjects, "CDRsIngresados"));
		});
		
		get("/:tipo/getCDRsRegistered/:id",(request, response) ->{
			ObtenerCDRsSegunTarificacionUseCase obtenerCDRs=new ObtenerCDRsSegunTarificacionUseCase();
			ObtenerTarificacionUseCase obtenerTarificacion=new ObtenerTarificacionUseCase();
			Tarificacion tarificacion=obtenerTarificacion.getTarificacion(Integer.parseInt(request.params(":id")),request.params(":tipo"));
			List<CDR> cdrsTarificacion=obtenerCDRs.obtenerCDRS(tarificacion);
			Iterable<CDR> listaCDRs=cdrsTarificacion;
			Map<String, Object> viewObjects = new HashMap<String, Object>();
			viewObjects.put("listaCDRs", listaCDRs);
			viewObjects.put("cant", tarificacion.getId());
			viewObjects.put("tipo", request.params(":tipo"));
			return engine.render(new ModelAndView(viewObjects, "TarificationCDRs"));
		});
		
	}

}
