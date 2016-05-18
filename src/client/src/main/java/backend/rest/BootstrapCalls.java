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
import backend.json.ID;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BootstrapCalls implements calls{
    static final String REST_URI = "http://localhost:9999/rest/rest/";
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public ID getHello(){
		Client client = ClientBuilder.newClient();
     	
 		ID respons = client.target(Settings.BOOTSTRAP_URL)
     			.path("hello")
     			.request(MediaType.APPLICATION_JSON)
     			.get(ID.class);
 		
 		LOG.log(Level.INFO,respons.toString());
 		return respons;
	}
    public Address getTest(){	
    	Client client = ClientBuilder.newClient();
    	
    	Address respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("test")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Address.class);
    	
    	
    	
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}

	public Peers getPeers(){	
		
    	Client client = ClientBuilder.newClient();
    	
    	Peers respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("peers")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Peers.class);
		
		LOG.log(Level.INFO,respons.toString());

		return respons;
	}
	
	public Bootstraps getBootstraps(){
		
		Client client = ClientBuilder.newClient();
    	
		Bootstraps respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("bootstraps")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Bootstraps.class);
		
		
		
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	
	public Blacklist getBlacklist(){	
		
		Client client = ClientBuilder.newClient();
    	
		Blacklist respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("blacklist")
    			.request(MediaType.APPLICATION_JSON)
    			.get(Blacklist.class);
		
		
		
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	public SwarmsHelper getSwarms(){	
		
		Client client = ClientBuilder.newClient();
    	
		SwarmsHelper respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("swarms")
    			.request(MediaType.APPLICATION_JSON)
    			.get(SwarmsHelper.class);
		
		
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}
	
	
	
	public Swarm getSwarm(String id){
		
		Client client = ClientBuilder.newClient();
    	
		Swarm respons = client.target(Settings.BOOTSTRAP_URL)
    			.path("swarms/" + id)
    			.request(MediaType.APPLICATION_JSON)
    			.get(Swarm.class);
		
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}

}


