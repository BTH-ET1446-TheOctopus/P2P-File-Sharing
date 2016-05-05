package backend;

import backend.rest.RESTStartUp;

public class Main {

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
