package Repositories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;


 
public class CDRFileRepository implements PersistenciaCDR{
	public void guardarCDR(CDR cdr,int id_tarificacion) {
		try {
			int id=getLastId()+1;
			cdr.setId(id);
			File f = new File("cdrs_register.txt");
			FileWriter fw;
			BufferedWriter bw;
			if(f.exists()){
				fw = new FileWriter(f,true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(cdr.getId()+"%"+cdr.getTelfOrigen() + "%" + cdr.getTelfDestino() + "%" + cdr.getFecha() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
			}else {
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.write("id%telf_origen%telf_destino%FechaLlamada%HoraLlamada%DuracionLlamada%Tarifa%ID_Tarificacion\n");
				bw.write(cdr.getId()+"%"+cdr.getTelfOrigen() + "%" + cdr.getTelfDestino() + "%" + cdr.getFecha() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
			}
			bw.close();
			fw.close(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public CDR getCDR(int id) {
		CDR cdr = new CDR();
		try {
			File f = new File("cdrs_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				for(int i=0;i<id;i++) {
				linea = br.readLine();//skip header and other CDRs
				}
				linea = br.readLine();
				String [] contacto = linea.split("%");
				
				cdr.setId(id);
				cdr.setTelfOrigen(contacto[1]);
				cdr.setTelfDestino(contacto[2]);
				cdr.setFecha(contacto[3]);
				cdr.setHoraLlamada(contacto[4]);
				cdr.setDuracionLlamada(contacto[5]);
				cdr.setTarifa(Double.parseDouble(contacto[6]));
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return cdr;
	}
	public int getLastId(){
		int id=0;
		try {
			File f = new File("cdrs_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//skip header and other CDRs
				linea = br.readLine();//next line
				while(linea != null) {
					id=id+1;
					linea = br.readLine();
					
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	
	public List<CDR> getCDRSbyTarificationId(int id) {
		List<CDR> listaCDRs=new ArrayList<CDR>();
		try {
			File f = new File("cdrs_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				
				while(linea != null) {
					String [] contacto = linea.split("%");
					
					if((Integer.parseInt(contacto[7]))==id) {
						CDR cdr = new CDR();
						cdr.setId(Integer.parseInt(contacto[0]));
						cdr.setTelfOrigen(contacto[1]);
						cdr.setTelfDestino(contacto[2]);
						cdr.setFecha(contacto[3]);
						cdr.setHoraLlamada(contacto[4]);
						cdr.setDuracionLlamada(contacto[5]);
						cdr.setTarifa(Double.parseDouble(contacto[6]));
						listaCDRs.add(cdr);
					}
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	    return listaCDRs;
	}
	public List<CDR> getCDRSbyTelfOrigen(String telfOrigen) {
		List<CDR> listaCDRs=new ArrayList<CDR>();
		try {
			File f = new File("cdrs_register.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				
				while(linea != null) {
					String [] contacto = linea.split("%");
					
					if(Long.parseLong(contacto[1])==Long.parseLong(telfOrigen)) {
						CDR cdr = new CDR();
						cdr.setId(Integer.parseInt(contacto[0]));
						cdr.setTelfOrigen(contacto[1]);
						cdr.setTelfDestino(contacto[2]);
						cdr.setFecha(contacto[3]);
						cdr.setHoraLlamada(contacto[4]);
						cdr.setDuracionLlamada(contacto[5]);
						cdr.setTarifa(Double.parseDouble(contacto[6]));
						listaCDRs.add(cdr);
					}
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	    return listaCDRs;
	}
}
