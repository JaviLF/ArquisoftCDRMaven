package Interactors;

import Entities.CDR;
import Gateways.PersistenciaLinea;
import Repositories.PersistenciaLineaSql;

public class TarificarCDRUseCase {
	public void tarifarCDR(CDR cdr) {
		PersistenciaLinea persistencia=new PersistenciaLineaSql();
		cdr.calcularTarifaParaLinea(persistencia.getLinea(cdr.getNumeroLlamante()));
	}
}
