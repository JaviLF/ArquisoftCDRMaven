package Interactors;

import java.util.List;

import Entities.Linea;
import Gateways.PersistenciaLinea;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;

public class ObtenerLineasUseCase {
	public List<Linea> getLineas(String tipo_persistencia){
		PersistenciaLinea persistencia =null;
		switch(tipo_persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}
		return persistencia.getLineas();
	}
}
