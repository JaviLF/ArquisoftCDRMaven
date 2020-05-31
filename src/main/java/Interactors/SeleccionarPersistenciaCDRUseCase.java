package Interactors;

import Gateways.PersistenciaCDR;
import Repositories.PersistenciaCDRArchivo;
import Repositories.PersistenciaCDRSql;

public class SeleccionarPersistenciaCDRUseCase {
	public PersistenciaCDR seleccionarPersistencia(String tipo) {
		PersistenciaCDR persistencia = null;
		switch(tipo) {
		case("sql"):persistencia= new PersistenciaCDRSql();
		case("archivo"):persistencia= new PersistenciaCDRArchivo();
		}
		return persistencia;
	}
}
