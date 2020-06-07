package Entities;

public class TarifaFija implements Tarifa {
	
	private double tarifa=0.99;
	
	public double getTarifa(CDR cdr) {
		return tarifa;
	}
}
