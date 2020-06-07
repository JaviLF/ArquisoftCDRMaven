package DTOs;

import java.util.List;

import Entities.Linea;
 
public class LineaDTO {
	private Linea linea;
	private List<String> numerosAmigos=null;
	
	public LineaDTO(Linea linea) {
		this.linea=linea;
	}
	public LineaDTO(Linea linea,List<String>numerosAmigos) {
		this.linea=linea;
		this.numerosAmigos=numerosAmigos;
	}
	public Linea getLinea() {
		return this.linea;
	}
	public List<String> getNumerosAmigos() {
		return this.numerosAmigos;
	}
}
