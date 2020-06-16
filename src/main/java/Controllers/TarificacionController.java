package Controllers;
 
import static spark.Spark.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Tarificacion;
import Interactors.GestionarConfiguracionPersistencia;
import Interactors.ObtenerTarificaciones;

import spark.ModelAndView;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class TarificacionController {

	private static ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
	
	public void main() {
		get("/:tipo/GetTarifications", (request, response)->{
			ObtenerTarificaciones tarificaciones=new ObtenerTarificaciones();
			GestionarConfiguracionPersistencia configuracion=new GestionarConfiguracionPersistencia();
			configuracion.seleccionarPersistencia(request.params(":tipo"));
			
			List<Tarificacion> lista=tarificaciones.obtenerTarificaciones(configuracion.getPersistenciaTarificacion());
			Iterable<Tarificacion> Lista=lista;
			Map<String, Object> viewObjects = new HashMap<String, Object>();
	        viewObjects.put("tarificaciones", Lista);
	        viewObjects.put("tipo", request.params(":tipo"));
	        return engine.render(new ModelAndView(viewObjects, "Tarifications"));
		});
	}
}
