package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Search extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JDialog frame;
	private JTextField searchTextField;
	private JButton searchButton;
	private JTable searchResultTable;
	private JButton downloadButton;
	
	public Search(JFrame parent)
	{
		createSearchJDialog(parent);
		addSearchTextField();
		addSearchButton();
		addSearchResultTable();
		addDownloadButton();
	}
	
	/**
	 * This method creates the Search Dialog and sets the Main frame of the Octopus P2P client as the parent for it
	 * @author Kamran Alipoursimakani
	 */

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
	
	/**
	 * This method adds "searchTextField" text field to the search dialog
	 * @author Kamran Alipoursimakani
	 */

	private void addSearchTextField()
	{
		searchTextField = new JTextField("");
		searchTextField.setToolTipText("Enter what ever you are looking for here");
		searchTextField.setBounds(6, 6, 506, 30);
		frame.getContentPane().add(searchTextField);
	}
	
	/**
	 * This method adds the "Search" button to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addSearchButton()
	{
		searchButton = new JButton("Search");
		searchButton.setBounds(524, 6, 70, 30);
		frame.getContentPane().add(searchButton);
	}
	
	/**
	 * This method adds the "searchResultTable" table to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addSearchResultTable()
	{
		searchResultTable = new JTable();
		JScrollPane resultTableScrolPane = new JScrollPane(searchResultTable);
		resultTableScrolPane.setBounds(6, 40, 588, 190);
		frame.getContentPane().add(resultTableScrolPane);
	}
	
	/**
	 * This method adds the "downloadButton" button to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addDownloadButton()
	{
		downloadButton = new JButton("Download");
		downloadButton.setBounds(487, 242, 107, 30);
		frame.getContentPane().add(downloadButton);		
	}

}
