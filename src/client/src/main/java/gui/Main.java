package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.Logging;
import backend.rest.RESTStartUp;
import sql.insertsample;
import sql.sqlconnector;

public class Main
{
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args)
	{
		
		try {
			 Logging.newlog();
		    }
		catch (IOException e) {
		    	LOG.log(Level.INFO, e.getMessage(), e);
		      throw new RuntimeException("Error when creating log file");
		    }
		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);
		
		sqlconnector test = new sqlconnector();
		//Creates Client DB on Runtime
		test.createclientdb();
		
		//Insert Sample in Client DB
		insertsample insample = new insertsample();
		insample.insertcdb();
		
		final Thread restServerThread = new Thread(new RESTStartUp());
		restServerThread.start();

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{
				restServerThread.interrupt();
				try
				{
					restServerThread.join();
					//sqlconnector test=new sqlconnector();
					//test.connector("root", "sql", "mysql", "127.0.0.1", "3306");
					//Creates Client DB on Runtime
					//test.createclientdb();
				} catch (InterruptedException e)
				{
					LOG.log(Level.INFO, e.getMessage(), e);
				}
			}
		});

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Client window = new Client();
					window.getFrame().setVisible(true);
				} catch (Exception e)
				{
					LOG.log(Level.INFO, e.getMessage(), e);
				}
			}
		});
	}
}
