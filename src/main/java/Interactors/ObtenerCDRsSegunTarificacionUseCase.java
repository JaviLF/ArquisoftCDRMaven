package Interactors;

import java.util.List;

import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;

public class ObtenerCDRsSegunTarificacionUseCase {
	public List<CDR> obtenerCDRS(Tarificacion tarificacion){
		PersistenciaCDR persistencia;
		if(tarificacion.getTipo()=="sql") {
			persistencia=new CDRSqlRepository();
		}else {
			persistencia=new CDRFileRepository();
		}
		return persistencia.getCDRSbyTarificationId(tarificacion.getId());
	}
}
