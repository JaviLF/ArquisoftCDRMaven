package Entities;
public class PlanWow implements Plan{
	int id=3;
	private double tarifa=0.99;
	
	public int getId() {
		return this.id;
	}
	
	public double getTarifaPorMinuto(CDR cdr) {
		return tarifa;
	}
}
