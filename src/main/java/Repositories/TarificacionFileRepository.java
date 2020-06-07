package Repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entities.CDR;
import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;

public class TarificacionFileRepository implements PersistenciaTarificacion{
	public void guardarTarificacion(Tarificacion tarificacion) {
		try {
			File f = new File("tarificaciones_register.txt");
			FileWriter fw;
			BufferedWriter bw;
			LocalDateTime now=LocalDateTime.now();
			tarificacion.setFecha(now.toString());
			int id=getLastId()+1;
			tarificacion.setId(id);
			if(f.exists()){
				fw = new FileWriter(f,true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(tarificacion.getId()+"%"+tarificacion.getFecha()+"%"+tarificacion.getTipo());
			}else {
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.write("ID%FECHA%TIPO\n");
				bw.write(tarificacion.getId()+"%"+tarificacion.getFecha()+"%"+tarificacion.getTipo());
			}
			bw.close();
			fw.close(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public int getLastId() {
		int id=0;
		try {
			File f = new File("tarificaciones_register.txt");
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
	
	public Tarificacion getTarificacionById(int id) {
		Tarificacion tarificacion= new Tarificacion();
		try {
			File f = new File("tarificaciones_register.txt");
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
				tarificacion.setId(Integer.parseInt(contacto[0]));
				tarificacion.setFecha(contacto[1]);
				tarificacion.setTipo(contacto[2]);
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return tarificacion;
	}
	
	public List<Tarificacion> getTarificaciones(){
		List<Tarificacion>lista=new ArrayList<Tarificacion>();
		int lastId=getLastId();
		for (int i=1;i<=lastId;i++) {
			lista.add(getTarificacionById(i));
		}
		return lista;
	}
	
}
