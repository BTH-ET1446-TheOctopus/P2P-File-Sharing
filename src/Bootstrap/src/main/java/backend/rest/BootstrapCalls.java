package backend.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import backend.json.Sync;

/**
 * Used for communication between different bootstrap servers
 *
 */
public class BootstrapCalls {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 * Use in bootstrap to get information from other bootstraps in order to have the data required for 
	 * syncing database whit theirs
	 * @param bootstrapUrl the URL of the bootstrap server that going to fetch information
	 * @return the sync class
	 */
	public Sync getSync(String bootstrapIP){	
		Client client = ClientBuilder.newClient();
    	
		Sync respons = client.target(bootstrapIP)
    			.path("/sync/")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Sync.class);
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
}
