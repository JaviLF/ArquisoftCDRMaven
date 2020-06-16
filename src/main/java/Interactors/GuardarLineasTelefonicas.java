package Interactors;

import java.util.ArrayList;
import java.util.List;

import DTOs.InputLineaTelefonicaDTO;
import Gateways.PersistenciaLineaTelefonica;


public class GuardarLineasTelefonicas {
	public List<InputLineaTelefonicaDTO> guardarLineasDesdeArchivo(List<InputLineaTelefonicaDTO> lineasTelefonicasValidas,PersistenciaLineaTelefonica persistencia) {
		List<InputLineaTelefonicaDTO> listaLineasTelefonicas=new ArrayList<InputLineaTelefonicaDTO>();//de momento
		for(int i=0;i<lineasTelefonicasValidas.size();i++) {
			if(!persistencia.exists(lineasTelefonicasValidas.get(i).getLinea().getNumero())) {
				persistencia.guardarLineaTelefonica(lineasTelefonicasValidas.get(i));
				listaLineasTelefonicas.add(lineasTelefonicasValidas.get(i));
			}
		}
		return listaLineasTelefonicas;
		
	}
}