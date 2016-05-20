package sql;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import sql.DatabaseCalls;

public class addPeerArrayTest {

	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	@Test
	public void SuccessfuladdPeerArrayTest() 
	{ 
		//test to see if peers are added to the server database
		
		sqlconnector sc = new sqlconnector();
		ResultSet rs = null;
		
		//Initialize table columns
		
		String uniquefileid = null;
		String peers = null;
		
		
		DatabaseCalls dbc = new DatabaseCalls();

		dbc.addPeerArray("278f6d83-a707-4aee-8471-8ffc03c662a9", "192.168.45.98");
		
		//get info from the table
		rs = sc.runquery("SELECT * FROM peersarray where peers='192.168.45.98'");
		
		try {
			while(rs.next()){
			
				
				
				uniquefileid=rs.getString("uniquefileid");
				peers=rs.getString("peers");
				System.out.println(uniquefileid);			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}
		

		assertNotNull(uniquefileid);
		assertEquals("278f6d83-a707-4aee-8471-8ffc03c662a9", uniquefileid);
		
		assertNotNull(peers);
		assertEquals("192.168.45.98", peers);
		
		

	}
}
