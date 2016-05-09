package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Search extends JDialog
{

	private JDialog frame;

	public Search(JFrame parent)
	{
		createJDialog(parent);

	}

	private void createJDialog(JFrame parent)
	{
		frame = new JDialog(parent, "Search");
		frame.setBounds(0, 0, 600, 300);
		frame.setResizable(false);
		frame.setMinimumSize(null);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);		
	}

}
