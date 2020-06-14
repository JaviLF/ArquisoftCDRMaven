package Gateways;

import java.util.List;

import DTOs.LineaDTO;
import Entities.LineaTelefonica;

public interface PersistenciaLineaTelefonica {
	public void guardarLineaTelefonica(LineaDTO DTO);
	public LineaTelefonica getLineaTelefonicaByNumero(String numero);
	public List<LineaTelefonica>getLineasTelefonicas();
	
	public boolean exists(String numero);
}
