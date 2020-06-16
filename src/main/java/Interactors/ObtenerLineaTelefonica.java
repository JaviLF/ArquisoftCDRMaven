package Interactors;

import Entities.LineaTelefonica;
import Gateways.PersistenciaLineaTelefonica;

public class ObtenerLineaTelefonica {
	public LineaTelefonica getLinea(String Numero,PersistenciaLineaTelefonica persistenciaConfigurada) {
		PersistenciaLineaTelefonica persistencia =persistenciaConfigurada;
		return persistencia.getLineaTelefonicaByNumero(Numero);
	}
}
