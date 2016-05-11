package backend;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.util.Log;

import backend.api.BackendController;
import backend.api.BackendObserver;
import backend.json.Swarm;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;
import sql.DBWrite;

public class Backend implements BackendController {
	private static final Logger LOG = Logger.getLogger(DBWrite.class.getName());
	
	private BackendObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;
	
	HashMap<String, SwarmEngager> activeSwarms;

	public Backend(BackendObserver restObserver) {
		this.restObserver = restObserver;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();
		
		activeSwarms = new HashMap<String, SwarmEngager>();
	}

	public boolean engageSwarm(String id) {
		if (activeSwarms.containsKey(id)) {
			LOG.log(Level.WARNING, "Cannot engage swarm id={0}: has already been engaged", id);
			return false;
		}
		
		SwarmEngager swarmEngager = new SwarmEngager(id, restObserver, bootstrapCalls, clientCalls);
		swarmEngager.start();
		
		activeSwarms.put(id, swarmEngager);
		
		return true;
	}
	
	public boolean disengageSwarm(String id) {
		SwarmEngager swarmEngager = activeSwarms.get(id);
		if (swarmEngager == null) {
			LOG.log(Level.WARNING, "Cannot disengage swarm id={0}: has not been engaged", id);
			return false;
		}
		
		swarmEngager.interrupt();
		
		activeSwarms.remove(id);
		
		return true;
	}

	@Override
	public List<Swarm> getSwarms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPeers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBootstraps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBlacklist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSwarm(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchSwarm(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDark(boolean dark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Swarm getSwarm(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSwarm(String id) {
		disengageSwarm(id);
	}
	
}
