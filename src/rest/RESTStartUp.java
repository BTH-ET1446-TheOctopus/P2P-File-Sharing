package rest;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;


public class RESTStartUp {
    static final String BASE_URI = "http://localhost:8888/rest/"; //Client rest adress
    static final String REST_URI = "http://localhost:9999/rest/rest/"; //Bootstrap rest adress
    public static void main(String[] args) {
        /*
    	try {
            HttpServer server = HttpServerFactory.create(BASE_URI);
            server.start();
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
       BootstrapCalls calls = new BootstrapCalls();
       calls.getTest();
       calls.getPeers();
       calls.getBootstraps();
       calls.getBlacklist();
       calls.getSwarms();
       calls.getSwarm("1");
    	
    }
}
