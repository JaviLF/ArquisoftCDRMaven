package Interactors;

import Gateways.PersistenciaLinea;
import Repositories.PersistenciaLineaSql;

public class AgregarLineasDesdeArchivoUseCase {
	public int agregarLineasDesdeArchivo(String Archivo) {
		PersistenciaLinea persistencia = new PersistenciaLineaSql();
		return persistencia.saveFromArchive(Archivo);
	}
}
