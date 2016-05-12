package sql;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import sql.sqlconnector;

public class ClientFileTest {
	@org.junit.Test
	
	public void dbtest() {
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet rdrs;
 		String wrdata="";
 		String rddata="";
 		readquery="select distinct filename from clientfile";
 		rdrs = test.runquery(readquery);
 		
 		/**
 		 * 
 		 * For Writing Database record from clientdb
 		 *
 		 */
 		
 		String writequery="insert into clientfile (filename,totalblocks,peers,peercount,uniquefileid,filechecksum,metadatachecksum)"
 				+ "VALUES ('Shahkaar101',5040,'192.168.15.1',5,'SM-1528',' 958957945',' 958957945005')";	
 		test.Update(writequery);
 		
	}
}