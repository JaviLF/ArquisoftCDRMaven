package Controllers;

import static spark.Spark.*;
import static spark.Spark.get;

import Interactors.GenerarDatosFacturaJSON;
import Interactors.ObtenerLineaTelefonica;


public class APIController {
	
	public void main() {
		get("/:tipo/facturar/:numero/:mes",(request,response)->{
			ObtenerLineaTelefonica obtenerLineaTelefonica=new ObtenerLineaTelefonica();
			GenerarDatosFacturaJSON resp=new GenerarDatosFacturaJSON();
			if(obtenerLineaTelefonica.getLinea(request.params(":numero"), request.params(":tipo"))==null){
				return "{}";
			}else {
				return resp.getJSON(obtenerLineaTelefonica.getLinea(request.params(":numero"), request.params(":tipo")), request.params(":tipo"), Integer.parseInt(request.params(":mes")));
			}
		});
	}
}
