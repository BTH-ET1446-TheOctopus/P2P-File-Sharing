package backend;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.rest.BootstrapCalls;

/**
 * This thread is responsible for saying hello to the bootstrap server.
 * 
 * @author iiMaXii
 *
 */
public class BootstrapHelloThread extends Thread {
	private static final Logger LOG = Logger.getLogger(BootstrapHelloThread.class.getName());

	private BootstrapCalls bootstrapCalls;

	public BootstrapHelloThread(BootstrapCalls bootstrapCalls) {
		this.bootstrapCalls = bootstrapCalls;
	}

	public void run() {
		try {
			while (true) {
				LOG.log(Level.INFO, "(no implemented) Sending hello to bootstrap");

				// TODO bootstrapCalls.getHello(id);

				Thread.sleep(Settings.BOOTSTRAP_HELLO_INTERVAL * 1000);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}
}
