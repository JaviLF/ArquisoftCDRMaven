package Interactors;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class AgregarTarificacion {
	public Tarificacion agregarTarificacion(String tipoPersistencia) {
		PersistenciaTarificacion persistencia=null;
		switch(tipoPersistencia) {
		case("sql"):
			persistencia=new TarificacionSqlRepository();
			break;
		case("archivo"):
			persistencia=new TarificacionFileRepository();
			break;
		}
		Tarificacion tarificacion = new Tarificacion(tipoPersistencia);
		persistencia.guardarTarificacion(tarificacion);
		return tarificacion;
	}
}
