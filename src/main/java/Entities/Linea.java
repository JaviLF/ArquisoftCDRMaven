package Entities;
import java.util.ArrayList;
import java.util.List;

public class Linea {	
	private String numero;
	private String nombrePropietario;
	private Plan plan;
	private List<String> numeros_amigos=null;
	
	public Linea(){
		numero="0000";
		nombrePropietario="Pepe";
		plan=new PlanPrepago();
	}
	public Linea(String numero,String nombrePropietario,Plan plan) {
		this.numero=numero;
		this.nombrePropietario=nombrePropietario;
		this.plan=plan;
		if(plan.getId()==3)
			numeros_amigos=new ArrayList<String>();
	}
	
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
		if(plan.getId()==3)
			numeros_amigos=new ArrayList<String>();
	}
	public Plan getPlan() {
		return plan;
	}
	public boolean esNumeroAmigo(String numero){
		if(this.numeros_amigos!=null) {
			return (this.numeros_amigos.contains(numero));
		}else {
		return false;
		}
	}
	
	public void addNumeroAmigo(String numero) {
		if(this.numeros_amigos!=null) {
			if(this.numeros_amigos.size()<4)
				this.numeros_amigos.add(numero);
		}
	}
	
	public double getTarifaMinuto(CDR cdr) {
		if(esNumeroAmigo(cdr.getNumeroLlamado())) {
			return 0.0;
		}
		return this.plan.getTarifaPorMinuto(cdr);
	}
	public List<String> getNumerosAmigos(){
		return this.numeros_amigos;
	}
}