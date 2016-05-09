package backend;

import backend.rest.RESTStartUp;

/*
 * starts the rest server in a new thread and adds a shutdown hook 
 * that is excuted when the program exits
 * 
 */

public class Main {
/*
	 * #@param args
	 */

	public static void main(String[] args) {
		/*The below command line is calling a method/class "RESTStartUp"
		 where the bootstrap is initiated, interrupted and terminated  */
		
		
		
		final Thread restServerThread = new Thread(new RESTStartUp());
		restServerThread.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				restServerThread.interrupt();
//This command line is allowing the user to join the peer network again 
//if the connection is interrupted
				try {
					restServerThread.join();//This command line is defining the user to join the peer network
				} catch (InterruptedException e) {
					e.printStackTrace();//Jakson is disconnected from peer network
				}
			}
		});
	}

}
