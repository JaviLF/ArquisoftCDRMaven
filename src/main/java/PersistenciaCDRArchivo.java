import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class PersistenciaCDRArchivo implements PersistenciaCDR{
	public void guardarCDR(CDR cdr) {
		try {
			File f = new File("cdrs.txt");
			FileWriter fw;
			BufferedWriter bw;
			if(f.exists()){
				fw = new FileWriter(f,true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(cdr.getNumeroLlamante() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa());
			}else {
				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);
				bw.write("id%telf_origen%telf_destino%HoraLlamada%DuracionLlamada%Tarifa\n");
				bw.write(cdr.getId()+"%"+cdr.getNumeroLlamante() + "%" + cdr.getNumeroLlamado() + "%" + cdr.getHoraLlamada() + "%" + cdr.getDuracionLlamada() + "%" + cdr.getTarifa());
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
}
