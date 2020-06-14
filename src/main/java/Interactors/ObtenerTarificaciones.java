package Interactors;

import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class ObtenerTarificaciones {
	public List<Tarificacion> obtenerTarificaciones(String tipoPersistencia){
		PersistenciaTarificacion persistencia=null;
		switch(tipoPersistencia) {
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
