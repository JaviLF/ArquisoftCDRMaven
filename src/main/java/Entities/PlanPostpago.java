package Entities;
public class PlanPostpago implements Plan{
	//propiedades plan
	int id=2;
	private double tarifa=0.99;
		
	public PlanPostpago(){	}
	
	public int getId() {
		return this.id;
	}
		
	public double getTarifaPorMinuto(CDR cdr) {
		return tarifa;
	}
		
}
