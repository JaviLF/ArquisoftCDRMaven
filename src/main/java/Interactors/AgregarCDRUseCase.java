package Interactors;

import Entities.CDR;
import Gateways.PersistenciaCDR;

public class AgregarCDRUseCase {
	public void agregarCDR(CDR cdr,PersistenciaCDR persistencia,int idTarificacion) {
		persistencia.guardarCDR(cdr, idTarificacion);
	}
}
