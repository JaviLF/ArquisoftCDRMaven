package Interactors;


import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;


public class TarificarYGuardarCDRsUseCase {
	public List<CDR> agregarCDR(List<CDR> cdrs,PersistenciaCDR cdrsRepository,PersistenciaLinea lineasRepository,int idTarificacion) {
		List<CDR> lista=new ArrayList<CDR>();//de momento
		
		CDR cdr=new CDR();
		for(int i=0;i<cdrs.size();i++) {
			cdr=cdrs.get(i);
			if(lineasRepository.exists(cdr.getTelfOrigen())) {
				
				cdr.calcularTarifaSegunLinea(lineasRepository.getLineaByNumero(cdr.getTelfOrigen()));
				cdrsRepository.guardarCDR(cdr, idTarificacion);
				lista.add(cdr);
			}
		}
		return lista;
	}
	
}
