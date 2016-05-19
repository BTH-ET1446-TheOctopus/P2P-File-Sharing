package sql;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import sql.DatabaseCalls;

public class TestDatabaseCalls {
	DatabaseAPI database = new DatabaseCalls();
	
	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	@org.junit.Test
	public void SuccessfuladdBootstrapServerTest() { //Test write to 'servers' table in serverdb
			
			sqlconnector sc = new sqlconnector();
			ResultSet rs = null;
			
			//Initialize table columns
			String ip = null;
			String name = null;
			String clientcount = null;
			String servercount = null;
			
			//Call the method to put some sample data into it to do the testing
			//dbc.addBootstrapServer("192.168.2.5", "Backup01", 2, 1);
			database.addBootstrapServer("192.168.2.5", "Backup01", 2, 1);
			
			//get info from the table
			rs = sc.runquery("SELECT * FROM bootstrapserver where ip='192.168.2.5'");
			
			try {
				while(rs.next()){
					//Retrieve by column name
					ip = rs.getString("ip");
					name=rs.getString("name");
					clientcount = rs.getString("clientcount");
					servercount = rs.getString("servercount");
				}
			} catch (SQLException ex){
				// handle any errors
				LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
				LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
				LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
			} finally  {
				sc.closeconnect();
			}
			
			/* Test to see if the same sample data written into the table?
			 * After each test run, try to insert a different "primary key"
			 * or you can just 'truncate' your table in command line to delete previous data	
			 * NOTE: Truncate your data just for the test, in real application running,
			 * You do not truncate any table, unless you know what you're doing!
			 */
			
			assertNotNull(ip);
			assertEquals("192.168.2.5", ip);
			
			assertNotNull(name);
			assertEquals("Backup01", name);
			
			assertNotNull(clientcount);
			assertEquals("2", clientcount);
			
			assertNotNull(servercount);
			assertEquals("1", servercount);

		}
	
	@org.junit.Test
	public void testBlacklistedIP()
	{
		database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5", true, "2016-05-19 16:59:40");
		database.addPeer("fdf27a64-1dd0-11e6-b6ba-3e1d05defe78", "192.161.7.6", false, "2016-05-19 17:00:00");
		database.addPeer("06806786-1dd1-11e6-b6ba-3e1d05defe78", "192.160.7.7", true, "2016-05-19 18:18:18");
		
		assertEquals(true,database.isBlacklisted("192.160.7.5"));
		assertEquals(false,database.isBlacklisted("192.161.7.6"));
		assertEquals(true,database.isBlacklisted("192.161.7.7"));	
	}
}
