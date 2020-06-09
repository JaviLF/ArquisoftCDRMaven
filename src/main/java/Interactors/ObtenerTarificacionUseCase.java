package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class ObtenerTarificacionUseCase {
	public Tarificacion getTarificacion(int id,String tipo) {
		PersistenciaTarificacion persistencia=null;
		switch(tipo) {
		case("sql"):
			persistencia=new TarificacionSqlRepository();
			break;
		case("archivo"):
			persistencia=new TarificacionFileRepository();
			break;
		}
		return persistencia.getTarificacionById(id);
	}
}
