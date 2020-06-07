package Entities;


public class Tarificacion {
	private String fecha;
	private int id;
	private String tipo;
	
	public Tarificacion() {	};
	public Tarificacion(String tipo){
		this.setTipo(tipo);
	}

	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha=fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
