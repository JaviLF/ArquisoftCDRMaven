package Entities;

import Gateways.PersistenciaCDR;

public class CDR {
	
	int id=0;
	private String telfOrigen;
	private String telfDestino;
	private String fecha="01-01-2020";
	private String horaLlamada = "00:00";
	private String duracionLlamada= "00:00";
	private double tarifa;
	
	public CDR() { 
		telfOrigen="0000";
		telfDestino="1111";
		horaLlamada = "00:00";
		duracionLlamada= "00:00";
	}
	
	public CDR(String numeroLlamante, String numeroLlamado,String fecha,String horaLlamada,String duracionLlamada) {
		this.telfOrigen=numeroLlamante;
		this.telfDestino=numeroLlamado;
		this.fecha=fecha;
		this.horaLlamada=horaLlamada;
		this.duracionLlamada=duracionLlamada;
	}
	
	public void calcularTarifaSegunLinea(LineaTelefonica lineaTelefonica) {
		String[] duracion=this.duracionLlamada.split(":");
		int minutos=Integer.parseInt(duracion[0]);
		double segundos=Double.parseDouble(duracion[1])/60;
		double tarifaMinuto=lineaTelefonica.getTarifaMinuto(this);
		this.tarifa=(tarifaMinuto*minutos)+(tarifaMinuto*segundos);
		
	}
	
	public void guardarCDR(PersistenciaCDR repository,int id_tarificacion) {
		repository.guardarCDR(this, id_tarificacion);
	}
	
	public void setHoraLlamada(String horaLlamada) {
		this.horaLlamada=horaLlamada;
	}
	public String getHoraLlamada() {
		return horaLlamada;
	}
	public void setDuracionLlamada(String tiempoLlamada) {
		this.duracionLlamada=tiempoLlamada;
	}
	public String getDuracionLlamada() {
		return duracionLlamada;
	}
	public void setTelfOrigen(String numeroLlamante) {
		this.telfOrigen=numeroLlamante;
	}
	public String getTelfOrigen() {
		return telfOrigen;
	}
	public void setTelfDestino(String numeroLlamado) {
		this.telfDestino=numeroLlamado;
	}
	public String getTelfDestino() {
		return telfDestino;
	}
	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}
	public double getTarifa() {
		return this.tarifa;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	public void setFecha(String Fecha) {
		this.fecha=Fecha;
	}
	public String getFecha() {
		return this.fecha;
	}
}
