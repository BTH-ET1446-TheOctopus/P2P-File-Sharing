package rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BootstrapCalls {
    static final String REST_URI = "http://localhost:9999/rest/rest/";
    
    public void getTest(){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("test");
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
    
	public void getPeers(){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("peers");
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	public void getBootstraps(){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("bootstraps");
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	public void getBlacklist(){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("blacklist");
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	public void getSwarms(){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("swarms");
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
	
	
	public void getSwarm(String id){
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI).path("swarms").path(id);
        
        ClientResponse response = service.accept("application/json")
                .get(ClientResponse.class);
        
        if (response.getStatus() != 200) {
 		   throw new RuntimeException("Failed : HTTP error code : "
 			+ response.getStatus());
 		}
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
		System.out.println(output);
	}
	
}
