package rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;


public class RESTStartUp {
    static final String REST_URI = "http://localhost:9999/rest/rest/"; //Bootstrap rest adress
    public static void main(String[] args) {
    	
    	try {
    		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9995).build();
        	ResourceConfig config = new ResourceConfig(Rest.class);
        	HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        	System.out.println("Press Enter to stop the server. ");
			System.in.read();
			server.stop(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 e.printStackTrace();
		}
       BootstrapCalls calls = new BootstrapCalls();
       calls.getTest();
       calls.getPeers();
       calls.getBootstraps();
       calls.getBlacklist();
       //calls.getSwarms();
       calls.getSwarm("1");
    	
    }
}
