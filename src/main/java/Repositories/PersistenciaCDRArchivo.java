package Repositories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.net.URL;
//import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Gateways.PersistenciaCDR;
import Gateways.PersistenciaLinea;

 
public class PersistenciaCDRArchivo implements PersistenciaCDR{
	public void guardarCDR(CDR cdr,int id_tarificacion) {
		try {
			int id=getNextId();
			cdr.setId(id);
			File f = new File("cdrs.txt");
			FileWriter fw;
			BufferedWriter bw;
			if(f.exists()){
				fw = new FileWriter(f,true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(cdr.getId()+"%"+cdr.getNumeroLlamante() + "%" + cdr.getFecha() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
			}else {
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.write("id%telf_origen%telf_destino%FechaLlamada%HoraLlamada%DuracionLlamada%Tarifa%ID_Tarificacion\n");
				bw.write(cdr.getId()+"%"+cdr.getNumeroLlamante() + "%" + cdr.getFecha() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
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
			File f = new File("cdrs.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				for(int i=0;i<id;i++) {
				linea = br.readLine();//skip header and other CDRs
				}
				linea = br.readLine();
				String [] contacto = linea.split("%");
				//CDR cdr = new CDR();
				cdr.setId(id);
				cdr.setNumeroLlamante(contacto[1]);
				cdr.setNumeroLlamado(contacto[2]);
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
	public int getNextId(){
		int id=1;
		try {
			File f = new File("cdrs.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//skip header and other CDRs
				linea = br.readLine();//next line
				while(linea != null) {
					linea = br.readLine();
					id=id+1;
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	public int saveFromArchive(String archive,int id_t) {
		int count=0;
		PersistenciaLinea persis=new PersistenciaLineaSql();
		try {
			File f = new File(archive);
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				String [] contacto;
				while(linea != null) {
					count=count+1;
					contacto = linea.split(",");
					CDR cdr = new CDR();
					cdr.setNumeroLlamante(contacto[0]);
					cdr.setNumeroLlamado(contacto[1]);
					cdr.setFecha(contacto[2]);
					cdr.setHoraLlamada(contacto[3]);
					cdr.setDuracionLlamada(contacto[4]);
						cdr.calcularTarifaParaLinea(persis.getLinea(contacto[0]));
					guardarCDR(cdr,id_t);
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}
	public List<CDR> getCDRSbyTarificationId(int id) {
		List<CDR> lista=new ArrayList<CDR>();
		try {
			File f = new File("cdrs.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				
				while(linea != null) {
					String [] contacto = linea.split("%");
					System.out.println(contacto[7]);
					if((Integer.parseInt(contacto[7]))==id) {
						CDR cdr = new CDR();
						cdr.setId(Integer.parseInt(contacto[0]));
						cdr.setNumeroLlamante(contacto[1]);
						cdr.setNumeroLlamado(contacto[2]);
						cdr.setFecha(contacto[3]);
						cdr.setHoraLlamada(contacto[4]);
						cdr.setDuracionLlamada(contacto[5]);
						cdr.setTarifa(Double.parseDouble(contacto[6]));
						lista.add(cdr);
					}
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	    return lista;
	}
}
