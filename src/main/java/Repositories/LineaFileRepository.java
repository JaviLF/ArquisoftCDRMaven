package Repositories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

import DTOs.InputLineaTelefonicaDTO;

import Entities.LineaTelefonica;

import Entities.PlanFactory;

import Gateways.PersistenciaLineaTelefonica;
 
public class LineaFileRepository implements PersistenciaLineaTelefonica{
	public void guardarLineaTelefonica(InputLineaTelefonicaDTO DTO) {
		LineaTelefonica lineaTelefonica=DTO.getLinea();
		if(!exists(lineaTelefonica.getNumero())) {
			try {
				File f = new File("lineas_register.txt");
				FileWriter fw;
				BufferedWriter bw;  
				String datosLinea=generarDatosLinea(DTO);
				if(f.exists()){
					fw = new FileWriter(f,true);
					bw = new BufferedWriter(fw);
					bw.newLine();
					bw.write(datosLinea);
				}else {
					fw = new FileWriter(f);
					bw = new BufferedWriter(fw);
					bw.write("TELEFONO%PROPIETARIO%PLAN%NUMERO_AMIGO_1%NUMERO_AMIGO_2%NUMERO_AMIGO_3%NUMERO_AMIGO_4\n");
					bw.write(datosLinea);
				}
				bw.close();
				fw.close(); 
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	public String generarDatosLinea(InputLineaTelefonicaDTO DTO) {
		LineaTelefonica lineaTelefonica=DTO.getLinea();
		List<String> numerosAmigos=DTO.getNumerosAmigos();
		String datosLinea=lineaTelefonica.getNumero()+"%"+lineaTelefonica.getNombreUsuario()+"%"+lineaTelefonica.getPlan().getNombre();
		if(lineaTelefonica.getPlan().getNombre()!="wow"||numerosAmigos==null) {
			datosLinea=datosLinea+"%"+null+"%"+null+"%"+null+"%"+null;
		}else {
			for(int i=0;i<4;i++) {
				
				if(i<numerosAmigos.size()) {
					datosLinea=datosLinea+"%";
					datosLinea=datosLinea+(numerosAmigos.get(i));
				} else {
					datosLinea=datosLinea+"%"+null;
				}
			}
		}
		
		return datosLinea;
	}
	
	public LineaTelefonica getLineaTelefonicaByNumero(String numero) {
		LineaTelefonica lineaTelefonica = new LineaTelefonica();
		try {
			File f = new File("lineas_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				
				line = br.readLine();//skip header
				String [] contacto=line.split("%");
				boolean found=false;
				while(found==false) {
					line = br.readLine();
					
					contacto = line.split("%");
					
					if(Integer.parseInt(contacto[0])==Integer.parseInt(numero))
						found=true;
				}
				lineaTelefonica=generarLinea(line);
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return lineaTelefonica;
	}
	public List<LineaTelefonica> getLineasTelefonicas(){
		List<LineaTelefonica> listaLineasTelefonicas=new ArrayList<LineaTelefonica>();
		try {
			File f = new File("lineas_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				line = br.readLine();//skip header
				line = br.readLine();//getting first line
				while(line!=null) {
					listaLineasTelefonicas.add(generarLinea(line));
					line = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return listaLineasTelefonicas;
	}
	public LineaTelefonica generarLinea(String linea) {
		LineaTelefonica lineaTelefonica = new LineaTelefonica();
		String [] contacto=linea.split("%");
		lineaTelefonica.setNumero(contacto[0]);
		lineaTelefonica.setNombreUsuario(contacto[1]);
		PlanFactory factory=new PlanFactory();
		
		if(contacto.length<4) {
			lineaTelefonica.setPlan(factory.generarPlanByName(contacto[2],null));
		}else {
			List<String> numerosAmigos=new ArrayList<String>();
			for(int j=0;j<4;j++) {
				if(contacto[j+3]!=null) {
					numerosAmigos.add(contacto[j+3]);
				}
			}
			lineaTelefonica.setPlan(factory.generarPlanByName(contacto[2],numerosAmigos));
		}
		return lineaTelefonica;
	}
	
	
	public boolean exists(String numero) {
		boolean resp=false;
		try {
			File f = new File("lineas_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				
				line = br.readLine();//skip header
				line = br.readLine();//get first line
				while(line!=null) {
					String [] contacto=line.split("%");
					if(Integer.parseInt(contacto[0])==Integer.parseInt(numero)) {
						resp=true;
						line=null;
					}else {
						line = br.readLine();
					}
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return resp;
	}
}
