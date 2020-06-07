package Repositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entities.Tarificacion;
import Gateways.PersistenciaTarificacion;

public class TarificacionSqlRepository implements PersistenciaTarificacion{
	public void createTable() {
		Connection c = null;
		Statement stmt = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "CREATE TABLE IF NOT EXISTS TARIFICACION" +
	                      "(ID INTEGER PRIMARY KEY	AUTOINCREMENT NOT NULL, " +
	                      "FECHA	TEXT	NOT NULL, " + 
	                      "TIPO	TEXT	NOT NULL)";
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
	public void guardarTarificacion(Tarificacion tarificacion) {
		this.createTable();
		//tarificacion.setId(getNextId());
		Connection c = null;
	    Statement stmt = null;
	    LocalDateTime now=LocalDateTime.now();
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");
	       stmt = c.createStatement();
	       String sql = "INSERT INTO TARIFICACION (FECHA,TIPO) " +
	                      "VALUES ('"+now.toString()
	                      +"','"+tarificacion.getTipo()+"');";
	       System.out.println(sql);
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.commit();
	       c.close();
	       tarificacion.setFecha(now.toString());
	       tarificacion.setId(getLastId());
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	}
	public List<Tarificacion> getTarificaciones(){
		List<Tarificacion> lista=new ArrayList<Tarificacion>();
		Connection c = null;
	    Statement stmt = null;
	    Tarificacion tarificacion=null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM TARIFICACION;" );
		      
		      while ( rs.next() ) {
		    	  tarificacion=new Tarificacion();
		    	  int CDRid = rs.getInt("id");
		    	  String fecha = rs.getString("FECHA");
		    	  String tipo  = rs.getString("TIPO");
			      tarificacion.setFecha(fecha.replace('a', '-'));
			      tarificacion.setTipo(tipo);
			      tarificacion.setId(CDRid);
			      lista.add(tarificacion);
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
	public Tarificacion getTarificacionById(int id) {
		Connection c = null;
	    Statement stmt = null;
	    Tarificacion tarificacion=null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM TARIFICACION WHERE ID ="+id+ ";" );
		      
		      while ( rs.next() ) {
		    	  tarificacion=new Tarificacion();
		    	  int CDRid = rs.getInt("id");
		    	  String fecha = rs.getString("FECHA");
		    	  String tipo  = rs.getString("TIPO");
			      tarificacion.setFecha(fecha.replace('a', '-'));
			      tarificacion.setTipo(tipo);
			      tarificacion.setId(CDRid);
			      
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
	    return tarificacion;
	}
	public int getLastId(){
		int id=1;
		Connection c = null;
	    Statement stmt1 = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:CLARO.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt1 = c.createStatement();
		      ResultSet rs = stmt1.executeQuery( "SELECT * FROM TARIFICACION WHERE ID = (SELECT MAX(ID) FROM TARIFICACION);" );
		      id=rs.getInt("id");
		      rs.close();
		      stmt1.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		return id;
	}
}
