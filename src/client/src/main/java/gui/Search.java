package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import backend.Backend;

public class Search extends JDialog
{

	private static final long	serialVersionUID	= 1L;

	private JDialog				frame;
	private HintTextField		searchTextField;
	private JButton				searchButton;
	private JTable				searchResultTable;
	private JScrollPane			resultTableScrolPane;
	private JButton				downloadButton;

	public Search(JFrame parent)
	{
		createSearchJDialog(parent);
		addSearchTextField();
		addServerSearchButton();
		addLocalSearchButton();
		addSearchResultTable();
		addDownloadButton();
	}

	/**
	 * This method creates the Search Dialog and sets the Main frame of the
	 * Octopus P2P client as the parent for it
	 * 
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
	 * 
	 * @author Kamran Alipoursimakani
	 */

	private void addSearchTextField()
	{
		searchTextField = new HintTextField("What is it that you are looking for.!?");
		searchTextField.setBounds(6, 6, 588, 30);

		frame.getContentPane().add(searchTextField);

	}

	/**
	 * This method adds the "Search Neighbors" button to the search dialog
	 * 
	 * @author Kamran Alipoursimakani
	 */

	private void addLocalSearchButton()
	{
		searchButton = new JButton("Search in Neighbors");
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Backend back = new Backend(null);
				back.searchSwarm(searchTextField.getText());
			}
		});
		searchButton.setBounds(414, 48, 180, 30);
		frame.getContentPane().add(searchButton);
	}

	/**
	 * This method adds the "Search on Servers" button to the search dialog2
	 */

	private void addServerSearchButton()
	{
		searchButton = new JButton("Search on Server");
		searchButton.setBounds(6, 48, 180, 30);
		frame.getContentPane().add(searchButton);

		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Backend back = new Backend(null);
				back.searchSwarm(searchTextField.getText());
			}
		});
	}

	/**
	 * This method adds the "searchResultTable" table to the search dialog
	 * 
	 * @author Kamran Alipoursimakani
	 */

	private void addSearchResultTable()
	{
		searchResultTable = new JTable();
		resultTableScrolPane = new JScrollPane(searchResultTable);
		resultTableScrolPane.setBounds(6, 90, 588, 202);
		frame.getContentPane().add(resultTableScrolPane);
	}

	/**
	 * This method adds the "downloadButton" button to the search dialog
	 * 
	 * @author Kamran Alipoursimakani
	 */

	private void addDownloadButton()
	{
		downloadButton = new JButton("Download");
		downloadButton.setBounds(481, 310, 107, 30);
		frame.getContentPane().add(downloadButton);

	}
}

/**
 * This class creates a TextField with grayed out hint text when not selected
 * 
 * @author Kamran Alipoursimakani
 *
 */

class HintTextField extends JTextField implements FocusListener
{

	private static final long	serialVersionUID	= 1L;
	private final String		hint;
	private boolean				showingHint;

	public HintTextField(final String hint)
	{
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText("");
			showingHint = false;
			super.setForeground(new Color(0, 0, 0));

		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText(hint);
			showingHint = true;
			super.setForeground(new Color(210, 210, 210));

		}
	}

	@Override
	public String getText()
	{
		return showingHint ? "" : super.getText();
	}
}