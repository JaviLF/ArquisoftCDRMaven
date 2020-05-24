import java.util.concurrent.atomic.AtomicInteger;

public class CDR {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
	int id;
	private String telf_salida;
	private String telf_entrada;

	private int horaLlamada = 0;
	private double duracionLlamada= 0;
	private double tarifa;
	
	public CDR() { 
		telf_salida="0000";
		telf_entrada="1111";
		horaLlamada = 0;
		duracionLlamada= 0.0;
	}
	
	public CDR(String numeroLlamante, String numeroLlamado,int horaLlamada,double duracionLlamada) {
		this.id=ID_GENERATOR.getAndIncrement();
		this.telf_salida=numeroLlamante;
		this.telf_entrada=numeroLlamado;
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
		this.telf_salida=numeroLlamante;
	}
	public String getNumeroLlamante() {
		return telf_salida;
	}
	public void setNumeroLlamado(String numeroLlamado) {
		this.telf_entrada=numeroLlamado;
	}
	public String getNumeroLlamado() {
		return telf_entrada;
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
