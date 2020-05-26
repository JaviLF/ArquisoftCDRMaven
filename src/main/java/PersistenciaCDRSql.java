import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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
	                        " TARIFA			DOUBLE     	NOT NULL )"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	}
	public void guardarCDR(CDR cdr) {
		this.createTable();
		Connection c = null;
	    Statement stmt = null;
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:test.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");

	       stmt = c.createStatement();
	       String sql = "INSERT INTO CDR (ID,TELF_ORIGEN,TELF_DESTINO,HORALLAMADA,DURACIONLLAMADA,TARIFA) " +
	                      "VALUES ("+cdr.getId()+","+cdr.getNumeroLlamante()+","+cdr.getNumeroLlamado()+","
	                      +cdr.getHoraLlamada()+","+cdr.getDuracionLlamada()+","+cdr.getTarifa()+");";
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
	    return cdr;
	}
	/*public List<CDR> getAllCDRs(){
		
	}*/
}
