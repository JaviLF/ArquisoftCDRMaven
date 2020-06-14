package DTOs;

import java.util.List;

import Entities.CDR;
import Entities.LineaTelefonica;

public class LineaJSONDTO {
	private LineaTelefonica lineaTelefonica;
	private List<CDR> listaCdrs;
	private double totalAPagar=0;
	public LineaJSONDTO(LineaTelefonica lineaTelefonica,List<CDR> cdrs,int mes) {
		this.lineaTelefonica=lineaTelefonica;
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
	public LineaTelefonica getLinea() {
		return this.lineaTelefonica;
	}
	public void setLinea(LineaTelefonica lineaTelefonica) {
		this.lineaTelefonica = lineaTelefonica;
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
