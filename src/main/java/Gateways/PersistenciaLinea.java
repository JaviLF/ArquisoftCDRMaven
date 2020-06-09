package Gateways;

import java.util.List;

import DTOs.LineaDTO;
import Entities.Linea;

public interface PersistenciaLinea {
	public void guardarLinea(LineaDTO DTO);
	public Linea getLineaByNumero(String numero);
	public List<Linea>getLineas();
	
	public boolean exists(String numero);
}
