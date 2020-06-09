package Interactors;

import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;
import Entities.Linea;
import Entities.PlanFactory;
import Gateways.PersistenciaLinea;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;


public class GuardarLineasUseCase {
	public List<LineaDTO> guardarLineasDesdeArchivo(List <String> lineas,String tipo_persistencia) {
		PersistenciaLinea persistencia = null;
		switch(tipo_persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}
		List<LineaDTO> lista=new ArrayList<LineaDTO>();//de momento
		
		int count=0;
		
		for(int i=0;i<lineas.size();i++) {
			String datosLinea=lineas.get(i);
			String [] contacto=datosLinea.split(",");
			
			if(!persistencia.exists(contacto[0])) {
				lista.add(generarLineaDTOSegunDatos(datosLinea));
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
		
		String [] contacto=datos.split(",");
		PlanFactory factory=new PlanFactory();
		Linea lineaTelef = new Linea();
		lineaTelef.setNumero(contacto[0]);
		lineaTelef.setNombreUsuario(contacto[1]);
		
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