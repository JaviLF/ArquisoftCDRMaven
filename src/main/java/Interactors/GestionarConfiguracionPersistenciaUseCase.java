package Interactors;

import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Gateways.PersistenciaTarificacion;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class GestionarConfiguracionPersistenciaUseCase {
	private PersistenciaCDR persistenciaCDR=null;
	private PersistenciaLinea persistenciaLineas=null;
	private PersistenciaTarificacion persistenciaTarificacion=null;
	
	public void seleccionarPersistencia(String tipo) {
		switch(tipo) {
		case("sql"):
			this.persistenciaCDR= new CDRSqlRepository();
			this.persistenciaLineas=new LineaSqlRepository();
			this.persistenciaTarificacion=new TarificacionSqlRepository();
			break;
		case("archivo"):
			this.persistenciaCDR= new CDRFileRepository();
			this.persistenciaLineas=new LineaFileRepository();
			this.persistenciaTarificacion=new TarificacionFileRepository();
			break;
		}
	}
	
	public PersistenciaCDR getPersistenciaCDR() {
		return this.persistenciaCDR;
	}
	public PersistenciaLinea getPersistenciaLinea() {
		return this.persistenciaLineas;
	}
	public PersistenciaTarificacion PersistenciaTarificacion() {
		return this.persistenciaTarificacion;
	}
}
