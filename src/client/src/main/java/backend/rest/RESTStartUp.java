package backend.rest;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import backend.Backend;
import backend.Settings;
import backend.api.BackendObserver;
import backend.api.calls;
import backend.json.Bootstraps;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RESTStartUp implements Runnable {
	private static final Logger LOG = Logger.getLogger(RESTStartUp.class.getName());

	public void run() {
		// function to test connection client -> bootstrap -> client
		testBoostrapConnection();
		
		//testClientConnection();
		
		/**
		 * Start the HTTP server that is used for the rest calls. The URL that
		 * the Rest server will run on is determined in file settings.
		 */
		HttpServer server = null;
		try {
			server = HttpServerFactory.create(Settings.clientURL);
			server.start();
			LOG.log(Level.INFO, "HTTP server was started");
			while (true) {
				Thread.sleep(1000);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOG.log(Level.INFO, "HTTP server was stopped");
			server.stop(0);
			server = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("HTTP server was stopped (force)");
				LOG.log(Level.INFO, "HTTP server was stopped (force)");
				server.stop(0);
			}
		}
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

	private void testClientConnection() {
		Backend backend = new Backend(new BackendObserver() {

			@Override
			public void newSwarm(String id, String filename, int blockCount, String dateAdded) {
				// TODO Auto-generated method stub

			}

			@Override
			public void updateSwarmBlock(String id, int blockNumber, boolean correctChecksum) {
				// TODO Auto-generated method stub

			}

			@Override
			public void updateSwarm(double progress, double speed, List<String> peers, String timeToCompletion) {
				// TODO Auto-generated method stub

			}

			@Override
			public void searchResult(String clientAddress, String id, String filename, int blockCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void newSwarm(String id, String filename, int blockCount)
			{
				// TODO Auto-generated method stub
				
			}

		});
		
		backend.engageSwarm("abc123");
	}
}
