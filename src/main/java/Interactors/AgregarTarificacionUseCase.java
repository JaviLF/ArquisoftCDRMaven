package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionSqlRepository;

public class AgregarTarificacionUseCase {
	public void agregarTarificacion(Tarificacion tarificacion) {
		PersistenciaTarificacion persistencia=new TarificacionSqlRepository();
		persistencia.guardarTarificacion(tarificacion);
	}
}
