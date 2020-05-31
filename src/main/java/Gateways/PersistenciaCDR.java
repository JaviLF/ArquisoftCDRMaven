package Gateways;
import java.util.List;

import Entities.CDR;

public interface PersistenciaCDR {
	public void guardarCDR(CDR cdr,int id_tarificacion);
	public CDR getCDR(int id);
	public int getNextId();
	public int saveFromArchive(String archive,int id_t);
	public List<CDR> getCDRSbyTarificationId(int id);
	//public List<CDR> getAllCDRs();
}
