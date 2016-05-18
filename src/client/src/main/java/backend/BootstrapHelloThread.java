package backend;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.json.ID;
import backend.rest.BootstrapCalls;


/**
 * This thread is responsible for saying hello to the bootstrap server.
 * 
 * @author iiMaXii
 *
 */
public class BootstrapHelloThread extends Thread {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private BootstrapCalls bootstrapCalls;
	
	public BootstrapHelloThread(BootstrapCalls bootstrapCalls) {
		this.bootstrapCalls = bootstrapCalls;
	}

	public void run() {
		try {
			while (true) {
				LOG.log(Level.INFO, "(no implemented) Sending hello to bootstrap");
				// TODO bootstrapCalls.getHello(id);
				ID id = bootstrapCalls.getHello();
				LOG.log(Level.INFO, id.getid());
				Thread.sleep(Settings.BOOTSTRAP_HELLO_INTERVAL * 1000);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}
}
