package Interactors;

import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLineaTelefonica;
import Gateways.PersistenciaTarificacion;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;
import Repositories.TarificacionFileRepository;
import Repositories.TarificacionSqlRepository;

public class GestionarConfiguracionPersistencia {
	private PersistenciaCDR persistenciaCDR=null;
	private PersistenciaLineaTelefonica persistenciaLineaTelefonicas=null;
	private PersistenciaTarificacion persistenciaTarificacion=null;
	
	public void seleccionarPersistencia(String tipo) {
		switch(tipo) {
		case("sql"):
			this.persistenciaCDR= new CDRSqlRepository();
			this.persistenciaLineaTelefonicas=new LineaSqlRepository();
			this.persistenciaTarificacion=new TarificacionSqlRepository();
			break;
		case("archivo"):
			this.persistenciaCDR= new CDRFileRepository();
			this.persistenciaLineaTelefonicas=new LineaFileRepository();
			this.persistenciaTarificacion=new TarificacionFileRepository();
			break;
		}
	}
	
	public PersistenciaCDR getPersistenciaCDR() {
		return this.persistenciaCDR;
	}
	public PersistenciaLineaTelefonica getPersistenciaLinea() {
		return this.persistenciaLineaTelefonicas;
	}
	public PersistenciaTarificacion PersistenciaTarificacion() {
		return this.persistenciaTarificacion;
	}
}
