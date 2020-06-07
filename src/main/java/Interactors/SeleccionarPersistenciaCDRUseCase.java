package Interactors;

import Gateways.PersistenciaCDR;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;

public class SeleccionarPersistenciaCDRUseCase {
	public PersistenciaCDR seleccionarPersistencia(String tipo) {
		PersistenciaCDR persistencia = null;
		switch(tipo) {
		case("sql"):
			persistencia= new CDRSqlRepository();
			System.out.println("es sql");
			break;
		case("archivo"):
			persistencia= new CDRFileRepository();
			System.out.println("es archivo");
			break;
		}
		return persistencia;
	}
}
