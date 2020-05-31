package Interactors;

import Entities.Linea;
import Gateways.PersistenciaLinea;
import Repositories.PersistenciaLineaSql;

public class AgregarLineaUseCase {
	public void agregarLinea(Linea linea) {
		PersistenciaLinea persistencia = new PersistenciaLineaSql();
		persistencia.guardarLinea(linea);
	}
}
