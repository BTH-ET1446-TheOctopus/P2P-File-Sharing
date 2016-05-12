package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class ServerFileTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select distinct filename from serverfile";
 		rdrs = test.runquery(readquery);
 		
 		/**
 		 * 
 		 * For Writing Database record from serverdb
 		 *
 		 */
 		
 		String writequery="insert into serverfile (filename,totalblocks,peer,peercount,uniquefileid,filechecksum,metadatachecksum)"
 				+ "VALUES ('kainaat',2000,'192.168.1.22',8,'1528',' 222957957',' 2229579571500')";	
 		test.Update(writequery);
		
 		/**
 		 * 
 		 * For Reading Database record from serverdb
 		 *
 		 */
 		
 		try{			
 
 		while(rdrs.next()){
 			rddata = rdrs.getString("filename");	         	
 		      }
 		}
		catch (Exception e) {
        System.out.print("Problem with the sql query: ");
        System.out.println(e.getMessage());
		}
		assertEquals(wrdata, rddata);
 		test.closeconnect();
	}
}