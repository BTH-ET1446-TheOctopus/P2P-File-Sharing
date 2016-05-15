package backend.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.DefaultClientConfig;

import backend.Settings;
import backend.api.calls;
import backend.json.Address;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Chunk;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BootstrapCalls implements calls{
    static final String REST_URI = "http://localhost:9999/rest/rest/";
    private static final Logger LOG = Logger.getLogger(BootstrapCalls.class.getName());

    public Address getTest(){	
    	Client client = ClientBuilder.newClient();
    	
    	Address respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("test")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Address.class);
    	
    	
    	/*
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "test");
		
		Address respons = webResource
				.accept("Content-Type", "application/json")
                .get(Address.class);
		*/
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}

	public Peers getPeers(){	
		
    	Client client = ClientBuilder.newClient();
    	
    	Peers respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("peers")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Peers.class);
		
		/*
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "peers");
		
		Peers respons = webResource
				.accept("Content-Type", "application/json")
                .get(Peers.class);
                
        */
		LOG.log(Level.INFO,respons.toString());

		return respons;
	}
	
	public Bootstraps getBootstraps(){
		
		Client client = ClientBuilder.newClient();
    	
		Bootstraps respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("bootstraps")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Bootstraps.class);
		
		
		/*
		
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "bootstraps");
		
		Bootstraps respons = webResource
				.accept("Content-Type", "application/json")
                .get(Bootstraps.class);

		*/
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	
	public Blacklist getBlacklist(){	
		
		Client client = ClientBuilder.newClient();
    	
		Blacklist respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("blacklist")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Blacklist.class);
		
		
		/*
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "blacklist");
		
		Blacklist respons = webResource
				.accept("Content-Type", "application/json")
                .get(Blacklist.class);
		*/
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	public SwarmsHelper getSwarms(){	
		
		Client client = ClientBuilder.newClient();
    	
		SwarmsHelper respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("swarms")
    			.request(MediaType.APPLICATION_JSON)
    			.get(SwarmsHelper.class);
		
		/*
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "swarms");
		
		SwarmsHelper respons = webResource
				.accept("Content-Type", "application/json")
                .get(SwarmsHelper.class);
		
		*/
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	
	
	public Swarm getSwarm(String id){
		
		Client client = ClientBuilder.newClient();
    	
		Swarm respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("swarms/" + id)
    			.request(MediaType.APPLICATION_JSON)
    			.get(Swarm.class);
		/*
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(Settings.BOOTSTRAP_URL + "swarms/" + id);
		
		Swarm respons = webResource
				.accept("Content-Type", "application/json")
                .get(Swarm.class);
                
        */
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}

}
