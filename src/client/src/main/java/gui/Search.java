package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Search extends JDialog
{

	private JDialog frame;

	public Search(JFrame parent)
	{
		createSearchJDialog(parent);
		addSearchTextField();
		addSearchButton();
		addSearchResultTable();
		addDownloadButton();
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
	
	private void addSearchButton()
	{
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(524, 6, 70, 30);
		frame.getContentPane().add(searchButton);
	}
	
	private void addSearchResultTable()
	{
		JTable searchResultTable = new JTable();

		JScrollPane resultTableScrolPane = new JScrollPane(searchResultTable);
		resultTableScrolPane.setBounds(6, 40, 588, 190);
		frame.getContentPane().add(resultTableScrolPane);
	}
	
	private void addDownloadButton()
	{
		JButton downloadButton = new JButton("Download");
		downloadButton.setBounds(487, 242, 107, 30);
		frame.getContentPane().add(downloadButton);		
	}

}
