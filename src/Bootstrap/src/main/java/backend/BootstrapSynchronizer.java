package backend;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.json.Sync;
import backend.rest.BootstrapCalls;

public class BootstrapSynchronizer implements Runnable {
	private static final Logger LOG = Logger.getLogger(BootstrapSynchronizer.class.getName());

	private BootstrapCalls bootstrapCalls;

	public BootstrapSynchronizer() {
		bootstrapCalls = new BootstrapCalls();
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(60 * 1000); // Wait for our own REST server to start. TODO This line should be removed later

				long timestampStart = System.currentTimeMillis();

				String address = Settings.DEFAULT_BOOTSTRAP_ADDRESS;
				LOG.log(Level.INFO, "Synchronizing data with {0}", address);
				Sync sync = bootstrapCalls.getSync(address);

				long elapsedTime = System.currentTimeMillis() - timestampStart;
				long sleepTime = (elapsedTime > Settings.BOOTSTRAP_SYNCHRONIZATION_INTERVAL) ? 0
						: (Settings.BOOTSTRAP_SYNCHRONIZATION_INTERVAL - elapsedTime);
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}
}
