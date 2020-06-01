package Interactors;

import Gateways.PersistenciaCDR;

public class AgregarCDRDesdeArchivoUseCase {
	public int agregarCDRDesdeArchivo(String file,PersistenciaCDR persistencia,int idTarificacion) {
		int cant=persistencia.saveAndTarifyFromArchive(file, idTarificacion);
		return cant;
	}
}
