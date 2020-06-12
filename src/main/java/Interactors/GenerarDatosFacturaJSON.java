package Interactors;

import com.google.gson.Gson;

import DTOs.LineaJSONDTO;

import Entities.Linea;
import Gateways.PersistenciaCDR;
import Repositories.CDRSqlRepository;

public class GenerarDatosFacturaJSON {
	public String getJSON(Linea linea,int mes) {
		
		PersistenciaCDR persistencia = new CDRSqlRepository();
		 
		LineaJSONDTO dto = new LineaJSONDTO(linea,persistencia.getCDRSbyTelfOrigen(linea.getNumero()), mes);
		Gson gson = new Gson();
		String jsonInString;
		jsonInString = gson.toJson(dto);
		
		return jsonInString;
		
	}
}
