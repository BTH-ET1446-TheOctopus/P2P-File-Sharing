package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class sqltest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		//test.connector("root", "farhan123", "clientdb", "127.0.0.1", "3306");
 		readquery="select distinct ip from peersarray";
 		rdrs = test.runquery(readquery);
		String writequery="insert into peersarray (uniquefileid,peers)"
					+ "VALUES ('abc123','192.168.1.22')";
		
 		//Writing Data to ClientDB
		test.Update(writequery);
 		//Reading Data from ClientDB
		try {
 			System.out.println();
 			while(rdrs.next()){
 		         //Retrieve by column name			
 		         rddata = rdrs.getString("peers");	         	
 		      }
 	    }
 	    catch (Exception e) {
 	        System.out.println("Exception in query method:\n" + e.getMessage());
 	    }
		
		//Compare the Written and Read Data
		assertEquals(wrdata, rddata);
 		test.closeconnect();
	}
}