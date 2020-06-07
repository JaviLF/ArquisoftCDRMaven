package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ObtenerCDRsDesdeArchivoUseCase {
	public List<String> ObtenerCDRsDeArchivo(Path path){
		List<String> CDRs=new ArrayList<String>();
		try {
			File f = path.toFile();
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String cdr;
				cdr = br.readLine();//header
				cdr = br.readLine();//firstline
				while(cdr != null) {
					CDRs.add(cdr);
					cdr = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return CDRs;
	}
}
