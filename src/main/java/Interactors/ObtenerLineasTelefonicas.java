package Interactors;

import java.util.List;

import Entities.LineaTelefonica;
import Gateways.PersistenciaLineaTelefonica;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;

public class ObtenerLineasTelefonicas {
	public List<LineaTelefonica> getLineas(String tipo_persistencia){
		PersistenciaLineaTelefonica persistencia =null;
		switch(tipo_persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}
		return persistencia.getLineasTelefonicas();
	}
}
