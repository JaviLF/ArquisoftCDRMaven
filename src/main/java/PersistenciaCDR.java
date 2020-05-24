import java.util.List;

public interface PersistenciaCDR {
	public void guardarCDR(CDR cdr);
	public CDR getCDR(int id);
	//public List<CDR> getAllCDRs();
}
