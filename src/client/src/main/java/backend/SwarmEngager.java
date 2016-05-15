package backend;

import java.io.IOException;
import java.util.Arrays;
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

	public SwarmEngager(String swarmId, BackendObserver restObserver, BootstrapCalls bootstrapCalls,
			ClientCalls clientCalls) {
		this.swarmId = swarmId;
		this.restObserver = restObserver;
		this.bootstrapCalls = bootstrapCalls;
		this.clientCalls = clientCalls;
	}

	public void run() {

		// TODO Try to grab metadata from database?

		// Grab swarm metadata
//		Swarm swarm = bootstrapCalls.getSwarm(swarmId);
//		LOG.log(Level.INFO,
//				"Swarm metadata: swarmId={0}, filename={1}, blockCount={2}, fileChecksum={3}, metadataChecksum={3}",
//				new Object[] { swarmId, swarm.getfilename(), swarm.getblockCount(), swarm.getfileChecksum(),
//						swarm.getmetadataChecksum() });
//		restObserver.newSwarm(swarmId, swarm.getfilename(), swarm.getblockCount(), "null");

		// TODO Add metadata to database
		// Mock the bootstrap data
		
		Swarm swarm = bootstrapCalls.getSwarm(swarmId);
		
		swarmId = "abc123";
		swarm.setBlockCount(6);
		//Swarm swarm = new Swarm(4, "pom.xml", "Wololochecksum", swarmId, Arrays.asList("localhost"));
		
		String filename = "downloaded_" + swarm.getfilename();
		
		BlockBuffer blockBuffer = FileHandler.create(filename);
		if (blockBuffer == null) {
			LOG.log(Level.WARNING, "Unable to create file {0}", filename);
			
			blockBuffer = FileHandler.write(filename);
			if (blockBuffer == null) {
				LOG.log(Level.WARNING, "Unable to open file {0}, thread will exit", filename);
				return;
			}
		}
		
		restObserver.newSwarm(swarmId, swarm.getfilename(), swarm.getblockCount());
		
		
		// Get available chunks
		for (String peer : swarm.getpeers()) {
			
			
			LOG.log(Level.INFO, "Grabbing avaiable chunks for swarmId={0}, peer={1}", new Object[] { swarmId, peer });
			Chunks chunks = clientCalls.getFileChunks("http://169.254.8.2:1337", swarmId);

			StringBuilder b = new StringBuilder();
			for (int i : chunks.getchunks()) {
				b.append(i).append(", ");
			}

			LOG.log(Level.INFO, "Chunks for swarmId={0}, peer={1}: {2} ", new Object[] { swarmId, peer, b.toString() });
			break;
		}

		int blockNumber = 0;
		while (blockNumber < swarm.getblockCount()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			restObserver.updateSwarm(swarmId, (double)(blockNumber + 1) / (double)swarm.getblockCount(), 1.0, swarm.getpeers(), "1 hour");
			
			LOG.log(Level.INFO, "Grabbing block: swarmId={0} blockNumber={1}", new Object[] { swarmId, blockNumber });
			Chunk chunk = clientCalls.getFileChunk("http://169.254.8.2:1337", swarmId, blockNumber);
			
			byte[] data = chunk.getData();
			if (data == null) {
				LOG.log(Level.WARNING, "Block: swarmId={0} blockNumber={1} was null",
						new Object[] { swarmId, blockNumber });
				continue;
			}

			LOG.log(Level.INFO,
					"Got block: swarmId={0} blockNumber(request)={1}, blockNumber(response)={2}, checksum={3}, size={4}, data={5}",
					new Object[] { swarmId, blockNumber, chunk.getSequenceNumber(), chunk.getChecksum(),
							chunk.getSize(), new String(data) });

			try {
				blockBuffer.setBlock(data, chunk.getSize(), blockNumber);
			} catch (IOException e) {
				LOG.log(Level.WARNING, "Unable to write to file: swarmId={0} blockNumber={1}",
						new Object[] { swarmId, blockNumber });
			}

			blockNumber++;
		}

		LOG.log(Level.INFO, "Finished downloading file swarmId={0}, filename={1}",
				new Object[] { swarmId, swarm.getfilename() });

	}

}
