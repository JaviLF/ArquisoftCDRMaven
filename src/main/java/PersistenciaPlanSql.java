import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class PersistenciaPlanSql implements PersistenciaPlan{
	
	static final String TABLAPLANES= "PLANES";

	public void connect() {
		Connection c = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	}
	
	@Override
	public void savePlan(PlanDTO dto) {
		if (this.tableExists(TABLAPLANES))
		{
			Connection c = null;
		      Statement stmt = null;
		      try {
		         Class.forName("org.sqlite.JDBC");
		         c = DriverManager.getConnection("jdbc:sqlite:test.db");
		         c.setAutoCommit(false);
		         stmt = c.createStatement();    
		         String sql = "INSERT INTO PLANES (ID,NOMBRE,CARACTERISTICA,PROPIAS) " + "VALUES ('3','"+ dto.getNombre() + "','" + dto.getCaracteristica() + "','" + dto.getPropias() + "');" ;
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch ( Exception e ) {
		         System.err.println("Exception in 41 guardarPlan() en PersistenciaPlanSql, " + e.getClass().getName() + ": " + e.getMessage() );
		         System.exit(0);
		      }
			System.out.println("Plan " + dto.getNombre() + " created successfully");
			
		}
		else
		{
			this.createTable(TABLAPLANES);
			Connection c = null;
		      Statement stmt = null;
		      
		      try {
		         Class.forName("org.sqlite.JDBC");
		         c = DriverManager.getConnection("jdbc:sqlite:test.db");
		         c.setAutoCommit(false);
		         stmt = c.createStatement();
		         String sql = "INSERT INTO PLANES (ID,NOMBRE,CARACTERISTICA,PROPIAS) " + "VALUES ('1','"+ dto.getNombre() + "','" + dto.getCaracteristica() + "','" + dto.getPropias() + "');" ;
		         stmt.executeUpdate(sql);
		         stmt.close();
		         c.commit();
		         c.close();
		      } catch ( Exception e ) {
		         System.err.println("Exception in 95 savePlan() en PersistenciaPlanSql, " + e.getClass().getName() + ": " + e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Records created successfully");
		}
		
	}

	private void createTable(String name) {
		Connection c = null;
		Statement stmt = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         stmt = c.createStatement();
	         String sql = "CREATE TABLE PLANES " +
	                        "(ID INT PRIMARY KEY     NOT NULL," +
	                        " NOMBRE	TEXT  		NOT NULL, " + 
	                        " CARACTERISTICA		TEXT  		NOT NULL, " + 
	                        " PROPIAS		TEXT     	NOT NULL)"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( "Exception in 66 createPlanesTable(), " + e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table " + name + " created successfully");
	}

	private boolean tableExists(String name) {
		Connection con = null;
	    boolean answer = false;
		try {
	      Class.forName("org.sqlite.JDBC");
	      con = DriverManager.getConnection("jdbc:sqlite:test.db");
	      DatabaseMetaData meta = con.getMetaData();
	      ResultSet tables = meta.getTables(null, null, name, null);
		  		if (tables.next())
		  		  answer =  true;
	      con.close();
	    } catch (Exception e) {
	      System.err.println("Exception: "+e.getMessage());
	    }
		return answer;
	}

	@Override
	public List<Plan> loadPlans() {
		List<Plan> planes = new ArrayList<Plan>();
		if (this.tableExists(TABLAPLANES))
		{
			Connection c = null;
			Statement stmt = null;
			   try {
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:test.db");
			      c.setAutoCommit(false);
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT * FROM PLANES;" );
			      int id;
			      String  name;
			      String  characteristic;
			      String propias;  
			      
			      while ( rs.next() ) {
			         id = rs.getInt("id");
			         name = rs.getString("NOMBRE");
			         characteristic = rs.getString("CARACTERISTICA");
			         propias = rs.getString("PROPIAS"); 
			         System.out.println( "ID = " + id );
			         System.out.println( "NOMBRE = " + name );
			         System.out.println( "CARACTERISTICA = " + characteristic );
			         System.out.println( "PROPIAS = " + propias );
			         System.out.println();
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			   }
			   return null;
		}
		else 
		{
			System.out.println("PLANES doesn't exist");
			return null;
		}
	}

}
