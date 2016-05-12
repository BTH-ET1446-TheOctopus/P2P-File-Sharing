package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class ServersTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select name from servers where servercount >= 2 ";
  		rdrs = test.runquery(readquery);
  		
  		/**
 		 * 
 		 * For Writing Database record from serverdb
 		 *
 		 */
 		
 		String writequery="insert into servers (ip,name,timestamp,clientcount,servercount)"
 				+ "VALUES ('172.12.58.5','server-1',2016-05-19 08:14:07,12,3)";	
		test.Update(writequery);
		
		/**
 		 * 
 		 * For Reading Database record from serverdb
 		 *
 		 */
		
		try{
 			while(rdrs.next()){
 		         rddata = rdrs.getString("ip");	         	
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