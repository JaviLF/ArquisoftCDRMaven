package Interactors;

import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class ObtenerTarificacionesUseCase {
	public List<Tarificacion> obtenerTarificaciones(String tipo){
		PersistenciaTarificacion persistencia=null;
		switch(tipo) {
		case("sql"):
			persistencia=new TarificacionSqlRepository();
			break;
		case("archivo"):
			persistencia=new TarificacionFileRepository();
			break;
		}
		return persistencia.getTarificaciones();
	}
}