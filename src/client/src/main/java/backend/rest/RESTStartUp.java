package backend.rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import backend.json.Bootstraps;

public class RESTStartUp implements Runnable {
	 static final String BASE_URI = "http://localhost:1337/rest/";
	// //Bootstrap rest adress

	public void run() {
		
		BootstrapCalls calls = new BootstrapCalls();
		Bootstraps servers = calls.getBootstraps();
		System.out.println(servers.getbootstraps());
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
			System.out.println("HTTP server was started");
			while (true) {
				Thread.sleep(1000);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("HTTP server was stopped");
			server.stop(0);
			server = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("HTTP server was stopped (force)");
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
