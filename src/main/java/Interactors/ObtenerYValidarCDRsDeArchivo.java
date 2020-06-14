package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Entities.CDR;

public class ObtenerYValidarCDRsDeArchivo {
	public List<CDR> ObtenerCDRsDeArchivo(Path path){
		List<CDR> CDRs=new ArrayList<CDR>();
		try {
			File f = path.toFile();
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String cdr;
				cdr = br.readLine();//header
				cdr = br.readLine();//firstline
				while(cdr != null) {
					if(cdr.split(",").length==5) {
						if(esCDRValido(cdr)) {
							CDRs.add(generarCDRDesdeDatos(cdr));
						}
					}
					cdr = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return CDRs;
	}
	
	public CDR generarCDRDesdeDatos(String datosCDR) {
		
		CDR cdr=new CDR ();
		String [] contacto=datosCDR.split(",");
		cdr.setTelfOrigen(contacto[0]);
		cdr.setTelfDestino(contacto[1]);
		cdr.setFecha(contacto[2]);
		cdr.setHoraLlamada(contacto[3]);
		cdr.setDuracionLlamada(contacto[4]);
		return cdr;
	}
	
	public boolean esCDRValido(String cdr) {
		String []datosCdr=cdr.split(",");
		if(esNumeroTelefonicoValido(datosCdr[0])&&esNumeroTelefonicoValido(datosCdr[1])&&esFechaValida(datosCdr[2])&&esHoraValida(datosCdr[3])&&esDuracionValida(datosCdr[4]))
			return true;
		return false;
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
	public boolean esFechaValida(String Fecha) {
		boolean esFecha=false;
		String[] fecha=Fecha.split("-");
		if(fecha.length==3) {
			if(esValorNumerico(fecha[0])&&esValorNumerico(fecha[1])&&esValorNumerico(fecha[2])) {
				if(Integer.parseInt(fecha[0])<=30&&Integer.parseInt(fecha[0])>0&&Integer.parseInt(fecha[1])<=12&&Integer.parseInt(fecha[1])>0&&Integer.parseInt(fecha[2])>1800&&Integer.parseInt(fecha[2])<2021) {
					esFecha=true;
				}
			}
		}
		return esFecha;
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

	public boolean esHoraValida(String Hora) {
		boolean esValido=false;
		String[] hora=Hora.split(":");
		if(hora.length==2) {
			if(esValorNumerico(hora[0])&&esValorNumerico(hora[1])) {
				if(Integer.parseInt(hora[0])<=23&&Integer.parseInt(hora[0])>=0&&Integer.parseInt(hora[1])<=59&&Integer.parseInt(hora[1])>=0) {
					esValido=true;
				}
			}
		}
		return esValido;
	}
	public boolean esDuracionValida(String Duracion) {
		boolean esValido=false;
		String[] duracion=Duracion.split(":");
		if(duracion.length==2) {
			if(esValorNumerico(duracion[0])&&esValorNumerico(duracion[1])) {
				if(Integer.parseInt(duracion[0])<=59&&Integer.parseInt(duracion[0])>=0&&Integer.parseInt(duracion[1])<=59&&Integer.parseInt(duracion[1])>=0) {
					esValido=true;
				}
			}
		}
		return esValido;
	}
}
