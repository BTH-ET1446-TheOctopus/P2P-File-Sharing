package sql;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import sql.DatabaseCalls;

public class addBootstrapServerTest {

	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	@Test
	public void SuccessfuladdBootstrapServerTest() { //Test write to 'servers' table in serverdb
		
		sqlconnector sc = new sqlconnector();
		ResultSet rs = null;
		
		//Initialize table columns
		String ip = null;
		String name = null;
		String clientcount = null;
		String servercount = null;
		
		
		DatabaseCalls dbc = new DatabaseCalls();
		//Call the method to put some sample data into it to do the testing
		dbc.addBootstrapServer("192.168.2.5", "Backup01", 2, 1);
		
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
}
