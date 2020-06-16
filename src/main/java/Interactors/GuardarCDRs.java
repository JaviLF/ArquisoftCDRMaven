package Interactors;

import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;

public class GuardarCDRs {
	public void guardarCDRs(List<CDR> cdrs,PersistenciaCDR cdrsRepository,int idTarificacion) {
		
		for(int i=0;i<cdrs.size();i++) {
			cdrsRepository.guardarCDR(cdrs.get(i), idTarificacion);
		}
	}
	
}
