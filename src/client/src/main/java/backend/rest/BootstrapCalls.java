package backend.rest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import backend.Settings;
import backend.api.calls;
import backend.json.Address;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.ID;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;
import backend.thread.BootstrapHelloThread;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BootstrapCalls implements calls {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Client client;
	private String bootstrapUrl;

	public BootstrapCalls() {
		if (Settings.ENABLE_HTTPS) {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
			    public X509Certificate[] getAcceptedIssuers(){return null;}
			    public void checkClientTrusted(X509Certificate[] certs, String authType){}
			    public void checkServerTrusted(X509Certificate[] certs, String authType){}
			}};

			// Install the all-trusting trust manager
			SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("TLS");
			    sc.init(null, trustAllCerts, new SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			    ;
			}
			
			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier((s1, s2) -> true).build();
		} else {
			client = ClientBuilder.newClient();
		}
		
		bootstrapUrl = ((Settings.ENABLE_HTTPS) ? "HTTPS://" : "HTTP://") + Settings.DEFAULT_BOOTSTRAP_ADDRESS + ":"
				+ Settings.BOOTSTRAP_PORT + "/rest";

	}

	public ID getHello(String id) {
		ID respons = client.target(bootstrapUrl).path("hello").queryParam("id", id).request(MediaType.APPLICATION_JSON)
				.get(ID.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public Address getTest() {
		Address respons = client.target(bootstrapUrl).path("test").request(MediaType.APPLICATION_JSON)
				.get(Address.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public Peers getPeers() {
		Peers respons = client.target(bootstrapUrl).path("peers").request(MediaType.APPLICATION_JSON).get(Peers.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public Bootstraps getBootstraps() {
		Bootstraps respons = client.target(bootstrapUrl).path("bootstraps").request(MediaType.APPLICATION_JSON)
				.get(Bootstraps.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public Blacklist getBlacklist() {
		Blacklist respons = client.target(bootstrapUrl).path("blacklist").request(MediaType.APPLICATION_JSON)
				.get(Blacklist.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public SwarmsHelper getSwarms() {
		SwarmsHelper respons = client.target(bootstrapUrl).path("swarms").request(MediaType.APPLICATION_JSON)
				.get(SwarmsHelper.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public Swarm getSwarm(String id) {
		Swarm respons = client.target(bootstrapUrl).path("swarms/" + id)
				.queryParam("clientID", BootstrapHelloThread.getClientId())
				.request(MediaType.APPLICATION_JSON)
				.get(Swarm.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public ID addSwarm(String filename, int blockCount, String fileChecksum, String metadataChecksum) {

		// TODO, not working ATM

		ID respons = client.target(bootstrapUrl)
			.path("swarms")
			.queryParam("filename", filename)
			.queryParam("blockCount", blockCount)				
			.queryParam("fileChecksum", fileChecksum)
			.queryParam("metadataChecksum", metadataChecksum)
			.queryParam("clientID", BootstrapHelloThread.getClientId())
			.request(MediaType.APPLICATION_JSON)
			.post(null, ID.class);
		
		/*ID respons = client.target(bootstrapUrl)
				.path("swarms/")
				.queryParam("filename", '"' + filename + '"')
				.queryParam("blockCount", blockCount)
				.queryParam("fileChecksum", '"' + fileChecksum + '"')
				.queryParam("metadataChecksum", '"' + metadataChecksum + '"')
				// .queryParam("filename", filename)
				// .queryParam("blockCount", blockCount)
				// .queryParam("fileChecksum", fileChecksum)
				// .queryParam("metadataChecksum", metadataChecksum)
				.request(MediaType.TEXT_PLAIN_TYPE)
				.post(null, ID.class);*/

		return respons;
	}
}
