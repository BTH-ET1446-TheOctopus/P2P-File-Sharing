package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Search extends JDialog
{

	private JDialog frame;

	public Search(JFrame parent)
	{
		createSearchJDialog(parent);
		addSearchTextField();

	}

	private void createSearchJDialog(JFrame parent)
	{
		frame = new JDialog(parent, "Search");
		frame.setBounds(0, 0, 600, 300);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setMinimumSize(null);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);
	}

	private void addSearchTextField()
	{
		JTextField searchTextField = new JTextField("");
		searchTextField.setToolTipText("Enter what ever you are looking for here");
		searchTextField.setBounds(6, 6, 506, 30);
		frame.getContentPane().add(searchTextField);
	}

}
