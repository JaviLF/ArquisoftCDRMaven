import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PersistenciaCDRArchivo implements PersistenciaCDR{
	public void guardarCDR(CDR cdr,int id_tarificacion) {
		try {
			int id=getNextId();
			int idTarif=getNextIdTarificacion();
			cdr.setId(id);
			File f = new File("cdrs.txt");
			FileWriter fw;
			BufferedWriter bw;
			if(f.exists()){
				fw = new FileWriter(f,true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(cdr.getNumeroLlamante() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
			}else {
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.write("id%telf_origen%telf_destino%HoraLlamada%DuracionLlamada%Tarifa%ID_Tarificacion\n");
				bw.write(cdr.getId()+"%"+cdr.getNumeroLlamante() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa() + "%" + id_tarificacion);
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
				cdr.setHoraLlamada(Integer.parseInt(contacto[3]));
				cdr.setDuracionLlamada(Float.parseFloat(contacto[4]));
				
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
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
	public int getNextIdTarificacion(){
		int id=1;
		int idAux=0;
		try {
			File f = new File("cdrs.txt");
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//skip header and other CDRs
				linea = br.readLine();//next line
				while(linea != null) {
					String [] contacto = linea.split("%");
					idAux=Integer.parseInt(contacto[5]);
					linea = br.readLine();
					//id=id+1;
				}
				id=idAux+1;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
}
