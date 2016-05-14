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
	public void SuccessfuladdBootstrapServerTest() {
		
		sqlconnector sc = new sqlconnector();
		ResultSet rs = null;
		
		String ip = null;
		String name = null;
		String clientcount = null;
		String servercount = null;
		
		
		DatabaseCalls dbc = new DatabaseCalls();
		dbc.addBootstrapServer("192.168.2.5", "Backup01", 2, 1);
		
		rs = sc.runquery("SELECT * FROM servers");
		
		try {
			while(rs.next()){
				ip = rs.getString("ip");
				name=rs.getString("name");
				clientcount = rs.getString("clientcount");
				servercount = rs.getString("servercount");
			}
		} catch (SQLException ex){
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}
		
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
