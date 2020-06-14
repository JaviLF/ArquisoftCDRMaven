package DTOs;

import java.util.List;

import Entities.LineaTelefonica;
 
public class LineaDTO {
	private LineaTelefonica lineaTelefonica;
	private List<String> numerosAmigos=null;
	
	public LineaDTO(LineaTelefonica lineaTelefonica) {
		this.lineaTelefonica=lineaTelefonica;
	}
	public LineaDTO(LineaTelefonica lineaTelefonica,List<String>numerosAmigos) {
		this.lineaTelefonica=lineaTelefonica;
		this.numerosAmigos=numerosAmigos;
	}
	public LineaTelefonica getLinea() {
		return this.lineaTelefonica;
	}
	public List<String> getNumerosAmigos() {
		return this.numerosAmigos;
	}
}
