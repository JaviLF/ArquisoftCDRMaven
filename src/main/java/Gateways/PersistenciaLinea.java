package Gateways;
import java.nio.file.Path;
import java.util.List;

import DTOs.LineaDTO;
import Entities.Linea;

public interface PersistenciaLinea {
	public void guardarLinea(LineaDTO DTO);
	public Linea getLineaByNumero(String numero);
	public List<Linea>getLineas();
	//public int saveFromArchive(Path archive);
	public boolean exists(String numero);
}
