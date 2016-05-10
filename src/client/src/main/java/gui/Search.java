package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
	 * This method adds "searchTextField" text field to the search dialog1
	 * @author Kamran Alipoursimakani
	 */

	private void addSearchTextField()
	{
		searchTextField = new JTextField("Type here");
		//searchTextField.setToolTipText("Enter what ever you are looking for here");
		searchTextField.setBounds(6, 6, 390, 30);
		frame.getContentPane().add(searchTextField);
		
		/**
		 * This method handles the prompt text in searchTextField 
		 */
		
		searchTextField.addFocusListener(new FocusListener(){
			
		    public void focusGained(FocusEvent e) {
		    	searchTextField.setText("");
		    }
			public void focusLost(FocusEvent e) {
				if(searchTextField.getText().isEmpty())
				searchTextField.setText("Type here");
			}
			});
	}
	
	/**
	 * This method adds the "Search Neighbors" button to the search dialog
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
	
	/**
	 * This method adds the "Search Neighbors" button to the search dialog2
	 */
	
	private void addsearch2TextField()
	{
		textField = new JTextField();
		//textField.setToolTipText("Enter what ever you are looking for here");
		textField.setBounds(6, 48, 390, 30);
		frame.getContentPane().add(textField);
	}
	
	/**
	 * This method adds the "Search on Servers" button to the search dialog2
	 */ 
	
	private void addsearch2Button()
	{
		searchButton = new JButton("Search on Server");
		searchButton.setBounds(410, 48, 178, 30);
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
		resultTableScrolPane.setBounds(6, 90, 588, 202);
		resultTableScrolPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resultTableScrolPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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