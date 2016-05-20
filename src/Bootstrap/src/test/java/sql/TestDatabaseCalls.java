package sql;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;

import backend.json.Swarm;
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
	public void isPeerIDExisting()
	{
		//Prepare
		String id = "deca4450-1dd0-11e6-b6ba-3e1d05defe78";
		String fakeID = "00";
		database.addPeer(id, "192.160.7.5", true, "2016-05-19 16:59:40");
		
		//Test
		assertEquals(true, database.isPeerIDExisting(id));
		assertEquals(false, database.isPeerIDExisting(fakeID));
	}
	
	@org.junit.Test
	public void blacklistedIP()
	{
		database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5", true, "2016-05-19 16:59:40");
		database.addPeer("fdf27a64-1dd0-11e6-b6ba-3e1d05defe78", "192.161.7.6", false, "2016-05-19 17:00:00");
		database.addPeer("06806786-1dd1-11e6-b6ba-3e1d05defe78", "192.160.7.7", true, "2016-05-19 18:18:18");
		
		//Test
		assertEquals(true,database.isBlacklisted("192.160.7.5"));
		assertEquals(false,database.isBlacklisted("192.161.7.6"));
		assertEquals(true,database.isBlacklisted("192.160.7.7"));	
	}
	
	@org.junit.Test
	public void addSwarmDB()
	{
		//Prepare
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String uuidClient2 = "deca4450-1dd0-11e6-b6ba-3e1d05defe78";
		String uuidClient3 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String swarmID2 = "deca4450-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID3 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		
		String fileChecksum1 ="";
		String fileChecksum2 ="";
		String fileChecksum3 ="";
		
		String metadataChecksum1 = "";
		String metadataChecksum2 = "";
		String metadataChecksum3 = "";
		
		
		
		
		//Test
		database.addSwarmDB(uuidClient1, 10, "Frozen", fileChecksum1, metadataChecksum1, swarmID1);
		database.addSwarmDB(uuidClient2, 5, "Pirates", fileChecksum2, metadataChecksum2, swarmID2);
		database.addSwarmDB(uuidClient3, 7, "Pretty", fileChecksum3, metadataChecksum3, swarmID3);
	}
		
	@org.junit.Test
	public void getSwarm()
	{
		//Prepare
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		//Execute
		database.addSwarmDB(uuidClient1, blockCount, filename, fileChecksum1, metadataChecksum1, swarmID1);
		Swarm swarm = database.getSwarm(swarmID1);
		
		//Test
		assertEquals(filename, swarm.getFilename());
		assertEquals(blockCount,swarm.getBlockCount());
	}
	
	@org.junit.Test
	public void isSwarmExisting()
	{
		//prepare
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		//Execute
		database.addSwarmDB(uuidClient1, blockCount, filename, fileChecksum1, metadataChecksum1, swarmID1);
		
		//Test
		assertEquals(true, database.isSwarmExisting(swarmID1));
		assertEquals(false, database.isSwarmExisting(filename));
	}
	
	
	@org.junit.Test
	public void addPeer()
	{
		database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5", true, "2016-05-19 16:59:40");
		database.addPeer("fdf27a64-1dd0-11e6-b6ba-3e1d05defe78", "192.161.7.6", false, "2016-05-19 17:00:00");
		database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5",false, "2016-05-19 16:59:40");

	assertEquals(true ,database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5", false, "2016-05-19 16:59:40"));
	assertEquals(true, database.addPeer("fdf27a64-1dd0-11e6-b6ba-3e1d05defe78", "192.161.7.6", false, "2016-05-19 17:00:00"));
	assertEquals(true, database.addPeer("deca4450-1dd0-11e6-b6ba-3e1d05defe78", "192.160.7.5",false, "2016-05-19 16:59:40"));
	}
	
	@org.junit.Test
	public void addBootstrapServer()
	{
		String ip = "192.180.0.1";
		String name = "Berlin";
		int clientcount = 10;
		int servercount = 4;
		
		database.addBootstrapServer(ip, name, clientcount, servercount);
		
		assertEquals(true, database.addBootstrapServer(ip, name, clientcount, servercount));
	}
	
}
