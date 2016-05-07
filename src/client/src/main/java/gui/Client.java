package gui;

import javax.swing.JFrame;

public class Client extends JFrame
{

	//Defining a public variable with JFrame type
	public JFrame frame;

	public Client()
	{

		frame = new JFrame("The Octopus P2P");
		frame.setSize(680, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

		
	}

}
