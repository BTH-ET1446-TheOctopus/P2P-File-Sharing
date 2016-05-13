package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class ClientPeersTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select distinct blacklist from clientpeers";
  		rdrs = test.runquery(readquery);
 		
  		/**
 		 * 
 		 * For Writing Database record from clientdb
 		 *
 		 */  		
  		 		String writequery="insert into clientpeers (id,latestIP,blacklist,timestamp,files,filecount)"
 				+ "VALUES ('Vf-866,'192.25.158.85',1,2016-05-12 08:19:07,'mysql-connector-5.1.39',156)";	
		test.Update(writequery);
		
		/**
 		 * 
 		 * For Reading Database record from clientdb
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