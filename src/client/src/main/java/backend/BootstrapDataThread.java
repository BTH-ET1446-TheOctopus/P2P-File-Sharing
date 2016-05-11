package backend;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.json.Swarms;
import backend.rest.BootstrapCalls;

/**
 * This thread retrieves data from the bootstrap.
 * 
 * @author iiMaXii
 *
 */
public class BootstrapDataThread extends Thread {
	private static final Logger LOG = Logger.getLogger(BootstrapDataThread.class.getName());

	private BootstrapCalls bootstrapCalls;

	private List<String> peers;
	private List<String> bootstraps;
	private List<Swarms> swarms;
	private List<String> blacklist;

	public BootstrapDataThread(BootstrapCalls bootstrapCalls) {
		this.bootstrapCalls = bootstrapCalls;
	}

	public void run() {
		try {
			while (true) {
				long timestampStart = System.currentTimeMillis();

				LOG.log(Level.INFO, "Grabbing peers from bootstrap server");
				peers = bootstrapCalls.getPeers().getpeers();

				LOG.log(Level.INFO, "Grabbing bootstrap list from bootstrap server");
				bootstraps = bootstrapCalls.getBootstraps().getbootstraps();

				LOG.log(Level.INFO, "Grabbing swarm list from bootstrap server");
				swarms = bootstrapCalls.getSwarms().getSwarms();

				LOG.log(Level.INFO, "Grabbing blacklist from bootstrap server");
				blacklist = bootstrapCalls.getBlacklist().getblacklist();

				long elapsedTime = System.currentTimeMillis() - timestampStart;
				Thread.sleep(Settings.BOOTSTRAP_DATA_RETRIVAL_INTERVAL * 1000 - elapsedTime);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}

	public List<String> getPeers() {
		return peers;
	}

	public List<String> getBootstraps() {
		return bootstraps;
	}

	public List<Swarms> getSwarms() {
		return swarms;
	}

	public List<String> getBlacklist() {
		return blacklist;
	}
}
