package backend.rest;

import backend.json.Address;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;

public interface calls {
	
	 static final String REST_URI = "http://localhost:9999/rest/rest/";
	
	  public void getFile();
	  public Address getTest();
	  public Peers getPeers();
	  public Bootstraps getBootstraps();
	  public Blacklist getBlacklist();
	  public SwarmsHelper getSwarms();
	  public Swarm getSwarm(String id);
	  
	  
}
