package Interactors;

import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.PersistenciaTarificacionSql;

public class ObtenerTarificacionesUseCase {
	public List<Tarificacion> obtenerTarificaciones(){
		PersistenciaTarificacion persistencia=new PersistenciaTarificacionSql();
		return persistencia.getTarificaciones();
	}
}
