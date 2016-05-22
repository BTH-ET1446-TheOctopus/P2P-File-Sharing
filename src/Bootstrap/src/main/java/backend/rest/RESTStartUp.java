package backend.rest;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

//import com.sun.jersey.api.container.httpserver.HttpServerFactory;
//import com.sun.net.httpserver.HttpServer;

import backend.Settings;

public class RESTStartUp implements Runnable {
	private static final Logger LOG = Logger.getLogger(RESTStartUp.class.getName());

	public void run() {
		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);
		
		final ResourceConfig rc = new ResourceConfig().packages("backend.rest");
		
		final URI uri = URI.create(((Settings.ENABLE_HTTPS) ? "HTTPS://" : "HTTP://") + Settings.DEFAULT_BOOTSTRAP_ADDRESS
				+ ":" + Settings.BOOTSTRAP_PORT + "/");
		
		if (Settings.ENABLE_HTTPS) {
			SSLContextConfigurator sslContext = new SSLContextConfigurator();

			// set up security context
			sslContext.setKeyStoreFile("servercertificate"); // contains server keypair
			sslContext.setKeyStorePass("vyshu_09");
			sslContext.setTrustStoreFile("truststore_client"); // contains server
																// certificate
			sslContext.setTrustStorePass("vyshu_09");

			if (!sslContext.validateConfiguration(true)) {
				LOG.severe("context not valid");
				System.exit(0);
			}
			
			GrizzlyHttpServerFactory.createHttpServer(uri, rc, true,
					new SSLEngineConfigurator(sslContext).setClientMode(false).setNeedClientAuth(false));
		} else {
			GrizzlyHttpServerFactory.createHttpServer(uri, rc);
		}


		/*
		 * HttpServer server = null; try { server =
		 * HttpServerFactory.create(Settings.bootstrapURL); server.start();
		 * LOG.log(Level.INFO, "Bootstrap server was started at {0}",
		 * server.getAddress().toString()); while (true) { Thread.sleep(1000); }
		 * } catch (IllegalArgumentException e) { LOG.log(Level.SEVERE,
		 * e.toString(), e); } catch (InterruptedException e) { server.stop(0);
		 * server = null; LOG.log(Level.INFO, "Bootstrap server was stopped"); }
		 * catch (IOException e) { LOG.log(Level.SEVERE, e.toString(), e); }
		 * finally { if (server != null) { server.stop(0);
		 * LOG.log(Level.WARNING, "Bootstrap server was stopped by force"); } }
		 */
	}
}
