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
 		
 		String writequery="insert into serverspeers (id,latestIP,blacklist,timestamp)"
 				+ "VALUES ('1016,'192.12.58.40',0,2016-05-10 03:14:07)";	
		test.Update(writequery);
		
 			while(rdrs.next()){
 		         rddata = rdrs.getString("blacklist");	         	
 		      }
 	   
 		assertEquals(wrdata, rddata);
 		test.closeconnect();
	}
}