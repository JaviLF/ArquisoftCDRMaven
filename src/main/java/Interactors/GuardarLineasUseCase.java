package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;
import Entities.Linea;
import Entities.PlanFactory;
import Gateways.PersistenciaLinea;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;

public class GuardarLineasUseCase {
	public List<Linea> guardarLineasDesdeArchivo(List <String> lineas,String tipo_persistencia) {
		PersistenciaLinea persistencia = null;
		List<Linea> lista=new ArrayList<Linea>();//de momento
		if(tipo_persistencia=="sql") {
			persistencia=new LineaSqlRepository();
		}else {
			persistencia=new LineaFileRepository();
		}
		
		int count=0;
		
		for(int i=0;i<lineas.size();i++) {
			String datosLinea=lineas.get(i);
			String [] contacto=datosLinea.split(",");
			if(!persistencia.exists(contacto[0])) {
				lista.add(generarLineaDTOSegunDatos(datosLinea).getLinea());
				persistencia.guardarLinea(generarLineaDTOSegunDatos(datosLinea));
				count=count+1;
			}
		}
		
		return lista;
		
	}

	public LineaDTO generarLineaDTOSegunDatos(String datos) {
		LineaDTO dto;
		
		datos=datos.replace("[", "");
		datos=datos.replace("]", "");
		System.out.println(datos);
		String [] contacto=datos.split(",");
		PlanFactory factory=new PlanFactory();
		Linea lineaTelef = new Linea();
		lineaTelef.setNumero(contacto[0]);
		lineaTelef.setNombreUsuario(contacto[1]);
		System.out.println(contacto.length);
		if(contacto.length>3) {
			List<String>numerosAmigos= new ArrayList<String>();
			int pos=3;
			while(pos<contacto.length) {
				numerosAmigos.add(contacto[pos]);
				pos=pos+1;
			}
			lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),numerosAmigos));
			dto=new LineaDTO(lineaTelef,numerosAmigos);
			
		}else {
			lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),null));
			dto=new LineaDTO(lineaTelef,null);
		}
		return dto;
	}
}