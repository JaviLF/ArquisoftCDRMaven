package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;


public class ObtenerTarificacion {
	public Tarificacion getTarificacion(int id,PersistenciaTarificacion persistenciaConfigurada) {
		PersistenciaTarificacion persistencia=persistenciaConfigurada;
		return persistencia.getTarificacionById(id);
	}
}
