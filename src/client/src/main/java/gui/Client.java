package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Client
{

	public JFrame frame;

	/**
	 * Create the application.
	 */

	public Client()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize()
	{

		// Creating the main frame

		frame = new JFrame("The Octopus P2P");
		frame.setSize(new Dimension(680, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// Creating the iconBar

		JPanel iconBar = new JPanel();
		iconBar.setBackground(new Color(245, 245, 245));
		iconBar.setBounds(0, 0, 680, 40);
		frame.getContentPane().add(iconBar);

		JPanel statusBar = new JPanel();
		statusBar.setBackground(new Color(255, 255, 255));
		statusBar.setBounds(0, 40, 680, 30);
		frame.getContentPane().add(statusBar);

		// Creating the statusBar

	}

}
