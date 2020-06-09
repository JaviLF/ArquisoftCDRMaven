package Interactors;

import Entities.Linea;
import Gateways.PersistenciaLinea;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;

public class ObtenerLineaUseCase {
	public Linea getLinea(String Numero,String tipo_persistencia) {
		PersistenciaLinea persistencia =null;
		switch(tipo_persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}
		return persistencia.getLineaByNumero(Numero);
	}
}
