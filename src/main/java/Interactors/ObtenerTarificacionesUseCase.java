package Interactors;

import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionSqlRepository;

public class ObtenerTarificacionesUseCase {
	public List<Tarificacion> obtenerTarificaciones(){
		PersistenciaTarificacion persistencia=new TarificacionSqlRepository();
		return persistencia.getTarificaciones();
	}
}
