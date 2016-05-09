package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Logger;


public class Dbase {

	private static final Logger LOG = Logger.getLogger(DBWrite.class.getName());
	
	

	public Dbase()
	{
		sqlconnector conect = new sqlconnector();
		conect.connector("root","nayyar123","serverdb","127.0.0.1","3306");
		
		String str = " INSERT INTO servers " + 
				"VALUES ('192.168.1.10', 'Server-1', '20:00:00', 1, 1)";
		//DataInsertion();
		conect.Update(str);	
	
	}
	
	public void DataInsertion()
	{  		
		sqlconnector conect = new sqlconnector();
		Statement st = conect.getStatement();
		try {
			st.executeQuery(" INSERT INTO servers " + 
			"VALUES ('192.168.1.10', 'Server-1', NULL, 1, 1)"); 

			
			st.executeUpdate("INSERT INTO serverfile  " + 					
			"VALUES ('star war', 10000, '192.168.2.140', 1, 1775, 'filechecksum', 'metadatachecksum' )");
			
			
			st.executeUpdate("INSERT INTO serverpeers  " + 
					"VALUES (20556, 0, '192.168.2.50', NULL, 2255, 'files004', 21 )");
			
		} 
		
		
		catch (SQLException e) {
			e.printStackTrace(); }
		conect.closeconnect();
	}
}
