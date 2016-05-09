package backend;

import backend.api.BackendObserver;

import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

public class Backend {
	private BackendObserver restObserver;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;
	private FileHandler fileHandler;

	public Backend(BackendObserver restObserver) {
		this.restObserver = restObserver;

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();
	}

	public void engageSwarm(String id) {
		(new SwarmEngager(id, restObserver, bootstrapCalls, clientCalls, fileHandler)).start();
	}
	
}
