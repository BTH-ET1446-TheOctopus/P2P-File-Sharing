package backend;

import backend.rest.RESTStartUp;
/**
 * starts the rest server in a new thread and also adds
 *  a shutdown hook that is executed 
 * when the program exits
 */
public class Main {

 /**
  *  @param args
 */
	public static void main(String[] args) {
		final Thread restServerThread = new Thread(new RESTStartUp());
		restServerThread.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				restServerThread.interrupt();
				try {
					restServerThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
