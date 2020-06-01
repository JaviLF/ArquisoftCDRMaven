package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.PersistenciaTarificacionSql;

public class AgregarTarificacionUseCase {
	public void agregarTarificacion(Tarificacion tarificacion) {
		PersistenciaTarificacion persistencia=new PersistenciaTarificacionSql();
		persistencia.guardarTarificacion(tarificacion);
	}
}
