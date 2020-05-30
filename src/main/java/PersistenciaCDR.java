
public interface PersistenciaCDR {
	public void guardarCDR(CDR cdr,int id_tarificacion);
	public CDR getCDR(int id);
	//public List<CDR> getAllCDRs();
}
