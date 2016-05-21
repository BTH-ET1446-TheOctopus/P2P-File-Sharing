package backend.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.Settings;
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

	private String clientId;

	public BootstrapHelloThread(BootstrapCalls bootstrapCalls) {
		this.bootstrapCalls = bootstrapCalls;
	}

	public void run() {
		try {
			while (true) {
				ID id = bootstrapCalls.getHello(clientId);

				if (id.getid().equals(clientId)) {
					LOG.log(Level.INFO, "Sent /hello to bootstrap id={0}", id.getid());
				} else {
					LOG.log(Level.INFO, "Sent /hello to bootstrap (new identifier) id={0}->{1}",
							new Object[] { clientId, id.getid() });
					
					clientId = id.getid();
				}
				
				Thread.sleep(Settings.BOOTSTRAP_HELLO_INTERVAL * 1000);
			}
		} catch (InterruptedException e) {
			LOG.log(Level.FINE, "Thread interrupted");
		}
	}
}
