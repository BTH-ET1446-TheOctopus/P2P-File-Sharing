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
    */
    public Address getTest(){		
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "test");
		
		Address respons = webResource
				.accept("Content-Type", "application/json")
                .get(Address.class);
		
		System.out.println(respons.toString());
		return respons;
		
	}

	public Peers getPeers(){		
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "peers");
		
		Peers respons = webResource
				.accept("Content-Type", "application/json")
                .get(Peers.class);
		
		System.out.println(respons.toString());
		return respons;
	}
	
	public Bootstraps getBootstraps(){
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "bootstraps");
		
		Bootstraps respons = webResource
				.accept("Content-Type", "application/json")
                .get(Bootstraps.class);
		
		System.out.println(respons.toString());
		return respons;
	}
	
	
	public Blacklist getBlacklist(){	
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "blacklist");
		
		Blacklist respons = webResource
				.accept("Content-Type", "application/json")
                .get(Blacklist.class);
		
		System.out.println(respons.toString());
		return respons;
	}
	
	public SwarmsHelper getSwarms(){	
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "swarms");
		
		SwarmsHelper respons = webResource
				.accept("Content-Type", "application/json")
                .get(SwarmsHelper.class);
		
		System.out.println(respons.toString());
		return respons;
	}
	
	
	
	public Swarm getSwarm(String id){
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "swarms/" + id);
		
		Swarm respons = webResource
				.accept("Content-Type", "application/json")
                .get(Swarm.class);
		
		System.out.println(respons.toString());
		return respons;
	}

}
