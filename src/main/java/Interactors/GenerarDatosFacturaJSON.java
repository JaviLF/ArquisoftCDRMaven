package Interactors;

import com.google.gson.Gson;

import DTOs.LineaJSONDTO;

import Entities.LineaTelefonica;
import Gateways.PersistenciaCDR;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;

public class GenerarDatosFacturaJSON {
	public String getJSON(LineaTelefonica lineaTelefonica,String tipo, int mes) {
		PersistenciaCDR persistencia; 
		if(tipo=="sql") {
			persistencia = new CDRSqlRepository();
		}
		else {
			persistencia = new CDRFileRepository();
		}
		
		
		 
		LineaJSONDTO dto = new LineaJSONDTO(lineaTelefonica,persistencia.getCDRSbyTelfOrigen(lineaTelefonica.getNumero()), mes);
		Gson gson = new Gson();
		String jsonInString;
		jsonInString = gson.toJson(dto);
		
		return jsonInString;
		
	}
}
