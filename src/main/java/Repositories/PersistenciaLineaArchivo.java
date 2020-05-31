package Repositories;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaLinea;

public class PersistenciaLineaArchivo implements PersistenciaLinea{
	public void guardarLinea(Linea linea) {
		try {
			File f = new File("lineas.txt");
			FileWriter fw;
			BufferedWriter bw;
			String datosLinea=linea.getNumero()+"%"+linea.getNombreUsuario()+"%"+linea.getPlan().getId();
			if(linea.getPlan().getId()==3) {
				for(int i=0;i<4;i++) {
					if(i<linea.getNumerosAmigos().size()) {
						datosLinea.concat("%");
						datosLinea.concat(linea.getNumerosAmigos().get(i));
					} else {
						datosLinea=datosLinea+"%"+null;
					}
				}
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
				/*do { //find Linea
					line = br.readLine();
					System.out.println(line);
					contacto = line.split("%");	
				}while(contacto[0]!=numero);*/
				
				linea.setNumero(contacto[0]);
				linea.setNombreUsuario(contacto[1]);
				Plan plan=new PlanPrepago();
				if(Integer.parseInt(contacto[2])==2)
					plan=new PlanPostpago();
				if(Integer.parseInt(contacto[2])==3) {
					plan=new PlanWow();
					for(int j=0;j<4;j++) {
						if(contacto[j+3]!=null) {
							linea.addNumeroAmigo(contacto[j+3]);
						}
					}
				}
				linea.setPlan(plan);
				
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return linea;
	}
}
