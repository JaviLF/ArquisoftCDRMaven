package Interactors;

import java.util.List;

import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Repositories.PersistenciaCDRArchivo;
import Repositories.PersistenciaCDRSql;

public class ObtenerCDRSUseCase {
	public List<CDR> obtenerCDRS(Tarificacion tarificacion){
		PersistenciaCDR persistencia;
		if(tarificacion.getTipo()=="sql") {
			persistencia=new PersistenciaCDRSql();
		}else {
			persistencia=new PersistenciaCDRArchivo();
		}
		return persistencia.getCDRSbyTarificationId(tarificacion.getId());
	}
}
