package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.Backend;
import backend.Logging;
import backend.Settings;
import backend.rest.RESTStartUp;
import sql.insertsample;
import sql.sqlconnector;

public class Main {
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		
		try {
			Logging.newlog();
		} catch (IOException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
			//throw new RuntimeException("Error when creating log file");
		}
		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);
		
		
		if (args.length < 2) {
			System.out.println("Error: Too few arguments");
			System.out.println("Client.jar [local address] [bootstrap address]");
			System.out.println();
			System.out.println("Example:");
			System.out.println("\tClient.jar 192.168.1.100 192.168.1.254");
			
			return;
		}
		
		sqlconnector test = new sqlconnector();
		// Creates Client DB on Runtime
		test.createclientdb();

		// Insert Sample in Client DB
		//insertsample insample = new insertsample();
		//insample.insertcdb();
		
		test.closeconnect();
		
		String bindAddress = (args.length > 0) ? args[0] : null;
		Settings.DEFAULT_CLIENT_ADDRESS = args[0];
		Settings.DEFAULT_BOOTSTRAP_ADDRESS = args[1];
		
		final Thread restServerThread = new Thread(new RESTStartUp(bindAddress));
		restServerThread.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Backend.getInstance().destroy();
				
				restServerThread.interrupt();
				try {
					restServerThread.join();
				} catch (InterruptedException e) {
					LOG.log(Level.INFO, e.getMessage(), e);
				}
			}
		});

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					LOG.log(Level.INFO, e.getMessage(), e);
				}
			}
		});
	}
}
