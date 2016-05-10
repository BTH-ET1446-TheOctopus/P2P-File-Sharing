package gui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.rest.RESTStartUp;

public class Main
{

	public static void main(String[] args)
	{
		Logger.getLogger("com.sun.jersey").setLevel(Level.WARNING);

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
