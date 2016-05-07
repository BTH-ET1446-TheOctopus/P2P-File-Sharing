package backend;

import backend.api.RestObserver;
import backend.api.calls;
import backend.json.Address;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.SwarmsHelper;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

public class Backend implements calls {
	private RestObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;

	public Backend(RestObserver restObserver) {
		this.restObserver = restObserver;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();
	}

	public void engageSwarm(String id) {
		(new SwarmEngager(id, restObserver, bootstrapCalls, clientCalls)).start();
	}

	@Override
	public Address getTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Peers getPeers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bootstraps getBootstraps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blacklist getBlacklist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SwarmsHelper getSwarms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Swarm getSwarm(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
