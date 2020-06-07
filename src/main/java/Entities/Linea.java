package Entities;
import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;
import Gateways.PersistenciaLinea;

public class Linea {	
	private String numero;
	private String nombrePropietario;
	private Plan plan;
	
	public Linea(){	}
	
	public Linea(String numero,String nombrePropietario,Plan plan) {
		this.numero=numero;
		this.nombrePropietario=nombrePropietario;
		this.plan=plan;
	}
	
	/*public void guardar(PersistenciaLinea repository) {
		repository.guardarLinea(this);
	}*/
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNombreUsuario() {
		return nombrePropietario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombrePropietario = nombreUsuario;
	}
	public void setPlan(Plan plan) {
		this.plan=plan;
	}
	public Plan getPlan() {
		return plan;
	}
	
	public double getTarifaMinuto(CDR cdr) {
		return this.plan.getTarifaPorMinuto(cdr);
	}

}