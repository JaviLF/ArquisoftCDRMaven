package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import DTOs.InputLineaTelefonicaDTO;
import Entities.LineaTelefonica;
import Entities.PlanFactory;


public class ObtenerYValidarLineasTelefonicasDeArchivo {
	
	public List<InputLineaTelefonicaDTO> ObtenerLineasDeArchivo(Path path){
		List<InputLineaTelefonicaDTO> lineasTelefonicas=new ArrayList<InputLineaTelefonicaDTO>();
		try {
			File f = path.toFile();
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				while(linea != null) {
					if(generarLineaDTOSegunDatos(linea)!=null)
						lineasTelefonicas.add(generarLineaDTOSegunDatos(linea));
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return lineasTelefonicas;
	}
	
	public InputLineaTelefonicaDTO generarLineaDTOSegunDatos(String datos) {
		InputLineaTelefonicaDTO dto=null;
		
		datos=datos.replace("[", "");
		datos=datos.replace("]", "");
		
		String [] contacto=datos.split(",");
		
			if(sonDatosValidos(contacto)) {
				PlanFactory factory=new PlanFactory();
				LineaTelefonica lineaTelef = new LineaTelefonica();
		
			
				lineaTelef.setNumero(contacto[0]);
				lineaTelef.setNombreUsuario(contacto[1]);
				
				if(contacto.length>3) {
					List<String>numerosAmigos= new ArrayList<String>();
					int pos=3;
						while(pos<contacto.length) {
							if(esNumeroTelefonicoValido(contacto[pos])==true) {
								numerosAmigos.add(contacto[pos]);
							}
						pos=pos+1;
					}
					lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),numerosAmigos));
					dto=new InputLineaTelefonicaDTO(lineaTelef,numerosAmigos);
					
				}else {
					lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),null));
					dto=new InputLineaTelefonicaDTO(lineaTelef,null);
				}
			
		}
		return dto;
	}

	private boolean sonDatosValidos(String[] contacto) {
		return ((contacto.length > 2) && (esNumeroTelefonicoValido(contacto[0])==true)&&(esNombreValido(contacto[1])==true)&&(esPlanValido(contacto[2])==true));
	}
	
	public boolean esVacio(String valor) {
		return valor.isEmpty();
	}
	public boolean esValorNumerico(String numero) {
		boolean esNumero=false;
		try {
			Integer.parseInt(numero);
			esNumero = true;
		} catch (NumberFormatException nfe){
			esNumero = false;
		}
		return esNumero;
	}
	public boolean esNumeroTelefonicoValido(String numero) {
		boolean esValido=false;
		if(esVacio(numero)==false) {
			if(esValorNumerico(numero)==true) {
				esValido=true;
			}
		}
		return esValido;
	}
	public boolean esNombreValido(String nombre) {
		boolean esValido=false;
		if(esVacio(nombre)==false) {
			if(esValorNumerico(nombre)==false) {
				esValido=true;
			}
		}
		return esValido;
	}
	public boolean esPlanValido(String plan) {
		boolean esValido=false;
		if((plan.toLowerCase().equals("prepago"))||(plan.toLowerCase().equals("postpago"))||(plan.toLowerCase().equals("wow"))) {
				esValido=true;
		}
		return esValido;
	}
	
}

