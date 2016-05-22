package backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import backend.rest.RESTStartUp;
import backend.rest.Rest;
import sql.sqlconnector;
import sql.insertsample;
/*
 
 * starts the rest server in a new thread and adds a shutdown hook 
 * that is executed when the program exits
 * 
 */

public class Main {
/*
	 * #@param args
	 */

	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		
		
		if (args.length < 1) {
			System.out.println("Error: Too few arguments");
			System.out.println("Bootstrap.jar [local address]");
			System.out.println();
			System.out.println("Example:");
			System.out.println("\tBootstrap.jar 192.168.1.254");
			
			return;
		}
		
		Settings.DEFAULT_BOOTSTRAP_ADDRESS = args[0];
		
		/*The below command line is calling a method/class "RESTStartUp"
		 where the bootstrap is initiated, interrupted and terminated  */
		
		//Creates Server DB on Runtime
		sqlconnector test=new sqlconnector("mysql");
		//Creates Server DB on Runtime
		test.createserverdb();
		//Insert Sample Data in Server DB
		//insertsample insample = new insertsample();
		//insample.insertIntoServer();
		test.closeconnect();

		final Thread restServerThread = new Thread(new RESTStartUp());
		restServerThread.start();
		
		Thread bootstrapSynchronizerThread = new Thread(new BootstrapSynchronizer());
		bootstrapSynchronizerThread.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Rest.destory(); // Close the database connection
				
				restServerThread.interrupt(); // Stop the REST server thread
				
				try {
					restServerThread.join(); // Wait until the thread is stopped
				} catch (InterruptedException e) {
					LOG.log(Level.INFO, e.getMessage(), e);
				}
			}
		});
	}

}
