package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;
import Entities.Linea;
import Entities.PlanFactory;


public class ObtenerYValidarLineasTelefonicasDeArchivoUseCase {
	
	public List<LineaDTO> ObtenerLineasDeArchivo(Path path){
		List<LineaDTO> lineas=new ArrayList<LineaDTO>();
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
						lineas.add(generarLineaDTOSegunDatos(linea));
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return lineas;
	}
	
	public LineaDTO generarLineaDTOSegunDatos(String datos) {
		LineaDTO dto=null;
		
		datos=datos.replace("[", "");
		datos=datos.replace("]", "");
		
		String [] contacto=datos.split(",");
		if(contacto.length>2) {
			if((validarSiEsNumeroTelefonicoValido(contacto[0])==true)&&(validarSiEsNombreValido(contacto[1])==true)&&(validarSiEsPlanValido(contacto[2])==true)) {
				PlanFactory factory=new PlanFactory();
				Linea lineaTelef = new Linea();
		
			
				lineaTelef.setNumero(contacto[0]);
				lineaTelef.setNombreUsuario(contacto[1]);
				
				if(contacto.length>3) {
					List<String>numerosAmigos= new ArrayList<String>();
					int pos=3;
						while(pos<contacto.length) {
							if(validarSiEsNumeroTelefonicoValido(contacto[pos])==true) {
								numerosAmigos.add(contacto[pos]);
							}
						pos=pos+1;
					}
					lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),numerosAmigos));
					dto=new LineaDTO(lineaTelef,numerosAmigos);
					
				}else {
					lineaTelef.setPlan(factory.generarPlanByName(contacto[2].toLowerCase(),null));
					dto=new LineaDTO(lineaTelef,null);
				}
			}
		}
		return dto;
	}
	
	public boolean validarVacio(String valor) {
		return valor.isEmpty();
	}
	public boolean validarSiEsNumero(String numero) {
		boolean esNumero=false;
		try {
			Integer.parseInt(numero);
			esNumero = true;
		} catch (NumberFormatException nfe){
			esNumero = false;
		}
		return esNumero;
	}
	public boolean validarSiEsNumeroTelefonicoValido(String numero) {
		boolean esValido=false;
		if(validarVacio(numero)==false) {
			if(validarSiEsNumero(numero)==true) {
				esValido=true;
			}
		}
		return esValido;
	}
	public boolean validarSiEsNombreValido(String nombre) {
		boolean esValido=false;
		if(validarVacio(nombre)==false) {
			if(validarSiEsNumero(nombre)==false) {
				esValido=true;
			}
		}
		return esValido;
	}
	public boolean validarSiEsPlanValido(String plan) {
		boolean esValido=false;
		if((plan.toLowerCase().equals("prepago"))||(plan.toLowerCase().equals("postpago"))||(plan.toLowerCase().equals("wow"))) {
				esValido=true;
		}
		return esValido;
	}
	
}

