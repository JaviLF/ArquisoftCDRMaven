package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class AgregarTarificacionUseCase {
	public Tarificacion agregarTarificacion(String tipo) {
		PersistenciaTarificacion persistencia=null;
		switch(tipo) {
		case("sql"):
			persistencia=new TarificacionSqlRepository();
			break;
		case("archivo"):
			persistencia=new TarificacionFileRepository();
			break;
		}
		Tarificacion tarificacion = new Tarificacion(tipo);
		persistencia.guardarTarificacion(tarificacion);
		return tarificacion;
	}
}
