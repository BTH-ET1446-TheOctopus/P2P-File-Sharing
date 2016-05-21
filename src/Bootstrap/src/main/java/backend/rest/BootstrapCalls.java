package backend.rest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import backend.Settings;
import backend.json.Sync;

/**
 * Used for communication between different bootstrap servers
 *
 */
public class BootstrapCalls {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Client client;
	private String addressPrefix;
	private String addressSuffix;
	
	public BootstrapCalls() {
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
	 * Use in bootstrap to get information from other bootstraps in order to have the data required for 
	 * syncing database whit theirs
	 * @param bootstrapUrl the URL of the bootstrap server that going to fetch information
	 * @return the sync class
	 */
	public Sync getSync(String bootstrapIP) {
		
		
		Sync respons = client.target(addressPrefix + bootstrapIP + addressSuffix)
    			.path("/sync/")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Sync.class);
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
}
