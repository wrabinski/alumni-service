package org.camunda.bpm.getstarted.alumniservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DBConn implements JavaDelegate{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:~/test";
	
	private final static Logger LOGGER = Logger.getLogger(DBConn.class.getName());

	// Database credentials
	static final String USER = "sa";
	static final String PASS = "";

	  public void execute(DelegateExecution execution) throws Exception {
		  String anrede = (String) execution.getVariable("anrede");
		  String name = (String) execution.getVariable("name");
		  String firstname = (String) execution.getVariable("firstname");
		  String pmail = (String) execution.getVariable("pmail");
		  String program = (String) execution.getVariable("program");
		  String nationality = (String) execution.getVariable("nationality");
//		  String theme = (String) execution.getVariable("theme");
		  String unimail = (String) execution.getVariable("unimail");
		  String degree = (String) execution.getVariable("degree");
//		  String date = (String) execution.getVariable("date");
		  String adress = (String) execution.getVariable("adress");
		  Double plz = (Double) execution.getVariable("plz");
		  String place = (String) execution.getVariable("place");
		  Boolean cz = (Boolean) execution.getVariable("cz");
		  Boolean af = (Boolean) execution.getVariable("af");
		  String Fachbereich = (String) execution.getVariable("Fachbereich");
	
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			//System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 3: Execute a query
			//System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			String sql = "INSERT INTO ALUMNI "
						+ "(ID,ANREDE,FIRSTNAME,NAME,PMAIL,NATIONALITY,DEGREE,UNIMAIL,ADRESS,PLZ,PLACE,CZ,AF,FIELD,PROGRAM) "
						+ "VALUES (default, '"+anrede+"', '"+firstname+"', '"+name+"', '"+pmail+"', '"+nationality+"', '"+degree+"', '"+unimail+"', '"+adress+"', "+plz+", '"+place+"', "+cz+", "+af+", '"+Fachbereich+"','"+program+"');";
			LOGGER.info(sql);
			
			stmt.executeUpdate(sql);

			
			
			// STEP 4: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		//System.out.println("Goodbye!");
	}
}

//INSERT INTO ALUMNI VALUES (default, 'Herr', 'waldi', 'Rabinski', 'private@mail.org', 'Deutschland', 'Master', 'uni@th.bra.de', 'Strasse 44', 21232, 'Place', true, true, 'Wirtschaft', 'Wirtschaftsinformatik');
//"INSERT INTO ALUMNI VALUES (default, '"+anrede+"', '"+firstname+"', '"+name+"', '"+pmail+"', '"+nationality+"', '"+degree+"', '"+unimail+"', '"+adress+"', "+plz+", '"+place+"', "+cz+", "+af+", '"+Fachbereich+"','"+program+"');"

