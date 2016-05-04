package rest;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class RESTStartUp implements Runnable {
	static final String BASE_URI = "http://localhost:9999/rest/";

	public void run() {
		HttpServer server = null;
		try {
			server = HttpServerFactory.create(BASE_URI);
			server.start();
			System.out.println("Bootstrap server was started at " + server.getAddress().toString());
			while (true) {
				Thread.sleep(1000);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Bootstrap server was stopped");
			server.stop(0);
			server = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("Bootstrap server was stopped (force)");
				server.stop(0);
			}
		}
	}
}
