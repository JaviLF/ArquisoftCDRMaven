
public class PlanDTO {
	private String nombre;
	private String caracteristica;
	private String propias;
	
	PlanDTO(String nombre, String caracteristica, String propias){
		this.nombre = nombre;
		this.caracteristica = caracteristica;
		this.propias = propias;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	public String getPropias() {
		return propias;
	}
	public void setPropias(String propias) {
		this.propias = propias;
	}
}
