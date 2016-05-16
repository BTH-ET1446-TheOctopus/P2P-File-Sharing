package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.BackendController;
import backend.api.BackendObserver;
import backend.api.datatypes.SwarmMetadataShort;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

public class Backend implements BackendController {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private HashMap<String, SwarmEngager> activeSwarms;

	private BackendObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;

	private BootstrapHelloThread bootstrapHelloThread;
	private BootstrapDataThread bootstrapDataThread;
	
	private static Backend instance;
	
	public static Backend getInstance() {
		if (instance == null) {
			instance = new Backend();
		}
		
		return instance;
	}
	
	public void setObserver(BackendObserver restObserver) {
		this.restObserver = restObserver;
	}
	
	public Backend() {
		activeSwarms = new HashMap<String, SwarmEngager>();

		restObserver = null;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();

		bootstrapHelloThread = new BootstrapHelloThread(bootstrapCalls);
		bootstrapDataThread = new BootstrapDataThread(bootstrapCalls);

		bootstrapHelloThread.start();
		bootstrapDataThread.start();
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
	public List<SwarmMetadataShort> getSwarms() {
		List<Swarms> json_swarms = bootstrapDataThread.getSwarms();
		List<SwarmMetadataShort> swarms = new ArrayList<SwarmMetadataShort>();
		
		for (Swarms json_swarm : json_swarms) {
			swarms.add(new SwarmMetadataShort(json_swarm.getid(), json_swarm.getfilename()));
		}
		
		return swarms;
	}

	@Override
	public List<String> getPeers() {
		return bootstrapDataThread.getPeers();
	}

	@Override
	public List<String> getBootstraps() {
		return bootstrapDataThread.getBootstraps();
	}

	@Override
	public List<String> getBlacklist() {
		return bootstrapDataThread.getBlacklist();
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
