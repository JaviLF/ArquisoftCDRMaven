package Gateways;

import java.util.List;

import DTOs.InputLineaTelefonicaDTO;
import Entities.LineaTelefonica;

public interface PersistenciaLineaTelefonica {
	public void guardarLineaTelefonica(InputLineaTelefonicaDTO DTO);
	public LineaTelefonica getLineaTelefonicaByNumero(String numero);
	public List<LineaTelefonica>getLineasTelefonicas();
	
	public boolean exists(String numero);
}
