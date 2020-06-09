package Interactors;

import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaCDR;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;

public class ObtenerCDRsSegunTarificacionUseCase {
	public List<CDR> obtenerCDRS(Tarificacion tarificacion){
		PersistenciaCDR persistencia=null;
		switch(tarificacion.getTipo()) {
		case("sql"):
			persistencia=new CDRSqlRepository();
			break;
		case("archivo"):
			persistencia=new CDRFileRepository();
			break;
		}
		List<CDR>lista=new ArrayList<CDR>();
		lista=persistencia.getCDRSbyTarificationId(tarificacion.getId());
		return lista;
	}
}
