package rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import json.Address;
import json.Blacklist;
import json.Bootstraps;
import json.Peers;
import json.Swarm;
import json.SwarmsHelper;

public class BootstrapCalls {
    static final String REST_URI = "http://localhost:9999/rest/rest/";
    
    
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
	
	public void getBootstraps(){
		Client client = ClientBuilder.newClient();
		
		Bootstraps respons = client.target(REST_URI)
				.path("bootstraps")
				.request(MediaType.APPLICATION_JSON)
				.get(Bootstraps.class);
		
		System.out.println(respons.toString());
	}
	
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
}
