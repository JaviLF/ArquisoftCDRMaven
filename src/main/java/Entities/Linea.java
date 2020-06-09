package Entities;

public class Linea {	
	private String numero;
	private String nombreUsuario;
	private Plan plan;
	
	public Linea(){	}
	
	public Linea(String numero,String nombrePropietario,Plan plan) {
		this.numero=numero;
		this.nombreUsuario=nombrePropietario;
		this.plan=plan;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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