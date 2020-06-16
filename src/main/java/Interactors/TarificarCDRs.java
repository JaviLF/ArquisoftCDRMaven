package Interactors;

import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaLineaTelefonica;

public class TarificarCDRs {
	public List<CDR> tarificarCDRs(List<CDR> cdrs,PersistenciaLineaTelefonica lineasRepository) {
		List<CDR> listaCDRs=new ArrayList<CDR>();//de momento
		CDR cdr=new CDR();
		for(int i=0;i<cdrs.size();i++) {
			cdr=cdrs.get(i);
			if(lineasRepository.exists(cdr.getTelfOrigen())) {
				cdr.calcularTarifaSegunLinea(lineasRepository.getLineaTelefonicaByNumero(cdr.getTelfOrigen()));
				listaCDRs.add(cdr);
			}
		}
		return listaCDRs;
	}
}
