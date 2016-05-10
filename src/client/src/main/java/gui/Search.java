package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Search extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JDialog frame;
	private JTextField searchTextField;
	private JButton searchButton;
	private JTable searchResultTable;
	private JButton downloadButton;
	private JTextField textField;
	
	public Search(JFrame parent)
	{
		createSearchJDialog(parent);
		addSearchTextField();
		addSearchButton();
		addsearch2TextField();
		addsearch2Button();
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
		frame.setBounds(0, 0, 600, 380);
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
		searchTextField.setBounds(6, 6, 390, 30);
		frame.getContentPane().add(searchTextField);
	}
	
	/**
	 * This method adds the "Search" button to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addSearchButton()
	{
		searchButton = new JButton("Search Neighbors");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.setBounds(408, 5, 180, 30);
		frame.getContentPane().add(searchButton);
	}
	
	private void addsearch2TextField()
	{
		textField = new JTextField("");
		textField.setToolTipText("Enter what ever you are looking for here");
		textField.setBounds(6, 48, 390, 30);
		frame.getContentPane().add(textField);
	}
	
	
	private void addsearch2Button()
	{
		JButton button = new JButton("Search on Server");
		button.setBounds(410, 48, 178, 30);
		frame.getContentPane().add(button);
	}
	
	/**
	 * This method adds the "searchResultTable" table to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addSearchResultTable()
	{
		searchResultTable = new JTable();
		JScrollPane resultTableScrolPane = new JScrollPane(searchResultTable);
		resultTableScrolPane.setBounds(6, 90, 588, 202);
		frame.getContentPane().add(resultTableScrolPane);
	}
	
	/**
	 * This method adds the "downloadButton" button to the search dialog
	 * @author Kamran Alipoursimakani
	 */
	
	private void addDownloadButton()
	{
		downloadButton = new JButton("Download");
		downloadButton.setBounds(481, 310, 107, 30);
		frame.getContentPane().add(downloadButton);		
		
	}
}
