package Interactors;


import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;
import Repositories.CDRFileRepository;
import Repositories.CDRSqlRepository;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;


public class TarificarYGuardarCDRsUseCase {
	public List<CDR> agregarCDRDesdeArchivo(List<String> cdrs,String persistencia_tipo,int idTarificacion) {
		List<CDR> lista=new ArrayList<CDR>();//de momento
		
		PersistenciaLinea lineasRepository=null;
		PersistenciaCDR persistencia=null;
		switch(persistencia_tipo) {
		case("sql"):
			persistencia=new CDRSqlRepository();
			lineasRepository=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new CDRFileRepository();
			lineasRepository=new LineaFileRepository();
			break;
		}
		
		int count=0;
		for(int i=0;i<cdrs.size();i++) {
			CDR cdr=generarCDRDesdeDatos(cdrs.get(i));
			if(lineasRepository.exists(cdr.getTelfOrigen())) {
				cdr.calcularTarifaSegunLinea(lineasRepository.getLineaByNumero(cdr.getTelfOrigen()));
			}else {
				cdr.setTarifa(9999.9);
			}
			persistencia.guardarCDR(cdr, idTarificacion);
			count=count+1;
			lista.add(cdr);
		}
		return lista;
	}
	
	public CDR generarCDRDesdeDatos(String datosCDR) {
		
		CDR cdr=new CDR ();
		String [] contacto=datosCDR.split(",");
		cdr.setTelfOrigen(contacto[0]);
		cdr.setTelfDestino(contacto[1]);
		cdr.setFecha(contacto[2]);
		cdr.setHoraLlamada(contacto[3]);
		cdr.setDuracionLlamada(contacto[4]);
		return cdr;
	}
}
