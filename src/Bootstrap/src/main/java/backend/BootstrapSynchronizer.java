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
				Thread.sleep(10 * 1000); // Wait for our own REST server to start. This line should be removed later

				long timestampStart = System.currentTimeMillis();

				String url = "http://localhost:9999/rest/";
				LOG.log(Level.INFO, "Synchronizing data with {0}", url);
				Sync sync = bootstrapCalls.getSync(url);

				long elapsedTime = System.currentTimeMillis() - timestampStart;
				long sleepTime = (elapsedTime > Settings.BOOTSTRAP_SYNCHRONIZATION_INTERVAL) ? 0
						: (Settings.BOOTSTRAP_SYNCHRONIZATION_INTERVAL - elapsedTime);
				Thread.sleep(sleepTime * 1000);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}
}
