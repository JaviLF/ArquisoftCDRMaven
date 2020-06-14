package Interactors;

import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;
import Entities.LineaTelefonica;
import Entities.PlanFactory;
import Gateways.PersistenciaLineaTelefonica;
import Repositories.LineaFileRepository;
import Repositories.LineaSqlRepository;


public class GuardarLineas {
	public List<LineaDTO> guardarLineasDesdeArchivo(List<LineaDTO> lineasTelefonicasValidas,PersistenciaLineaTelefonica persistencia) {
		List<LineaDTO> listaLineasTelefonicas=new ArrayList<LineaDTO>();//de momento
		for(int i=0;i<lineasTelefonicasValidas.size();i++) {
			if(!persistencia.exists(lineasTelefonicasValidas.get(i).getLinea().getNumero())) {
				persistencia.guardarLineaTelefonica(lineasTelefonicasValidas.get(i));
				listaLineasTelefonicas.add(lineasTelefonicasValidas.get(i));
			}
		}
		return listaLineasTelefonicas;
		
	}
}