package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.BackendController;
import backend.api.BackendObserver;
import backend.api.datatypes.SwarmMetadataShort;
import backend.json.Swarm;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

public class Backend implements BackendController {
	private static final Logger LOG = Logger.getLogger(Backend.class.getName());

	private HashMap<String, SwarmEngager> activeSwarms;

	private BackendObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;

	public Backend(BackendObserver restObserver) {
		activeSwarms = new HashMap<String, SwarmEngager>();

		this.restObserver = restObserver;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();

		// TODO Start bootstrap hello thread (/hello)

		// TODO Start bootstrap data retrieval thread (/peers, /bootstraps, /blacklist, etc)

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
		List<SwarmMetadataShort> swarms = new ArrayList<SwarmMetadataShort>();

		swarms.add(new SwarmMetadataShort() {
			public String getId() {
				return "abc123";
			}

			@Override
			public String getFilename() {
				return "pom.xml";
			}
		});

		return swarms;
	}

	@Override
	public List<String> getPeers() {
		return Arrays.asList("192.168.0.100", "192.168.0.200");
	}

	@Override
	public List<String> getBootstraps() {
		return Arrays.asList("192.168.0.1", "192.168.0.2");
	}

	@Override
	public List<String> getBlacklist() {
		return Arrays.asList("8.8.8.8", "8.8.4.4");
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
