package Interactors;


import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLineaTelefonica;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;


public class TarificarYGuardarCDRs {
	public List<CDR> agregarCDR(List<CDR> cdrs,PersistenciaCDR cdrsRepository,PersistenciaLineaTelefonica lineasRepository,int idTarificacion) {
		List<CDR> listaCDRs=new ArrayList<CDR>();//de momento
		
		CDR cdr=new CDR();
		for(int i=0;i<cdrs.size();i++) {
			cdr=cdrs.get(i);
			if(lineasRepository.exists(cdr.getTelfOrigen())) {
				
				cdr.calcularTarifaSegunLinea(lineasRepository.getLineaTelefonicaByNumero(cdr.getTelfOrigen()));
				cdrsRepository.guardarCDR(cdr, idTarificacion);
				listaCDRs.add(cdr);
			}
		}
		return listaCDRs;
	}
	
}
