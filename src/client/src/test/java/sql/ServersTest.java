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
 		
 		String writequery="insert into servers (ip,name,timestamp,clientcount,servercount)"
 				+ "VALUES ('172.12.58.5','server-1',2016-05-19 08:14:07,12,3)";	
		test.Update(writequery);
		
 			while(rdrs.next()){
 		         rddata = rdrs.getString("ip");	         	
 		      }
 	   
		assertEquals(wrdata, rddata);
 		test.closeconnect();
	}
}