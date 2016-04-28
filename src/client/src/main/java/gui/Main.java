package gui;

import java.awt.EventQueue;
import rest.RESTStartUp;

public class Main {
	public static void main(String[] args) {
		new Thread(new RESTStartUp()).start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frmOctopusP2P.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
