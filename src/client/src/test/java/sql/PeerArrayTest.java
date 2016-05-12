package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class PeerArrayTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select uniquefileid from peerarray";
 		rdrs = test.runquery(readquery);
 		
 		/**
 		 * 
 		 * For Writing Database record from clientdb
 		 *
 		 */
 		
 		String writequery="insert into peerarray (id,,uniquefileid,peers)"
 				+ "VALUES (5,'BFE2000','192.168.50.36')";	
 		test.Update(writequery);
		
 		/**
 		 * 
 		 * For Reading Database record from clientdb
 		 *
 		 */
 		
 		try{			
 
 		while(rdrs.next()){
 			rddata = rdrs.getString("id");	         	
 		      }
 		}
		catch (Exception e) {
        System.out.print("Problem with the sql query: ");
        System.out.println(e.getMessage());
		}
 		
 		/**
 		 * 
 		 * Comparing both read and write queries using assert method
 		 *
 		 */
 		
		assertEquals(wrdata, rddata);
 		test.closeconnect();
 		 		
	}
}