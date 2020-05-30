import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
	                        "(ID INT PRIMARY KEY     NOT NULL," +
	                        " TELF_ORIGEN	TEXT  		NOT NULL, " + 
	                        " TELF_DESTINO		TEXT  		NOT NULL, " + 
	                        " HORALLAMADA		INT     	NOT NULL, " + 
	                        " DURACIONLLAMADA	DOUBLE     	NOT NULL, " +
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
		Connection c = null;
	    Statement stmt = null;
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:test.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");
	       stmt = c.createStatement();
	       String sql = "INSERT INTO CDR (ID,TELF_ORIGEN,TELF_DESTINO,HORALLAMADA,DURACIONLLAMADA,TARIFA,ID_TARIFICACION) " +
	                      "VALUES ("+cdr.getId()+","+cdr.getNumeroLlamante()+","+cdr.getNumeroLlamado()+","
	                      +cdr.getHoraLlamada()+","+cdr.getDuracionLlamada()+","+cdr.getTarifa()+","+id_tarificacion+");";
	       stmt.executeUpdate(sql);
	       stmt.close();
	       c.commit();
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
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
		    	  int hora  = rs.getInt("HORALLAMADA");
			      double  duracion = rs.getDouble("DURACIONLLAMADA");
			      double tarifa = rs.getDouble("TARIFA");
			      cdr.setNumeroLlamante(telf_origen);
			      cdr.setNumeroLlamado(telf_destino);
			      cdr.setHoraLlamada(hora);
			      cdr.setDuracionLlamada(duracion);
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
	public int getNextId(){
		int id=1;
		Connection c = null;
	    Statement stmt = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM CDR WHERE ID = (SELECT MAX(ID) FROM CDR);" );
		      id=rs.getInt("id");
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		return id;
	}
	public int getNextId_Tarificacion(){
		int id=0;
		Connection c = null;
	    Statement stmt = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM CDR WHERE ID_TARIFICACION = (SELECT MAX(ID_TARIFICACION) FROM CDR);" );
		      id=rs.getInt("ID_TARIFICACION");
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
			  id=1;
		      //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      //System.exit(0);
		   }
		return id;
	}
	public void saveFromArchive(String archive) {
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
					contacto = linea.split(",");
					CDR cdr = new CDR();
					cdr.setNumeroLlamante(contacto[1]);
					cdr.setNumeroLlamado(contacto[2]);
					//aqui fecha
					cdr.setHoraLlamada(Integer.parseInt(contacto[3]));
					cdr.setDuracionLlamada(Float.parseFloat(contacto[4]));
					//guardarCDR(cdr);
					linea = br.readLine();
				}
				br.close();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
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
		    	  int hora  = rs.getInt("HORALLAMADA");
			      double  duracion = rs.getDouble("DURACIONLLAMADA");
			      double tarifa = rs.getDouble("TARIFA");
			      cdr.setNumeroLlamante(telf_origen);
			      cdr.setNumeroLlamado(telf_destino);
			      cdr.setHoraLlamada(hora);
			      cdr.setDuracionLlamada(duracion);
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
