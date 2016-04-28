package rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class RESTStartUp implements Runnable {
	// static final String REST_URI = "http://localhost:9999/rest/rest/";
	// //Bootstrap rest adress

	public void run() {
		HttpServer server = null;
		try {
			URI baseUri = UriBuilder.fromUri("http://localhost/").port(1337).build();
			ResourceConfig config = new ResourceConfig(Rest.class);
			server = JdkHttpServerFactory.createHttpServer(baseUri, config);
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
		} finally {
			System.out.println("HTTP finally block");
			if (server != null) {
				server.stop(0);
			}
		}

		// BootstrapCalls calls = new BootstrapCalls();
		// calls.getTest();
		// calls.getPeers();
		// calls.getBootstraps();
		// calls.getBlacklist();
		// calls.getSwarms();
		// calls.getSwarm("1");

	}
}
