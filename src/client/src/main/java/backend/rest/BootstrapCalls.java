package backend.rest;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

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
		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);
		WebResource webResource = client
				   .resource(REST_URI + "test");
		
		Address respons = webResource
				.accept("Content-Type", "application/json")
                .get(Address.class);
		
		LOG.log(Level.INFO,respons.toString());
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
		LOG.log(Level.INFO,respons.toString());

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

		LOG.log(Level.INFO,respons.toString());
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

		LOG.log(Level.INFO,respons.toString());
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

		LOG.log(Level.INFO,respons.toString());
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
		LOG.log(Level.INFO,respons.toString());
		return respons;
	}

}
