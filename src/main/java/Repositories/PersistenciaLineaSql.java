package Repositories;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import Entities.Linea;
import Entities.Plan;
import Entities.PlanPostpago;
import Entities.PlanPrepago;
import Entities.PlanWow;
import Gateways.PersistenciaLinea;

public class PersistenciaLineaSql implements PersistenciaLinea{
	public void createTable() {
		Connection c = null;
		Statement stmt = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:test.db");
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS LINEA" +
	                      "(TELEFONO TEXT PRIMARY KEY	NOT NULL, " +
	                      "PROPIETARIO	TEXT	NOT NULL, " + 
	                      "PLAN	INT	NOT NULL, " + 
	                      "NUMERO_AMIGO_1	TEXT, " + 
	                      "NUMERO_AMIGO_2	TEXT, " +
	                      "NUMERO_AMIGO_3	TEXT, " +
	                      "NUMERO_AMIGO_4	TEXT)";
	       System.out.println(sql);
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	}
	public void guardarLinea(Linea linea) {
		this.createTable();
		Connection c = null;
	    Statement stmt = null;
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:test.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String values;
	       if(linea.getPlan().getId()!=3) {
	    	   values="VALUES ("+linea.getNumero()+",'"+linea.getNombreUsuario()+"',"+linea.getPlan().getId()+","+null+","+null+","+null+","+null+");";
	       }else {
	    	   values="VALUES ("+linea.getNumero()+",'"+linea.getNombreUsuario()+"',"+linea.getPlan().getId();
	    	   List<String>numeros=linea.getNumerosAmigos();
	    	   
	    	   System.out.println(numeros.size());
	    	   for(int i=0;i<numeros.size();i++) { //concatenamos el campo de numeros amigos de la linea
	    		   values=values.concat(",");
	    		   values=values.concat(numeros.get(i));
	    	   }
	    	   while(numeros.size()<4) {	//completamos a 4 si la lista no tiene 4 elementos
	    		   numeros.add(null);
	    		   values=values.concat(",");
	    		   values=values+null;
	    	   }
	    	   values=values.concat(");");
	    	   
	       }
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
	public Linea getLinea(String numero) {
		Connection c = null;
	    Statement stmt = null;
	    Linea linea=null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM LINEA WHERE TELEFONO ="+numero+ ";" );
		      
		      while ( rs.next() ) {
		    	  linea=new Linea();
		    	  String numeroLinea = rs.getString("TELEFONO");
		    	  String  usuario = rs.getString("PROPIETARIO");
		    	  int planId  = rs.getInt("PLAN");
		    	  String  telf_amigo1 = rs.getString("NUMERO_AMIGO_1");
		    	  String  telf_amigo2 = rs.getString("NUMERO_AMIGO_2");
		    	  String  telf_amigo3 = rs.getString("NUMERO_AMIGO_3");
		    	  String  telf_amigo4 = rs.getString("NUMERO_AMIGO_4");
		    	  
		    	  linea.setNombreUsuario(usuario);
			      linea.setNumero(numeroLinea);
		    	  Plan plan=new PlanPrepago();
			      if (planId==2){
			    	  plan=new PlanPostpago();
			      }
			      if (planId==3){
			    	  plan=new PlanWow();
			      }
			      linea.setPlan(plan);
			      linea.addNumeroAmigo(telf_amigo1);
			      linea.addNumeroAmigo(telf_amigo2);
			      linea.addNumeroAmigo(telf_amigo3);
			      linea.addNumeroAmigo(telf_amigo4);
			      
			         /*System.out.println( "ID = " + id );
			         System.out.println( "NUMEROLLAMANTE = " + name );
			         System.out.println( "NUMEROLLAMADO = " + name2 );
			         System.out.println( "HORALLAMADA = " + age );
			         System.out.println( "DURACIONLLAMADA = " + address );
			         System.out.println( "TARIFA = " + salary );
			         System.out.println();*/
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
}
