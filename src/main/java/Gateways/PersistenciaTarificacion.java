package Gateways;

import java.util.List;

import Entities.Tarificacion;

public interface PersistenciaTarificacion {
	public void guardarTarificacion(Tarificacion tarificacion);
	public List<Tarificacion> getTarificaciones();
	public Tarificacion getTarificacionById(int id);
	
}
