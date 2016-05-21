package backend.rest;

import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jfree.util.Log;

import backend.Settings;;

public class ClientCalls {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Client client;
	private String addressPrefix;
	private String addressSuffix;
	
	
	public ClientCalls() {
		if (Settings.ENABLE_HTTPS) {
			// Create a trust manager that does not validate certificate chains
			SSLContext sslcontext = null;
			try {
				sslcontext = SSLContext.getInstance("TLS");
		    
				sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
				    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
				    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
				    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }

				}}, new java.security.SecureRandom());
			} catch (KeyManagementException e) {
				LOG.log(Level.SEVERE, e.toString(), e);
				System.exit(0);
			} catch (NoSuchAlgorithmException e) {
				LOG.log(Level.SEVERE, e.toString(), e);
				System.exit(0);
			}
			
			client = ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
		} else {
			client = ClientBuilder.newClient();
		}
		
		addressPrefix = ((Settings.ENABLE_HTTPS) ? "HTTPS://" : "HTTP://");
		addressSuffix = ":" + Settings.BOOTSTRAP_PORT + "/rest";
	}
	
	/**
	 * If no bootstrap server is available the client can request peers 
	 * from remote clients.
	 * 
	 * @param clientIP The ip address of the client and the port nr
	 * @return The peers that the remote client has connected to
	 */
    public Peers getPeers(String clientIP) {
    	Peers respons = client.target(addressPrefix + clientIP + addressSuffix)
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
	public Chunks getFileChunks(String clientIP, String fileID) {
		Chunks respons = client.target(addressPrefix + clientIP + addressSuffix)
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
	public Chunk getFileChunk(String clientIP, String fileID, int chunk) {
		Chunk respons = client.target(addressPrefix + clientIP + addressSuffix)
    			.path("file/" + fileID + "/" + chunk)
    			.queryParam("filename", "Robin hood")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Chunk.class);
		
		return respons;
	}

	public Response search(String clientIP,String filename, String ip, int hopLimit)
	{		
		Response respons=null;
		LOG.log(Level.INFO,"clientIP: " +clientIP +" Filename: " +filename +" IP: "+ip + " HopLimit: " +hopLimit);
		try{
		Client client = ClientBuilder.newClient();
		         respons = client.target(addressPrefix + clientIP + addressSuffix)
				.path("search")
				.queryParam("filename", filename)			
				.queryParam("ip", ip)							
				.queryParam("hopLimit", hopLimit)
				.request(MediaType.APPLICATION_JSON)
				.post(null);
		        System.out.print(respons);		
		}
		catch (Exception e) {
			LOG.log(Level.INFO,"Cannot connect to clientIP"+clientIP+ " Error:"+ e.getMessage());		
		}
		return respons;
		
	}

	public Response searchResult(String clientIP, String id, Integer blockCount, String filename, String fileChecksum, String metadataChecksum) {
		Response respons = null;
		LOG.log(Level.INFO, "In clientcalls.SearchResult: "+"clientIP: " +clientIP +" id: "+id+" Filename: " +filename);
		try {
		Client client = ClientBuilder.newClient();
				respons = client.target(addressPrefix+clientIP+addressSuffix)
				.path("searchresult")
				.queryParam("clientIP", clientIP)
				.queryParam("id", id)				
				.queryParam("blockCount", blockCount)
				.queryParam("filename", filename)
				.queryParam("fileChecksum", fileChecksum)
				.queryParam("metadataChecksum", metadataChecksum)
				.request(MediaType.APPLICATION_JSON)
				.post(null);
		System.out.print(respons);
		} 
		catch (Exception e)	{
			LOG.log(Level.INFO,"Cannot connect to clientIP"+clientIP+ " Error:"+ e.getMessage());	
		}
		return respons;
	}
}	
