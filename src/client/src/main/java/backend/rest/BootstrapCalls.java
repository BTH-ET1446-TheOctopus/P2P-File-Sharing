package backend.rest;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import backend.json.Address;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Chunk;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;

public class BootstrapCalls {
    static final String REST_URI = "http://localhost:9999/rest/rest/";
    
    /*
    public void getFile(){
    	ClientResponse response = webResource
    			
    	
    	WebResource r = c.resource("http://localhost:8080/xyz");

		
		Chunk respons = client.target("http://localhost:1337")
				.path("/rest/file/1/1")
				.request(MediaType.APPLICATION_JSON)
				.get(Chunk.class);
		
		System.out.println(respons.getSequenceNumber());
    }
    
    public void getTest(){
    	Client client = ClientBuilder.newClient();
		
		Address respons = client.target(REST_URI)
				.path("test")
				.request(MediaType.APPLICATION_JSON)
				.get(Address.class);
		
		System.out.println(respons.toString());
		
	}
    
	public void getPeers(){
		Client client = ClientBuilder.newClient();
		
		Peers respons = client.target(REST_URI)
				.path("peers")
				.request(MediaType.APPLICATION_JSON)
				.get(Peers.class);
		
		System.out.println(respons.toString());
	}
	*/
	
	public void getBootstraps(){
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "bootstraps");
		
		Bootstraps response = webResource
				.accept("Content-Type", "application/json")
                .get(Bootstraps.class);
		
		System.out.println(response.toString());
	}
	
	/*
	public void getBlacklist(){
		Client client = ClientBuilder.newClient();
		
		Blacklist respons = client.target(REST_URI)
				.path("blacklist")
				.request(MediaType.APPLICATION_JSON)
				.get(Blacklist.class);
		
		System.out.println(respons.toString());
	}
	
	public void getSwarms(){
		
		Client client = ClientBuilder.newClient();
		
		SwarmsHelper respons = client.target(REST_URI)
				.path("swarms")
				.request(MediaType.APPLICATION_JSON)
				.get(SwarmsHelper.class);
		
		System.out.println(respons.toString());
	}
	
	
	
	public void getSwarm(String id){
		
		Client client = ClientBuilder.newClient();

		Swarm respons = client.target(REST_URI)
				.path("swarms/")
				.path(id)
				.request(MediaType.APPLICATION_JSON)
				.get(Swarm.class);
		
		System.out.println(respons.toString());
	}
	*/
}
