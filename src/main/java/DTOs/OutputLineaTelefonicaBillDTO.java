package DTOs;

import java.util.List;

import Entities.CDR;
import Entities.LineaTelefonica;

public class OutputLineaTelefonicaBillDTO {
	private LineaTelefonica lineaTelefonica;
	private int cantLlamadas;
	private double totalAPagar=0;
	
	public OutputLineaTelefonicaBillDTO(LineaTelefonica lineaTelefonica,List<CDR> cdrs,int mes) {
		this.lineaTelefonica=lineaTelefonica;
		this.setCantLlamadas(cdrs.size());
		setTotalAPagar(this.calcularTotal(cdrs));
	}
	
	public double calcularTotal(List<CDR> cdrs) {
		double costoTotal=0;
		for (int i=0;i<cdrs.size();i++) {
			costoTotal=costoTotal+cdrs.get(i).getTarifa();
		}
		return costoTotal;
	}
	
	public LineaTelefonica getLinea() {
		return this.lineaTelefonica;
	}
	public void setLinea(LineaTelefonica lineaTelefonica) {
		this.lineaTelefonica = lineaTelefonica;
	}
	
	public double getTotalAPagar() {
		return totalAPagar;
	}
	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public int getCantLlamadas() {
		return cantLlamadas;
	}

	public void setCantLlamadas(int cantLlamadas) {
		this.cantLlamadas = cantLlamadas;
	}
}
