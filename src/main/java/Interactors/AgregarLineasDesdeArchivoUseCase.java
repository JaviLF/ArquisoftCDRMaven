package Interactors;

import java.nio.file.Path;

import Gateways.PersistenciaLinea;
import Repositories.PersistenciaLineaSql;

public class AgregarLineasDesdeArchivoUseCase {
	public int agregarLineasDesdeArchivo(Path Archivo) {
		PersistenciaLinea persistencia = new PersistenciaLineaSql();
		return persistencia.saveFromArchive(Archivo);
	}
}
