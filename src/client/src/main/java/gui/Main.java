package gui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.rest.RESTStartUp;
import sql.insertsample;
import sql.sqlconnector;

public class Main
{

	public static void main(String[] args)
	{
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
					e.printStackTrace();
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
					e.printStackTrace();
				}
			}
		});
	}
}
