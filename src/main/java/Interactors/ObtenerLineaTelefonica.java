package Interactors;

import Entities.LineaTelefonica;
import Gateways.PersistenciaLineaTelefonica;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;

public class ObtenerLineaTelefonica {
	public LineaTelefonica getLinea(String Numero,String tipo_persistencia) {
		PersistenciaLineaTelefonica persistencia =null;
		switch(tipo_persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}
		return persistencia.getLineaTelefonicaByNumero(Numero);
	}
}
