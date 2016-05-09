package backend;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.BackendObserver;
import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Swarm;
import backend.rest.BootstrapCalls;
import backend.rest.ClientCalls;

/**
 * Logic for downloading a file
 * 
 * @author iiMaXii
 *
 */
public class SwarmEngager extends Thread {
	private static final Logger LOG = Logger.getLogger(SwarmEngager.class.getName());

	private String swarmId;

	private BackendObserver restObserver;
	private BootstrapCalls bootstrapCalls;
	private ClientCalls clientCalls;
	private FileHandler fileHandler;

	public SwarmEngager(String swarmId, BackendObserver restObserver, BootstrapCalls bootstrapCalls,
			ClientCalls clientCalls, FileHandler fileHandler) {
		this.swarmId = swarmId;
		this.restObserver = restObserver;
		this.bootstrapCalls = bootstrapCalls;
		this.clientCalls = clientCalls;
		this.fileHandler = fileHandler;
	}

	public void run() {

		// TODO Try to grab metadata from database?

		// Grab swarm metadata
		Swarm swarm = bootstrapCalls.getSwarm(swarmId);
		LOG.log(Level.INFO,
				"Swarm metadata: swarmId={0}, filename={1}, blockCount={2}, fileChecksum={3}, metadataChecksum={3}",
				new Object[] { swarmId, swarm.getfilename(), swarm.getblockCount(), swarm.getfileChecksum(),
						swarm.getmetadataChecksum() });
		restObserver.newSwarm(swarmId, swarm.getfilename(), swarm.getblockCount(), "null");

		// boolean[] downloadedBlocks = new boolean[swarm.getblockCount()]; //
		// This should be in the database

		// TODO Add metadata to database

		// Get available chunks
		for (String peer : swarm.getpeers()) {
			LOG.log(Level.INFO, "Grabbing avaiable chunks for swarmId={0}, peer={1}", new Object[] { swarmId, peer });
			Chunks chunks = clientCalls.getFileChunks("http://localhost:1337", swarmId);
			
			StringBuilder b = new StringBuilder();
			for (int i : chunks.getchunks()) {
				b.append(i).append(", ");
			}
			
			LOG.log(Level.INFO, "Chunks for swarmId={0}, peer={1}: {2} ", new Object[] { swarmId, peer, b.toString() });
			break;
		}

		int blockNumber = 0;
		while (blockNumber < swarm.getblockCount()) {
			LOG.log(Level.INFO, "Grabbing block: swarmId={0} blockNumber=#{1}", new Object[] { swarmId, blockNumber });
			Chunk chunk = clientCalls.getFileChunk("http://localhost:1337", swarmId, blockNumber);

			StringBuilder b = new StringBuilder();
			for (byte c : chunk.getData()) {
				b.append((char) c);
			}

			LOG.log(Level.INFO,
					"Got block: swarmId={0} blockNumber(request)={1}, blockNumber(response)={2}, checksum={3}, size={4}, data={5}",
					new Object[] { swarmId, blockNumber, chunk.getSequenceNumber(), chunk.getChecksum(),
							chunk.getSize(), b.toString() });

			blockNumber++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		LOG.log(Level.INFO, "Finished downloading file swarmId={0}, filename={1}",
				new Object[] { swarmId, swarm.getfilename() });

	}

}
