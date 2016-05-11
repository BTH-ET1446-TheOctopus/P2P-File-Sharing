package backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import backend.rest.RESTStartUp;
import sql.sqlconnector;

/*
 
 * starts the rest server in a new thread and adds a shutdown hook 
 * that is excuted when the program exits
 * 
 */

public class Main {
/*
	 * #@param args
	 */

	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		/*The below command line is calling a method/class "RESTStartUp"
		 where the bootstrap is initiated, interrupted and terminated  */
		//sqlconnector test=new sqlconnector();
		//test.createserverdb();

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
					LOG.log(Level.INFO, e.getMessage(), e);//Jakson is disconnected from peer network
				}
			}
		});
	}

}
