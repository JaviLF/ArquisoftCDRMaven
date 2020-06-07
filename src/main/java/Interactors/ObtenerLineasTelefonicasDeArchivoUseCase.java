package Interactors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Entities.Linea;
import Entities.PlanFactory;

public class ObtenerLineasTelefonicasDeArchivoUseCase {
	
	public List<String> ObtenerLineasDeArchivo(Path path){
		List<String> lineas=new ArrayList<String>();
		try {
			File f = path.toFile();
			if(f.exists()) {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String linea;
				linea = br.readLine();//header
				linea = br.readLine();//firstline
				while(linea != null) {
					lineas.add(linea);
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return lineas;
	}
	
}

