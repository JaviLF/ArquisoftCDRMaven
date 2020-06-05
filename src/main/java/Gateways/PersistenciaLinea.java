package Gateways;
import java.nio.file.Path;

import Entities.Linea;

public interface PersistenciaLinea {
	public void guardarLinea(Linea linea);
	public Linea getLinea(String numero);
	public int saveFromArchive(Path archive);
}
