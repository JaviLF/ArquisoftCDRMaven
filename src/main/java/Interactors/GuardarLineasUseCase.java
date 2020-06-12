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
	public List<LineaDTO> guardarLineasDesdeArchivo(List<LineaDTO> lineas,PersistenciaLinea persistencia) {
		/*PersistenciaLinea persistencia = persistencia;
		switch(persistencia) {
		case("sql"):
			persistencia=new LineaSqlRepository();
			break;
		case("archivo"):
			persistencia=new LineaFileRepository();
			break;
		}*/
		List<LineaDTO> lista=new ArrayList<LineaDTO>();//de momento
		
		//int count=0;
		
		for(int i=0;i<lineas.size();i++) {
			//String datosLinea=lineas.get(i);
			//String [] contacto=datosLinea.split(",");
			
			if(!persistencia.exists(lineas.get(i).getLinea().getNumero())) {
				persistencia.guardarLinea(lineas.get(i));
				lista.add(lineas.get(i));
				/*persistencia.guardarLinea(generarLineaDTOSegunDatos(datosLinea));
				count=count+1;*/
			}
		}
		
		return lista;
		
	}
}