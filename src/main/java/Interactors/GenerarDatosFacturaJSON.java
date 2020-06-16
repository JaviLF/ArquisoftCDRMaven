package Interactors;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DTOs.OutputLineaTelefonicaBillDTO;
import Entities.CDR;
import Entities.LineaTelefonica;
import Gateways.PersistenciaCDR;

public class GenerarDatosFacturaJSON {
	public String getJSON(LineaTelefonica lineaTelefonica,PersistenciaCDR persistenciaConfigurada, int mes) {
		PersistenciaCDR persistencia=persistenciaConfigurada; 
		List<CDR>listaCDRs=persistencia.getCDRSbyTelfOrigen(lineaTelefonica.getNumero());
		listaCDRs=selecionarCDRsDelMes(listaCDRs,mes);
		OutputLineaTelefonicaBillDTO dto = new OutputLineaTelefonicaBillDTO(lineaTelefonica,listaCDRs, mes);
		Gson gson = new Gson();
		String jsonInString;
		jsonInString = gson.toJson(dto);
		
		return jsonInString;
		
	}
	
	public List<CDR> selecionarCDRsDelMes(List<CDR> registroTotalDeCDRs,int mes){
		List<CDR> cdrsDelMes=new ArrayList<CDR>();
		for(int i=0;i<registroTotalDeCDRs.size();i++) {
			String mesCDR=registroTotalDeCDRs.get(i).getFecha().split("-")[1];
			if(esIgual(Integer.parseInt(mesCDR),mes)){
				cdrsDelMes.add(registroTotalDeCDRs.get(i));
			}
		}
		return cdrsDelMes;
	}
	
	public boolean esIgual(int mes1,int mes2) {
		return (mes1==mes2);
	}
}
