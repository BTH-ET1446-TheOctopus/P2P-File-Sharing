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
 		ResultSet wrrs;
 		String wrdata="";
 		String rddata="";
 		test.connector("root", "farhan123", "serverdb", "127.0.0.1", "3306");
 		readquery="select distinct ip from servers";
 		rdrs = test.runquery(readquery);
		String writequery="insert into servers (ip,name,timestamp,clientcount,servercount)" 
				+ "VALUES ('192.168.1.20','server-1','2015-05-11 20:00:00','10','3')";
		
 		//Writing Data to ServerDB
		test.Update(writequery);
 		//Reading Data from ServerDB
		try {
 			System.out.println();
 			while(rdrs.next()){
 		         //Retrieve by column name			
 		         rddata = rdrs.getString("ip");	         	
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