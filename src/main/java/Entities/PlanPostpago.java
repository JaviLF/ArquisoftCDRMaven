package Entities;
public class PlanPostpago implements Plan{
	//propiedades plan
	String nombre="postpago";
	Tarifa tarifa;
	
	public PlanPostpago(){	}
	
	public PlanPostpago(Tarifa tarifa) {
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
