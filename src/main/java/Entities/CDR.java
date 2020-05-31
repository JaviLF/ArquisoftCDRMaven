package Entities;


public class CDR {
	//private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
	int id=0;
	private String telf_origen;
	private String telf_destino;

	private int horaLlamada = 0;
	private double duracionLlamada= 0;
	private double tarifa;
	
	public CDR() { 
		telf_origen="0000";
		telf_destino="1111";
		horaLlamada = 0;
		duracionLlamada= 0.0;
	}
	
	public CDR(String numeroLlamante, String numeroLlamado,int horaLlamada,double duracionLlamada) {
		//this.id=ID_GENERATOR.getAndIncrement();
		this.telf_origen=numeroLlamante;
		this.telf_destino=numeroLlamado;
		this.horaLlamada=horaLlamada;
		this.duracionLlamada=duracionLlamada;
	}
	
	public double calcularTarifaParaLinea(Linea linea) {
		tarifa=duracionLlamada*linea.getTarifaMinuto(this);
		return tarifa;
	}
	
	public void setHoraLlamada(int horaLlamada) {
		this.horaLlamada=horaLlamada;
	}
	public int getHoraLlamada() {
		return horaLlamada;
	}
	public void setDuracionLlamada(double tiempoLlamada) {
		this.duracionLlamada=tiempoLlamada;
	}
	public double getDuracionLlamada() {
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
}
