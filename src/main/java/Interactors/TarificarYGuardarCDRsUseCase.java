package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
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
		//int cant=persistencia.saveAndTarifyFromArchive(path, idTarificacion);
		//return cant;
		List<CDR> lista=new ArrayList<CDR>();//de momento
		
		PersistenciaLinea lineasRepository=null;
		PersistenciaCDR persistencia=null;
		if(persistencia_tipo=="sql") {
			persistencia=new CDRSqlRepository();
			lineasRepository=new LineaSqlRepository();
		}else {
			persistencia=new CDRFileRepository();
			lineasRepository=new LineaFileRepository();
		}
		
		int count=0;
		for(int i=0;i<cdrs.size();i++) {
			CDR cdr=generarCDRDesdeDatos(cdrs.get(i));
			if(lineasRepository.exists(cdr.getTelfOrigen())) {
				cdr.calcularTarifaSegunLinea(lineasRepository.getLineaByNumero(cdr.getTelfOrigen()));
				System.out.println(cdr.getTarifa());
			}else {
				cdr.setTarifa(9999.9);
			}
			persistencia.guardarCDR(cdr, idTarificacion);
			count=count+1;
			lista.add(cdr);
		}
		//return count;
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
