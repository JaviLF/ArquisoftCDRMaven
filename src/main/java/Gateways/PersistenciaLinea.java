package Gateways;
import Entities.Linea;

public interface PersistenciaLinea {
	public void guardarLinea(Linea linea);
	public Linea getLinea(String numero);
}
