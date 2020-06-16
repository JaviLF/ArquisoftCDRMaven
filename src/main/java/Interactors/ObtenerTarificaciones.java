package Interactors;

import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;

public class ObtenerTarificaciones {
	public List<Tarificacion> obtenerTarificaciones(PersistenciaTarificacion persistenciaConfigurada){
		PersistenciaTarificacion persistencia=persistenciaConfigurada;
		
		return persistencia.getTarificaciones();
	}
}
