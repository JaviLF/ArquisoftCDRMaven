package Entities;
public class PlanPrepago implements Plan{
	//propiedades plan
	String nombre="prepago";
	Tarifa tarifa;
	
	public PlanPrepago(){	}
	
	public PlanPrepago(Tarifa tarifa) {
		this.tarifa=tarifa;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa=tarifa;
	}
	
	public double getTarifaPorMinuto(CDR cdr) {
		return this.tarifa.getTarifa(cdr);
	}
}
