package backend.rest;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import backend.api.calls;
import backend.json.Bootstraps;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RESTStartUp implements Runnable {
	 static final String BASE_URI = "http://localhost:1337/rest/";
	// //Bootstrap rest adress
	 private static final Logger LOG = Logger.getLogger(RESTStartUp.class.getName());
	public void run() {
		
		calls calls = new BootstrapCalls();
		
		Bootstraps servers = calls.getBootstraps();
		LOG.log(Level.INFO,servers.getbootstraps().toString());
		
		calls.getTest();
		calls.getPeers();
		calls.getBootstraps();
		calls.getBlacklist();
		calls.getSwarms();
		calls.getSwarm("1");
		
		HttpServer server = null;
		try {
			server = HttpServerFactory.create(BASE_URI);
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

		//BootstrapCalls calls = new BootstrapCalls();
		// calls.getTest();
		// calls.getPeers();
		// calls.getBootstraps();
		// calls.getBlacklist();
		// calls.getSwarms();
		// calls.getSwarm("1");

	}
}
