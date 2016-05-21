package sql;

import static org.junit.Assert.*;
import org.junit.Test;

import backend.json.Peers;

public class TestgetInactivePeers {

	@Test
	public void getInactivePeersTest() {
		
		DatabaseCalls dbc = new DatabaseCalls();
		
		dbc.addPeer("sd1f3dg1f5dg", "192.168.5.4", true, "2016-05-21 13:20:00");
		dbc.addPeer("sd1f3dg1f5dh", "192.168.5.5", false, "2018-05-21 13:28:00");
		
		Peers s = dbc.getInactivePeers(180);
		
		assertNotNull(s.getpeers());
		assertEquals(false , s.getpeers().isEmpty());
		assertEquals(1 , s.getpeers().size());
		assertEquals("192.168.5.4" , s.getpeers().get(0));

	}

}
