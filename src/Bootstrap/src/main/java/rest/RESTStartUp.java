package rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class RESTStartUp implements Runnable {
	private static final Logger LOG = Logger.getLogger(RESTStartUp.class.getName());
	
	static final String BASE_URI = "http://localhost:9999/rest/";

	public void run() {
		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);
		
		HttpServer server = null;
		try {
			server = HttpServerFactory.create(BASE_URI);
			server.start();
			LOG.log(Level.INFO, "Bootstrap server was started at {0}", server.getAddress().toString());
			while (true) {
				Thread.sleep(1000);
			}
		} catch (IllegalArgumentException e) {
			LOG.log(Level.SEVERE, e.toString(), e);
		} catch (InterruptedException e) {
			server.stop(0);
			server = null;
			LOG.log(Level.INFO, "Bootstrap server was stopped");
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.toString(), e);
		} finally {
			if (server != null) {
				server.stop(0);
				LOG.log(Level.WARNING, "Bootstrap server was stopped by force");
			}
		}
	}
}
