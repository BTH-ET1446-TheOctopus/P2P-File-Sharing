package backend.rest;

import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
public class ClientCalls {
    
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * If no bootstrap server is available the client can request peers 
	 * from remote clients.
	 * 
	 * @param clientIP The ip address of the client and the port nr
	 * @return The peers that the remote client has connected to
	 */
    public Peers getPeers(String clientIP)
    {
    	Client client = ClientBuilder.newClient();
    	
    	Peers respons = client.target(clientIP)
    			.path("/rest/peers")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Peers.class);
		LOG.log(Level.INFO,respons.toString());
		return respons;
    }
    
    /**
     * Returns all available chunks the remote client has. This list can be used 
     * for requesting different chunks/parts of a file from a remote client
     * 
     * @param clientIP 		The ip address of the client and the port nr
     * @param fileID		The UUID for the file
     * @return				Existing chunks on connected client
     */
	public Chunks getFileChunks(String clientIP, String fileID)
	{
		Client client = ClientBuilder.newClient();
    	
		Chunks respons = client.target(clientIP)
    			.path("/rest/file/" + fileID)
    			.request(MediaType.APPLICATION_JSON)
    			.get(Chunks.class);
    	
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	/**
	 * Return the file chunk on the remote client. This data is then used to write 
	 * to a file binary on client side in order to build the file. The file is complete
	 * when all chunks has been recived from remote client. 
	 * 
	 * clientIP		The IP address of the remote client + port nr
	 * fileID		The UUID for the file
	 * chunk			The requested chunk for the remote client
	 * @return				Specific chunk, header and data
	 */
	public Chunk getFileChunk(String clientIP, String fileID, int chunk)
	{
		Client client = ClientBuilder.newClient();
    	
		Chunk respons = client.target(clientIP)
    			.path("/rest/file/" + fileID + "/" + chunk)
    			.queryParam("filename", "Robin hood")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Chunk.class);
		
		return respons;
	}
}
