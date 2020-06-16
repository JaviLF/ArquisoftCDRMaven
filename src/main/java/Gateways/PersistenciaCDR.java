package Gateways;

import java.util.List;

import Entities.CDR;

public interface PersistenciaCDR {
	public void guardarCDR(CDR cdr,int id_tarificacion);
	public CDR getCDR(int id);
	public int getLastId();
	public List<CDR> getCDRSbyTarificationId(int id);
	public List<CDR> getCDRSbyTelfOrigen(String telfOrigen);
}
