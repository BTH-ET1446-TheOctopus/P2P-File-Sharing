package backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.BackendController;
import backend.api.BackendObserver;
import backend.api.SpeedChartObserver;
import backend.api.datatypes.SwarmMetadata;
import backend.api.datatypes.SwarmMetadataShort;
import backend.file.BlockBuffer;
import backend.file.FileHandler;
import backend.json.ID;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;
import backend.thread.BootstrapDataThread;
import backend.thread.BootstrapHelloThread;
import backend.thread.ClientSearchThread;
import backend.thread.SwarmEngager;
import sql.DatabaseAPI;
import sql.DatabaseCalls;

public class Backend implements BackendController {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private HashMap<String, SwarmEngager> activeSwarms;

	private BackendObserver restObserver;
	private SpeedChartObserver speedChartObserver;

	private DatabaseAPI databaseAPI;

	private ClientCalls clientCalls;
	private BootstrapCalls bootstrapCalls;
	private DatabaseCalls databaseCalls;

	private BootstrapHelloThread bootstrapHelloThread;
	private BootstrapDataThread bootstrapDataThread;
	private ClientSearchThread clientSearchThread;

	private List<SwarmMetadata> searchResults;

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

	private Backend() {
		activeSwarms = new HashMap<String, SwarmEngager>();

		restObserver = null;
		speedChartObserver = null;

		databaseAPI = new DatabaseCalls();

		clientCalls = new ClientCalls();
		bootstrapCalls = new BootstrapCalls();
		databaseCalls = new DatabaseCalls();

		bootstrapHelloThread = new BootstrapHelloThread(bootstrapCalls);
		bootstrapDataThread = new BootstrapDataThread(bootstrapCalls);

		searchResults = new ArrayList<SwarmMetadata>();

		bootstrapHelloThread.start();
		bootstrapDataThread.start();
	}

	public boolean engageSwarm(String id) {
		if (activeSwarms.containsKey(id)) {
			LOG.log(Level.WARNING, "Cannot engage swarm id={0}: has already been engaged", id);
			return false;
		}
		
		SwarmMetadata darkSwarmMetadata = null;
		for (SwarmMetadata swarmMetadata : searchResults) {
			if (swarmMetadata.getId().equals(id)) {
				darkSwarmMetadata = swarmMetadata;
				break;
			}
		}
		
		SwarmEngager swarmEngager = null;
		if (darkSwarmMetadata == null) {
			swarmEngager = new SwarmEngager(id, restObserver, bootstrapCalls, clientCalls);
		} else {
			swarmEngager = new SwarmEngager(darkSwarmMetadata, restObserver, clientCalls);
		}
		
		swarmEngager.start();

		activeSwarms.put(id, swarmEngager);

		return true;
	}

	public boolean disengageSwarm(String id) {
		SwarmEngager swarmEngager = activeSwarms.get(id);
		if (swarmEngager == null) {
			LOG.log(Level.WARNING, "Cannot disengage swarm id={0}: has not been engaged", id);
		} else {
			swarmEngager.interrupt();
			activeSwarms.remove(id);
		}
		
		databaseCalls.deleteSwarmID(id);
		
		bootstrapCalls.removeClientFromSwarm(id);

		return true;
	}

	@Override
	public List<SwarmMetadataShort> getSwarms() {
		List<Swarms> json_swarms = bootstrapDataThread.getSwarms();
		List<SwarmMetadataShort> swarms = new ArrayList<SwarmMetadataShort>();

		for (Swarms json_swarm : json_swarms) {
			swarms.add(new SwarmMetadataShort(json_swarm.getid(), json_swarm.getfilename(), json_swarm.getPeers()));
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
	public void createSwarm(String originalFilename, boolean dark) {
		String basename = (new File(originalFilename)).getName();
		
		// Try to copy the file to working directory
		try {
			Files.copy((new File(originalFilename)).toPath(), (new File(basename)).toPath());
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Failed to copy {0} to working directory", new Object[] {originalFilename, basename});
		}
		
		BlockBuffer blockBuffer = FileHandler.read(originalFilename);
		
		String uuid;
		int blockCount = -1;
		try {
			blockCount = blockBuffer.getBlockCount();
		} catch (IOException e) {
			LOG.log(Level.WARNING, e.toString(), e);
			return;
		}

		String fileChecksum = "tobedone"; // TODO
		String metadataChecksum = "todoododododooo"; // TODO

		if (dark) {
			uuid = UUID.randomUUID().toString();
		} else {
			ID uuid_json = bootstrapCalls.addSwarm(basename, blockCount, fileChecksum, metadataChecksum);
			uuid = uuid_json.getid();
		}

		databaseAPI.addSwarm(uuid, basename, fileChecksum, metadataChecksum, blockCount, null);

		restObserver.newSwarm(uuid, basename, blockCount);
		restObserver.updateSwarm(uuid, 1.0, 0, Arrays.asList(""), "");
	}

	@Override
	public void searchSwarm(String filename) {
		clientSearchThread = new ClientSearchThread(filename);
		clientSearchThread.start();
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

	@Override
	public void subscribeSpeedChart(String id, SpeedChartObserver callback) {
		if (speedChartObserver != null) {
			throw new RuntimeException("Cannot subscribe for multiple speed charts");
		}
		
		SwarmEngager swarmEngager = activeSwarms.get(id);
		if (swarmEngager != null) {
			activeSwarms.get(id).subscribeSpeedCallback(callback);
		}
	}

	@Override
	public void unsubscribeSpeedChart(String id) {
		SwarmEngager swarmEngager = activeSwarms.get(id);
		if (swarmEngager != null) {
			activeSwarms.get(id).unsubscribeSpeedCallback();
		}
	}

	public void searchResult(String id, Integer blockCount, String filename, String fileChecksum,
			String metadataChecksum, String ipAddress) {
		searchResults.add(
				new SwarmMetadata(id, filename, blockCount, fileChecksum, metadataChecksum, Arrays.asList(ipAddress)));
		System.out.print(Arrays.asList(ipAddress));
		restObserver.searchResult(ipAddress, id, filename, blockCount);
	}
	public List<SwarmMetadata> getsearchResult()
	{
	return searchResults;
	}
	
	public void destroy() {
		if (databaseCalls != null) {
			databaseCalls.closedbconnect();
			databaseCalls = null;
		}
	}
}
