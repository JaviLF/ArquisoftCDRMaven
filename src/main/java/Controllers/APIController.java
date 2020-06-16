package Controllers;
 
import static spark.Spark.*;
import static spark.Spark.get;

import Interactors.GenerarDatosFacturaJSON;
import Interactors.GestionarConfiguracionPersistencia;
import Interactors.ObtenerLineaTelefonica;

 
public class APIController {
	
	public void main() {
		get("/:tipo/facturar/:numero/:mes",(request,response)->{
			GestionarConfiguracionPersistencia configuracion=new GestionarConfiguracionPersistencia();
			ObtenerLineaTelefonica obtenerLineaTelefonica=new ObtenerLineaTelefonica();
			GenerarDatosFacturaJSON resp=new GenerarDatosFacturaJSON();
			
			configuracion.seleccionarPersistencia(request.params(":tipo"));
			
			if(obtenerLineaTelefonica.getLinea(request.params(":numero"), configuracion.getPersistenciaLinea())==null){
				return "{}";
			}else {
				return resp.getJSON(obtenerLineaTelefonica.getLinea(request.params(":numero"), configuracion.getPersistenciaLinea()), configuracion.getPersistenciaCDR(), Integer.parseInt(request.params(":mes")));
			}
		});
	} 
}
