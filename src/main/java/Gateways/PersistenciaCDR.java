package Gateways;
import java.nio.file.Path;
import java.util.List;

import Entities.CDR;

public interface PersistenciaCDR {
	public void guardarCDR(CDR cdr,int id_tarificacion);
	public CDR getCDR(int id);
	public int getLastId();
	public int saveFromArchive(String archive,int id_t);
	public List<CDR> getCDRSbyTarificationId(int id);
	//public int saveAndTarifyFromArchive(Path path,int id_t);
	public List<CDR> getCDRSbyTelfOrigen(String telfOrigen);
}
