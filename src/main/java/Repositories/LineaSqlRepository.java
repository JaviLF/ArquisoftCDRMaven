package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTOs.LineaDTO;

import Entities.Linea;

import Entities.PlanFactory;

import Gateways.PersistenciaLinea;

public class LineaSqlRepository implements PersistenciaLinea{
	public void createTable() {
		Connection c = null;
		Statement stmt = null;
	     
	    try { 
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS LINEA" +
	                      "(TELEFONO TEXT PRIMARY KEY	NOT NULL, " +
	                      "PROPIETARIO	TEXT	NOT NULL, " + 
	                      "PLAN	TEXT	NOT NULL, " + 
	                      "NUMERO_AMIGO_1	TEXT, " + 
	                      "NUMERO_AMIGO_2	TEXT, " +
	                      "NUMERO_AMIGO_3	TEXT, " +
	                      "NUMERO_AMIGO_4	TEXT)";
	       
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	}
	public void guardarLinea(LineaDTO DTO) {
		Linea linea=DTO.getLinea();
		this.createTable();
		if(exists(linea.getNumero())==false) {
			Connection c = null;
		    Statement stmt = null;
		    try {
		       Class.forName("org.sqlite.JDBC");
		       c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		       c.setAutoCommit(false);
		       System.out.println("Opened database successfully");
	
		       stmt = c.createStatement();
		       String values=generateValues( DTO);
		       String sql = "INSERT INTO LINEA (TELEFONO,PROPIETARIO,PLAN,NUMERO_AMIGO_1,NUMERO_AMIGO_2,NUMERO_AMIGO_3,NUMERO_AMIGO_4) " + values;
		       System.out.println(sql);
		       stmt.executeUpdate(sql);
		       stmt.close();
		       c.commit();
		       c.close();
		    } catch ( Exception e ) {
		       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		       System.exit(0);
		    }
		}
	}
	public String generateValues(LineaDTO DTO) {
		Linea linea=DTO.getLinea();
		List<String> numerosAmigos=DTO.getNumerosAmigos();
		String values;
	       if(linea.getPlan().getNombre()!="wow"||numerosAmigos==null) {
	    	   values="VALUES ("+linea.getNumero()+",'"+linea.getNombreUsuario()+"','"+linea.getPlan().getNombre()+"',"+null+","+null+","+null+","+null+");";
	       }else {
	    	   values="VALUES ("+linea.getNumero()+",'"+linea.getNombreUsuario()+"','"+linea.getPlan().getNombre()+"'";
	    	   
	    	   for(int i=0;i<numerosAmigos.size();i++) { //concatenamos el campo de numeros amigos de la linea
	    		   values=values.concat(",");
	    		   values=values.concat(numerosAmigos.get(i));
	    	   }
	    	   int j=numerosAmigos.size();
	    	   while(j<4) {	//completamos a 4 si la lista no tiene 4 elementos
	    		   j=j+1;
	    		   values=values.concat(",");
	    		   values=values+null;
	    	   }
	    	   values=values.concat(");"); 
	       }
	      return values;
	}
	
	public Linea getLineaByNumero(String numero) {
		Connection c = null;
	    Statement stmt = null;
	    Linea linea=null;
	    this.createTable();
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LINEA WHERE TELEFONO ="+numero+ ";" );
		      
		      while ( rs.next() ) {
		    	  linea=new Linea();
		    	  List<String> numerosAmigos=new ArrayList<String>();
		    	  
		    	  String numeroLinea = rs.getString("TELEFONO");
		    	  String  usuario = rs.getString("PROPIETARIO");
		    	  String  plan  = rs.getString("PLAN");
		    	  String  telf_amigo1 = rs.getString("NUMERO_AMIGO_1");
		    	  String  telf_amigo2 = rs.getString("NUMERO_AMIGO_2");
		    	  String  telf_amigo3 = rs.getString("NUMERO_AMIGO_3");
		    	  String  telf_amigo4 = rs.getString("NUMERO_AMIGO_4");
		    	  
		    	  linea.setNombreUsuario(usuario);
			      linea.setNumero(numeroLinea);
			      PlanFactory factory=new PlanFactory();
			      if(telf_amigo1==null) {
			    	  linea.setPlan(factory.generarPlanByName(plan,null));
			      }else{
			    	  
			    	  numerosAmigos.add(telf_amigo1);
			    	 
			    	  numerosAmigos.add(telf_amigo2);
			    	 
			    	  numerosAmigos.add(telf_amigo3);
			    	  
			    	  numerosAmigos.add(telf_amigo4);
			    	  
			    	  linea.setPlan(factory.generarPlanByName(plan,numerosAmigos));
			      }
				  
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
	    return linea;
	}
	public List<Linea> getLineas(){
		List<Linea> lista= new ArrayList<Linea>();
		Connection c = null;
	    Statement stmt = null;
	    Linea linea=null;
	    this.createTable();
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LINEA;" );
		      
		      while ( rs.next() ) {
		    	  linea=new Linea();
		    	  List<String> numerosAmigos=new ArrayList<String>();
		    	  
		    	  String numeroLinea = rs.getString("TELEFONO");
		    	  String  usuario = rs.getString("PROPIETARIO");
		    	  String  plan  = rs.getString("PLAN");
		    	  String  telf_amigo1 = rs.getString("NUMERO_AMIGO_1");
		    	  String  telf_amigo2 = rs.getString("NUMERO_AMIGO_2");
		    	  String  telf_amigo3 = rs.getString("NUMERO_AMIGO_3");
		    	  String  telf_amigo4 = rs.getString("NUMERO_AMIGO_4");
		    	  
		    	  linea.setNombreUsuario(usuario);
			      linea.setNumero(numeroLinea);
			      PlanFactory factory=new PlanFactory();
			      if(telf_amigo1==null) {
			    	  linea.setPlan(factory.generarPlanByName(plan,null));
			      }else{
			    	  numerosAmigos.add(telf_amigo1);
			    	  numerosAmigos.add(telf_amigo2);
			    	  numerosAmigos.add(telf_amigo3);
			    	  numerosAmigos.add(telf_amigo4);
			    	  
			    	  linea.setPlan(factory.generarPlanByName(plan,numerosAmigos));
			      }
				  lista.add(linea);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		
		return lista;
	}
	
	
	public boolean exists(String numero) {
		boolean resp=false;
		Connection c = null;
	    Statement stmt = null;
	    this.createTable();
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LINEA;" );
		      
		      while ( rs.next() ) {
		    	  if(Integer.parseInt(rs.getString("TELEFONO"))==Integer.parseInt(numero)) {
		    		  resp=true;
		    	  }
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
	    
		return resp;
	}
}
