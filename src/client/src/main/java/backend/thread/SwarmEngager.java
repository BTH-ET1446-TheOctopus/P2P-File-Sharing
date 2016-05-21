package backend.thread;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.Settings;
import backend.api.BackendObserver;
import backend.api.SpeedChartObserver;
import backend.api.datatypes.SwarmMetadata;
import backend.file.BlockBuffer;
import backend.file.FileHandler;
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
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String swarmId;
	private SwarmMetadata swarmMetadata;
	private SpeedChartObserver speedChartObserver;

	private BackendObserver restObserver;
	private BootstrapCalls bootstrapCalls;
	private ClientCalls clientCalls;

	/**
	 * Engage a dark swarm
	 * 
	 * @param swarmMetadata
	 * @param restObserver
	 * @param bootstrapCalls
	 * @param clientCalls
	 */
	public SwarmEngager(SwarmMetadata swarmMetadata, BackendObserver restObserver, ClientCalls clientCalls) {
		this.swarmId = swarmMetadata.getId();
		this.swarmMetadata = swarmMetadata;

		this.restObserver = restObserver;
		this.bootstrapCalls = null;
		this.clientCalls = clientCalls;
	}

	/**
	 * Engage a public swarm
	 * 
	 * @param swarmId
	 * @param restObserver
	 * @param bootstrapCalls
	 * @param clientCalls
	 */
	public SwarmEngager(String swarmId, BackendObserver restObserver, BootstrapCalls bootstrapCalls,
			ClientCalls clientCalls) {
		this.swarmId = swarmId;
		swarmMetadata = null;

		this.restObserver = restObserver;
		this.bootstrapCalls = bootstrapCalls;
		this.clientCalls = clientCalls;
	}

	public void subscribeSpeedCallback(SpeedChartObserver speedChartObserver) {
		this.speedChartObserver = speedChartObserver;
	}

	public void unsubscribeSpeedCallback() {
		speedChartObserver = null;
	}

	public void run() {
		boolean darkSwarm = bootstrapCalls == null;
		
		if (!darkSwarm) {
			Swarm swarm = bootstrapCalls.getSwarm(swarmId);
			
			swarmMetadata = new SwarmMetadata(swarmId, swarm.getfilename(), swarm.getblockCount(), swarm.getfileChecksum(), swarm.getmetadataChecksum(),
					swarm.getpeers(), false);
		}
		
		LOG.log(Level.INFO,
				"Swarm metadata: swarmId={0}, filename={1}, blockCount={2}, fileChecksum={3}, metadataChecksum={3}",
				new Object[] { swarmId, swarmMetadata.getFilename(), swarmMetadata.getBlockCount(), swarmMetadata.getFileChecksum(),
						swarmMetadata.getMetadataChecksum() });

		// 3. swarm.getmetadataChecksum() == shaChecksum(id, filename, size,
		// fileChecksum);

		// TODO Add metadata to database

		/*swarmId = "abc123"; // For now just mock the data
		swarmMetadata.setBlockCount(874);
		swarmMetadata.setpeers(Arrays.asList("localhost"));
		swarmMetadata.setFilename("octopus.jpg");*/

		String filename = "downloaded_" + swarmMetadata.getFilename();

		BlockBuffer blockBuffer = FileHandler.create(filename);
		if (blockBuffer == null) {
			LOG.log(Level.WARNING, "Unable to create file {0}", filename);

			blockBuffer = FileHandler.write(filename);
			if (blockBuffer == null) {
				LOG.log(Level.WARNING, "Unable to open file {0}, thread will exit", filename);
				return;
			}
		}

		restObserver.newSwarm(swarmId, swarmMetadata.getFilename(), swarmMetadata.getBlockCount());
		restObserver.updateSwarm(swarmId, 0.0, 1.0, swarmMetadata.getPeers(), "N/A");

		// Get available chunks
		for (String peer : swarmMetadata.getPeers()) {

			LOG.log(Level.INFO, "Grabbing avaiable chunks for swarmId={0}, peer={1}", new Object[] { swarmId, peer });
			Chunks chunks = clientCalls.getFileChunks(peer, swarmId);

			StringBuilder b = new StringBuilder();
			for (int i : chunks.getchunks()) {
				b.append(i).append(", ");
			}

			LOG.log(Level.INFO, "Chunks for swarmId={0}, peer={1}: {2} ", new Object[] { swarmId, peer, b.toString() });
			break;
		}

		long timestampBegin = System.currentTimeMillis();
		long downloadedBytes = 0;
		long nextUpdate = System.currentTimeMillis();

		int blockNumber = 0;
		while (blockNumber < swarmMetadata.getBlockCount()) {
			if (swarmMetadata.getPeers().isEmpty()) {
				LOG.log(Level.INFO, "No peers for swarm {0}", swarmId);
				try {
					Thread.sleep(20 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				continue;
			}

			String peerIP = swarmMetadata.getPeers().get(0);

			LOG.log(Level.FINE, "Grabbing block: swarmId={0} blockNumber={1}", new Object[] { swarmId, blockNumber });
			Chunk chunk = clientCalls.getFileChunk(peerIP, swarmId, blockNumber);

			byte[] data = chunk.getData();
			if (data == null) {
				LOG.log(Level.WARNING, "Block: swarmId={0} blockNumber={1} was null",
						new Object[] { swarmId, blockNumber });
				continue;
			}

			downloadedBytes += chunk.getSize();

			if (System.currentTimeMillis() >= nextUpdate) {
				nextUpdate = System.currentTimeMillis() + Settings.GUI_UPDATE_INTERVAL;

				// Calculate download speed (bytes / second)
				double downloadSpeed = (double) downloadedBytes
						/ ((System.currentTimeMillis() - timestampBegin) / 1000);

				// Calculate ETC (seconds)
				long etcSeconds = (long) ((double) 1024 * (swarmMetadata.getBlockCount() - blockNumber - 1) / downloadSpeed);

				if (speedChartObserver != null) {
					speedChartObserver.updateSpeedChart(downloadSpeed / 1024);
				}

				restObserver.updateSwarm(swarmId, (double) (blockNumber + 1) / (double) swarmMetadata.getBlockCount(),
						downloadSpeed / 1024, swarmMetadata.getPeers(), toReadableString(etcSeconds));
			}

			LOG.log(Level.FINE,
					"Got block: swarmId={0} blockNumber(request)={1}, blockNumber(response)={2}, checksum={3}, size={4}, data={5}",
					new Object[] { swarmId, blockNumber, chunk.getSequenceNumber(), chunk.getChecksum(),
							chunk.getSize(), new String(data) });

			try {
				blockBuffer.setBlock(data, chunk.getSize(), blockNumber);

				blockNumber++;
			} catch (IOException e) {
				LOG.log(Level.WARNING, "Unable to write to file: swarmId={0} blockNumber={1}",
						new Object[] { swarmId, blockNumber });
			}
		}

		LOG.log(Level.INFO, "Finished downloading file swarmId={0}, filename={1}",
				new Object[] { swarmId, swarmMetadata.getFilename() });

		FileHandler.setReadOnly(filename);

		restObserver.updateSwarm(swarmId, 1, 0, swarmMetadata.getPeers(), "");
	}

	private static String toReadableString(long seconds) {
		StringBuilder s = new StringBuilder();

		long days = TimeUnit.SECONDS.toDays(seconds);
		seconds -= TimeUnit.DAYS.toSeconds(days);
		if (days > 0) {
			s.append(days).append("d ");
		}

		long hours = TimeUnit.SECONDS.toHours(seconds);
		seconds -= TimeUnit.HOURS.toSeconds(hours);
		if (hours > 0) {
			s.append(hours).append("h ");
		}

		long minutes = TimeUnit.SECONDS.toMinutes(seconds);
		seconds -= TimeUnit.MINUTES.toSeconds(minutes);
		if (minutes > 0) {
			s.append(minutes).append("m ");
		}

		s.append(seconds).append("s");

		return s.toString();
	}

}
