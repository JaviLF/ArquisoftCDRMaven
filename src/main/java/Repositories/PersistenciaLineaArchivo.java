package Repositories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

import Entities.CDR;
import Entities.Linea;
import Entities.Plan;
import Entities.PlanFactory;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaLinea;
 
public class PersistenciaLineaArchivo implements PersistenciaLinea{
	public void guardarLinea(Linea linea) {
		if(!exists(linea.getNumero())) {
			try {
				File f = new File("lineas.txt");
				FileWriter fw;
				BufferedWriter bw;
				String datosLinea=linea.getNumero()+"%"+linea.getNombreUsuario()+"%"+linea.getPlan().getId();
				if(linea.getPlan().getId()==3) {
					for(int i=0;i<4;i++) {
						System.out.println(linea.getNumerosAmigos().size());
						if(i<linea.getNumerosAmigos().size()) {
							datosLinea=datosLinea+"%";
							datosLinea=datosLinea+(linea.getNumerosAmigos().get(i));
						} else {
							datosLinea=datosLinea+"%"+null;
						}
					}
					System.out.println(datosLinea);
				}
				else {
					datosLinea=datosLinea+"%"+null+"%"+null+"%"+null+"%"+null;
				}
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
	public Linea getLinea(String numero) {
		Linea linea = new Linea();
		try {
			File f = new File("lineas.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				
				line = br.readLine();//skip header
				String [] contacto=line.split("%");
				boolean found=false;
				while(found==false) {
					line = br.readLine();
					System.out.println(line);
					contacto = line.split("%");
					System.out.println(contacto[0]);
					if(Integer.parseInt(contacto[0])==Integer.parseInt(numero))
						found=true;
				}
				linea.setNumero(contacto[0]);
				linea.setNombreUsuario(contacto[1]);
				PlanFactory factory=new PlanFactory();
				linea.setPlan(factory.generarPlanById(Integer.parseInt(contacto[2])));
				if(linea.getPlan().getId()==3) {
					for(int j=0;j<4;j++) {
						if(contacto[j+3]!=null) {
							linea.addNumeroAmigo(contacto[j+3]);
						}
					}
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return linea;
	}
	public int saveFromArchive(Path archive) {
		int count=0;
		try {
			File f = archive.toFile();
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				String [] contacto;
				PlanFactory factory=new PlanFactory();
				while(linea != null) {
					count=count+1;
					linea=linea.replace("[", "");
					linea=linea.replace("]", "");
					System.out.println(linea);
					contacto = linea.split(",");
					Linea lineaTelef = new Linea();
					lineaTelef.setNumero(contacto[0]);
					lineaTelef.setNombreUsuario(contacto[1]);
					lineaTelef.setPlan(factory.generarPlanByName(contacto[2]));
					for(int i=3;i<7;i++) {
						if(i<contacto.length)
							lineaTelef.addNumeroAmigo(contacto[i]);
					}
					guardarLinea(lineaTelef);
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}
	public boolean exists(String numero) {
		boolean resp=false;
		try {
			File f = new File("lineas.txt");
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
