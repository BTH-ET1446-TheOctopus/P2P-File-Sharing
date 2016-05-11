package backend;

import java.util.List;

import backend.api.BackendController;
import backend.api.BackendObserver;
import backend.json.Swarm;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

public class Backend implements BackendController {
	private BackendObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;

	public Backend(BackendObserver restObserver) {
		this.restObserver = restObserver;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();
	}

	public void engageSwarm(String id) {
		(new SwarmEngager(id, restObserver, bootstrapCalls, clientCalls)).start();
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
	public Swarm getSwarm(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSwarm(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSwarm(String id) {
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
	
}
