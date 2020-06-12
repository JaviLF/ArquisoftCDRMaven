package DTOs;

import java.util.List;

import Entities.CDR;
import Entities.Linea;

public class LineaJSONDTO {
	private Linea linea;
	private List<CDR> listaCdrs;
	private double totalAPagar=0;
	public LineaJSONDTO(Linea linea,List<CDR> cdrs,int mes) {
		this.linea=linea;
		this.listaCdrs=cdrs;
		setTotalAPagar(this.calcularTotal( mes));
	}
	
	public double calcularTotal(int mes) {
		double costoTotal=0;
		for (int i=0;i<listaCdrs.size();i++) {
			if(seRealizoEnMes(listaCdrs.get(i),mes))
			costoTotal=costoTotal+listaCdrs.get(i).getTarifa();
		}
		return costoTotal;
	}
	public boolean seRealizoEnMes(CDR cdr,int mes) {
		String[] datosFecha=cdr.getFecha().split("-");
		return (Integer.parseInt(datosFecha[1])==mes);
	}
	public Linea getLinea() {
		return this.linea;
	}
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	public List<CDR> getListaCdrs() {
		return this.listaCdrs;
	}
	public void setListaCdrs(List<CDR> listaCdrs) {
		this.listaCdrs = listaCdrs;
	}
	public double getTotalAPagar() {
		return totalAPagar;
	}
	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}
}
