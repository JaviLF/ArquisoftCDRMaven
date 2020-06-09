package Controllers;

import static spark.Spark.*;
import static spark.Spark.get;

import Interactors.GenerarDatosFacturaJSON;
import Interactors.ObtenerLineaUseCase;


public class APIController {
	
	public void main() {
		get("/:tipo/facturar/:numero",(request,response)->{
			ObtenerLineaUseCase obtenerLinea=new ObtenerLineaUseCase();
			GenerarDatosFacturaJSON resp=new GenerarDatosFacturaJSON();
			if(obtenerLinea.getLinea(request.params(":numero"), request.params(":tipo"))==null){
				return "{}";
			}else {
				return resp.getJSON(obtenerLinea.getLinea(request.params(":numero"), request.params(":tipo")));
			}
		});
	}
}
