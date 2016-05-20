package backend.rest;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import backend.Settings;
import backend.api.calls;
import backend.json.Bootstraps;

public class RESTStartUp implements Runnable {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private final ResourceConfig rc = new ResourceConfig().packages("backend.rest");

	private String bindAddress;

	public RESTStartUp(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	public void run() {
		// function to test connection client -> bootstrap -> client
		// testBoostrapConnection();

		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);

		if (bindAddress == null) {
			bindAddress = Settings.DEFAULT_CLIENT_ADDRESS;
		}

		String url = "http://" + bindAddress + ":" + Settings.CLIENT_PORT + "/";
		GrizzlyHttpServerFactory.createHttpServer(URI.create(url), rc);

		LOG.log(Level.INFO, "REST server started at {0}", url);
	}

	/**
	 * Function in order to test the connectivity to bootstrap. The function
	 * will show you some information that is retrieved from bootstrap if the
	 * function gives you error it could mean that bootstrap is not available.
	 * Otherwise that the port/ip address is incorrect.
	 */
	private void testBoostrapConnection() {
		/**
		 * Trying the connection to bootstrap
		 */
		calls calls = new BootstrapCalls();
		Bootstraps servers = calls.getBootstraps();
		LOG.log(Level.INFO, servers.getbootstraps().toString());

		calls.getTest();
		calls.getPeers();
		calls.getBootstraps();
		calls.getBlacklist();
		calls.getSwarms();
		calls.getSwarm("abc123");
	}
}
