package Entities;

import java.util.ArrayList;
import java.util.List;

public class PlanWow implements Plan{
	String nombre="wow";
	Tarifa tarifa;
	List<String> numerosAmigos = null;
	
	public PlanWow(){	}
	
	public PlanWow(Tarifa tarifa,List<String> numerosAmigos) {
		this.tarifa=tarifa;
		this.numerosAmigos=numerosAmigos;
	}
	public PlanWow(Tarifa tarifa) {
		this.tarifa=tarifa;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa=tarifa;
	}
	
	
	public boolean esNumeroAmigo(String numero){
		if((this.numerosAmigos!=null)) {
			return (this.numerosAmigos.contains(numero));
		}else {
			return false;
		}
	}
	
	
	public double getTarifaPorMinuto(CDR cdr) {
		if(esNumeroAmigo(cdr.getTelfDestino())){
			return 0.0;
		}
		return this.tarifa.getTarifa(cdr);
	}
}
