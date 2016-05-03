package gui;

import java.awt.EventQueue;

import rest.RESTStartUp;

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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
