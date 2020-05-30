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
		         int idNumber = this.getLastID(TABLAPLANES) + 1;
		         String sql = "INSERT INTO PLANES (ID,NOMBRE,CARACTERISTICA,PROPIAS) " + "VALUES ('" + idNumber + "','"+ dto.getNombre() + "','" + dto.getCaracteristica() + "','" + dto.getPropias() + "');" ;
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
	
	public int getLastID(String tableName) {
		Connection con = null;
	      Statement stmt = null;
	      int idNumber = -1;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         con = DriverManager.getConnection("jdbc:sqlite:test.db");
	         con.setAutoCommit(false);
	         stmt = con.createStatement();    
	         ResultSet rs = stmt.executeQuery("SELECT MAX(Id) FROM '" + tableName + "';");
	         try {
	        	 idNumber = ((Number) rs.getObject(1)).intValue();
	         }
	         catch (NumberFormatException e)
	         {
	        	 idNumber = -1;
	         }
	         //idNumber = rs.getInt("ID");
	         rs.close();
	         stmt.close();
	        // con.commit();
	         con.close();
	         
	         return idNumber;
	      } catch ( Exception e ) {
	         System.err.println("Exception in 127 getLastID() en PersistenciaPlanSql, " + e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
		return idNumber;
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
			         
			         if(name.equals("PREPAGO")) {
			        	 System.out.println("creo prepago....");
			        	 planes.add(new PlanPrepago());
			         }
			         if(name.equals("POSTPAGO")) {
			        	 planes.add(new PlanPostpago());
			         }
			         if(name.equals("WOW")) {
			        	 planes.add(new PlanWow());
			         }
			         
			
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			   }
			   System.out.println("ESta devolviendo....");
			   return planes;
		}
		else 
		{
			System.out.println("PLANES doesn't exist");
			return null;
		}
	}

	@Override
	public boolean planExists(String planName) {
		Connection con = null;
		Statement stmt = null;
		String message = "";
		 ResultSet rs;
	    boolean answer = false;
	//    Class.forName("org.sqlite.JDBC");
	//      con = DriverManager.getConnection("jdbc:sqlite:test.db");
	//      stmt = con.createStatement();    
	//      ResultSet rs = stmt.executeQuery("SELECT NOMBRE FROM '" + TABLAPLANES + "' WHERE NOMBRE = '" + planName + "'");
		try {
	      Class.forName("org.sqlite.JDBC");
	      con = DriverManager.getConnection("jdbc:sqlite:test.db");
	      stmt = con.createStatement();    
	      rs = stmt.executeQuery("SELECT NOMBRE FROM '" + TABLAPLANES + "' WHERE NOMBRE = '" + planName + "'");
	      //Object obj = rs;
	      //message = (String) rs.getObject(1);
	      //System.out.println("after");
	      try {
	    	  //message = (String) rs.getObject(1);
	    	  if (rs.next() == false) { 
	    		  //System.out.println("ResultSet in empty in Java"); 
	    		  answer = false;
	    		  } 
	    	  else { 
	    		  do { 
	    			  //String data = rs.getString("emp_name"); System.out.println(data);
	    			  answer = true;
	    			  } 
	    		  while (rs.next()); 
	    		  }

	    	  //Read more: https://javarevisited.blogspot.com/2016/10/how-to-check-if-resultset-is-empty-in-Java-JDBC.html#ixzz6NvbTcnDI
	    		  
	    		  //answer = true;
	    	  /*
	    	  else {
	    		  	if (rs.getObject(1) instanceof String) {
	    		  		System.out.println(message);
	    		  		answer = true;
	    		  	}
	    	  }
	    	  */
	    	  }
	      catch (Exception e)
	      {
	    	  System.err.println("shit here 225 Exception: "+e.getMessage());
	      }
	      rs.close();
	      stmt.close();
	      con.close();
	    } catch (Exception e) {
	      System.err.println("Fuck here 231 Exception: "+e.getMessage());
	    }
		return answer;
	}
	
}