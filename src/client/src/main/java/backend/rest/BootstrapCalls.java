package backend.rest;

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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
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
			SSLContext sslcontext = null;
			try {
				sslcontext = SSLContext.getInstance("TLS");

				sslcontext.init(null, new TrustManager[] { new X509TrustManager() {
					public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					}

					public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					}

					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}

				} }, new java.security.SecureRandom());
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
		Swarm respons = client.target(bootstrapUrl).path("swarms/" + id).request(MediaType.APPLICATION_JSON)
				.get(Swarm.class);

		LOG.log(Level.INFO, respons.toString());
		return respons;
	}

	public ID addSwarm(String filename, int blockCount, String fileChecksum, String metadataChecksum) {

		// TODO, not working ATM

		ID respons = client.target(bootstrapUrl)// + "?filename=" +
												// filename +
												// "&blockCount=" +
												// blockCount +
												// "&fileChecksum="
												// + fileChecksum +
												// "&metadataChecksum="
												// +
												// metadataChecksum)
				.path("swarms/").queryParam("filename", '"' + filename + '"').queryParam("blockCount", blockCount)
				.queryParam("fileChecksum", '"' + fileChecksum + '"')
				.queryParam("metadataChecksum", '"' + metadataChecksum + '"')
				// .queryParam("filename", filename)
				// .queryParam("blockCount", blockCount)
				// .queryParam("fileChecksum", fileChecksum)
				// .queryParam("metadataChecksum", metadataChecksum)
				.request(MediaType.TEXT_PLAIN_TYPE).post(null, ID.class);

		return respons;
	}
}
