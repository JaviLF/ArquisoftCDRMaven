package Entities;


public class CDR {
	//private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
	int id=0;
	private String telf_origen;
	private String telf_destino;
	private String fecha="01-01-2020";
	private String horaLlamada = "00:00";
	private String duracionLlamada= "00:00";
	private double tarifa;
	
	public CDR() { 
		telf_origen="0000";
		telf_destino="1111";
		horaLlamada = "00:00";
		duracionLlamada= "00:00";
	}
	
	public CDR(String numeroLlamante, String numeroLlamado,String fecha,String horaLlamada,String duracionLlamada) {
		//this.id=ID_GENERATOR.getAndIncrement();
		this.telf_origen=numeroLlamante;
		this.telf_destino=numeroLlamado;
		this.fecha=fecha;
		this.horaLlamada=horaLlamada;
		this.duracionLlamada=duracionLlamada;
	}
	
	public void calcularTarifaParaLinea(Linea linea) {
		String[] duracion=this.duracionLlamada.split(":");
		int minutos=Integer.parseInt(duracion[0]);
		double segundos=Integer.parseInt(duracion[1])/60;
		double tarifaMinuto=linea.getTarifaMinuto(this);
		this.tarifa=(tarifaMinuto*minutos)+(tarifaMinuto*segundos);
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
	public void setNumeroLlamante(String numeroLlamante) {
		this.telf_origen=numeroLlamante;
	}
	public String getNumeroLlamante() {
		return telf_origen;
	}
	public void setNumeroLlamado(String numeroLlamado) {
		this.telf_destino=numeroLlamado;
	}
	public String getNumeroLlamado() {
		return telf_destino;
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
