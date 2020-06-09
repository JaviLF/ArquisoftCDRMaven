package DTOs;

import java.util.List;

import Entities.CDR;
import Entities.Linea;

public class LineaJSONDTO {
	private Linea linea;
	private List<CDR> listaCdrs;
	public LineaJSONDTO(Linea linea,List<CDR> cdrs) {
		this.linea=linea;
		listaCdrs=cdrs;
	}
	public Linea getLinea() {
		return linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public List<CDR> getListaCdrs() {
		return listaCdrs;
	}
	public void setListaCdrs(List<CDR> listaCdrs) {
		this.listaCdrs = listaCdrs;
	}
}
