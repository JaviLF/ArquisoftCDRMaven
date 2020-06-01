package Repositories;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
 

public class PersistenciaCDRSql implements PersistenciaCDR{
	public void createTable() {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS CDR" +
	                        "(ID INTEGER PRIMARY KEY	AUTOINCREMENT	NOT NULL," +
	                        " TELF_ORIGEN	TEXT  		NOT NULL, " + 
	                        " TELF_DESTINO		TEXT  		NOT NULL, " + 
	                        " FECHALLAMADA		TEXT     	NOT NULL, " +
	                        " HORALLAMADA		TEXT     	NOT NULL, " + 
	                        " DURACIONLLAMADA	TEXT     	NOT NULL, " +
	                        " TARIFA			DOUBLE     	NOT NULL, " +
	                        " ID_TARIFICACION	INT     	NOT NULL )"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	}
	public void guardarCDR(CDR cdr,int id_tarificacion) {
		this.createTable();
		//cdr.setId(getNextId());
		Connection c = null;
	    Statement stmt = null;
	   
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:test.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");
	       stmt = c.createStatement();
	       String sql = "INSERT INTO CDR (TELF_ORIGEN,TELF_DESTINO,FECHALLAMADA,HORALLAMADA,DURACIONLLAMADA,TARIFA,ID_TARIFICACION) " +
	                      "VALUES ("+cdr.getNumeroLlamante()+","+cdr.getNumeroLlamado()+",'"
	                      +(cdr.getFecha().replace('-', 'a')) +"','"+(cdr.getHoraLlamada().replace(':', 'a')) 
	                      +"','"+ (cdr.getDuracionLlamada().replace(':', 'a'))+"',"+cdr.getTarifa()+","+id_tarificacion+");";
	       System.out.println(sql);
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.commit();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    cdr.setId(getLastId());
	}
	public CDR getCDR(int id) {
		Connection c = null;
	    Statement stmt = null;
	    CDR cdr=null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM CDR WHERE ID ="+id+ ";" );
		      
		      while ( rs.next() ) {
		    	  cdr=new CDR();
		    	  int CDRid = rs.getInt("id");
		    	  String  telf_origen = rs.getString("TELF_ORIGEN");
		    	  String  telf_destino = rs.getString("TELF_DESTINO");
		    	  String fecha = rs.getString("FECHALLAMADA");
		    	  String hora  = rs.getString("HORALLAMADA");
		    	  System.out.println(hora);
		    	  String  duracion = rs.getString("DURACIONLLAMADA");
		    	  System.out.println(duracion);
			      double tarifa = rs.getDouble("TARIFA");
			      cdr.setNumeroLlamante(telf_origen);
			      cdr.setNumeroLlamado(telf_destino);
			      cdr.setFecha(fecha.replace('a', '-'));
			      cdr.setHoraLlamada(hora.replace('a', ':'));
			      cdr.setDuracionLlamada(duracion.replace('a', ':'));
			      cdr.setId(CDRid);
			      cdr.setTarifa(tarifa);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
	    return cdr;
	}
	public int getLastId(){
		int id=1;
		Connection c = null;
	    Statement stmt1 = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt1 = c.createStatement();
		      ResultSet rs = stmt1.executeQuery( "SELECT * FROM CDR WHERE ID = (SELECT MAX(ID) FROM CDR);" );
		      id=rs.getInt("ID");
		      rs.close();
		      stmt1.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		return id;
	}
	
	public int saveFromArchive(String archive,int id_t) {
		int count=0;
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
					//cdr.setId(getNextId());
					cdr.setNumeroLlamante(contacto[0]);
					cdr.setNumeroLlamado(contacto[1]);
					cdr.setFecha(contacto[2]);
					cdr.setHoraLlamada(contacto[3]);
					cdr.setDuracionLlamada(contacto[4]);
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
	public int saveAndTarifyFromArchive(String archive,int id_t) {
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
		Connection c = null;
	    Statement stmt = null;
	    CDR cdr=null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM CDR WHERE ID_TARIFICACION ="+id+ ";" );
		      
		      while ( rs.next() ) {
		    	  cdr=new CDR();
		    	  int CDRid = rs.getInt("id");
		    	  String  telf_origen = rs.getString("TELF_ORIGEN");
		    	  String  telf_destino = rs.getString("TELF_DESTINO");
		    	  String fecha = rs.getString("FECHALLAMADA");
		    	  String hora  = rs.getString("HORALLAMADA");
		    	  String  duracion = rs.getString("DURACIONLLAMADA");
			      double tarifa = rs.getDouble("TARIFA");
			      cdr.setNumeroLlamante(telf_origen);
			      cdr.setNumeroLlamado(telf_destino);
			      cdr.setFecha(fecha.replace('a', '-'));
			      cdr.setFecha(fecha.replace('a', '-'));
			      cdr.setHoraLlamada(hora.replace('a', ':'));
			      cdr.setDuracionLlamada(duracion.replace('a', ':'));
			      cdr.setId(CDRid);
			      cdr.setTarifa(tarifa);
			      lista.add(cdr);
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
}
