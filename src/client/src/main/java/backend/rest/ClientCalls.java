package backend.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import backend.json.Address;
import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;

import java.util.logging.Level;
import java.util.logging.Logger;
public class ClientCalls {
    
	private static final Logger LOG = Logger.getLogger(ClientCalls.class.getName());
	/**
	 * If no bootstrap server is available the client can request peers 
	 * from remote clients.
	 * 
	 * @param clientIP The ip address of the client and the port nr
	 * @return The peers that the remote client has connected to
	 */
    public Peers getPeers(String clientIP)
    {
    	DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(clientIP + "/rest/peers");
		
		Peers respons = webResource
				.accept("Content-Type", "application/json")
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
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(clientIP + "/rest/file/" + fileID);
		
		Chunks respons = webResource
				.accept("Content-Type", "application/json")
                .get(Chunks.class);
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	/**
	 * Return the file chunk on the remote client. This data is then used to write 
	 * to a file binary on client side in order to build the file. The file is complete
	 * when all chunks has been recived from remote client. 
	 * 
	 * @param clientIP		The IP address of the remote client + port nr
	 * @param fileID		The UUID for the file
	 * @param chunk			The requested chunk for the remote client
	 * @return				Specific chunk, header and data
	 */
	public Chunk getFileChunk(String clientIP, String fileID, int chunk)
	{
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(clientIP + "/rest/file/" + fileID + "/" + chunk);
		
		Chunk respons = webResource
				.accept("Content-Type", "application/json")
                .get(Chunk.class);
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
}
