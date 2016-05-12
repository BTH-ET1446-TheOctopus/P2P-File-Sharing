package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class ServerPeerTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select distinct blacklist from serverspeers";
  		rdrs = test.runquery(readquery);
 		
  		/**
 		 * 
 		 * For Writing Database record from serverdb
 		 *
 		 */
  		
  		 		String writequery="insert into serverspeers (id,latestIP,blacklist,timestamp)"
 				+ "VALUES ('1016,'192.12.58.40',0,2016-05-10 03:14:07)";	
		test.Update(writequery);
		
		/**
 		 * 
 		 * For Reading Database record from serverdb
 		 *
 		 */
		
		try{
		
 			while(rdrs.next()){
 		         rddata = rdrs.getString("blacklist");	         	
 		      }
		}
		catch (Exception e) {
	        System.out.print("Problem with the sql query: ");
	        System.out.println(e.getMessage());
			}
		
		/**
 		 * 
 		 * Assert method for comparing query written and query read.
 		 *
 		 */
 		assertEquals(wrdata, rddata);
 		test.closeconnect();
	}
}