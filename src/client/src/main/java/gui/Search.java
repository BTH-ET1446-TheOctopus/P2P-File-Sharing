package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.Backend;

import backend.api.datatypes.SwarmMetadata;
import backend.api.datatypes.SwarmMetadataShort;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Search extends JDialog
{

	private JDialog				frame;
	private JTabbedPane			panelSearch;
	private JPanel				panelServers;
	private JPanel				panelNeighbors;
	private JTextField			txtServers;
	private JTextField			txtNeighbors;
	private static JScrollPane	scrollPaneNeighbors;
	private JScrollPane			scrollPaneServers;
	private JTable				searchServersResultTable;
	private static JTable		searchNeighborsResultTable;
	Backend						backend	= Backend.getInstance();
	private JButton btnRefreshServers;
	private JButton btnDownloadServers;
	private JButton btnSearchNeighbors;
	private JButton btnDownloadNeighbors;

	public Search(JFrame parent)
	{
		initialize(parent);
	}

	private void initialize(JFrame parent)
	{
		createSearchJDialog(parent);

		addTabbedPanel();

		addServerPanel();
		addNeighborsPanel();

		addServerScrollPanel();
		addServerDownloadButton();
		addServerRefreshButton();
		addServerTextField();
		addServersSearchResultTable();

		addNeighborsScrollPanel();
		addNeighborsSearchButton();
		addNeighborsDownloadButton();
		addNeighborsTextField();
		addNeighborsSearchResultTable();

	}

	private void createSearchJDialog(JFrame parent)
	{

		frame = new JDialog(parent, "Search");
		frame.setBounds(0, 0, 600, 380);
		frame.setResizable(false);
		frame.setMinimumSize(null);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);

	}

	private void addServersSearchResultTable()
	{
		searchServersResultTable = new JTable();
		searchServersResultTable.setRowHeight(30);
		searchServersResultTable.setGridColor(Color.LIGHT_GRAY);
		searchServersResultTable.setBackground(new Color(212, 239, 253));
		searchServersResultTable.setGridColor(new Color(192, 192, 192));
		searchServersResultTable.setRowHeight(40);

		createServersDataModel();

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		searchServersResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		searchServersResultTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		searchServersResultTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		searchServersResultTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		searchServersResultTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

	}

	private void createServersDataModel()
	{
		searchServersResultTable.setModel(new DefaultTableModel(new Object[][] {}, new String[]
		{ "ID", "Name", "Size", "Peers" })

		{
			private static final long	serialVersionUID	= 1L;

			@SuppressWarnings("rawtypes")
			Class[]						types				= new Class[]
			{ String.class, String.class, Integer.class, Integer.class };
			boolean[]					canEdit				= new boolean[]
			{ false, false, false, false };

			@SuppressWarnings(
			{ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});
	}

	private void addNeighborsSearchResultTable()
	{
		searchNeighborsResultTable = new JTable();
		searchNeighborsResultTable.setRowHeight(30);
		searchNeighborsResultTable.setGridColor(Color.LIGHT_GRAY);
		searchNeighborsResultTable.setBackground(new Color(212, 239, 253));
		searchNeighborsResultTable.setGridColor(new Color(192, 192, 192));
		searchNeighborsResultTable.setRowHeight(40);
		createNeighborsDataModel();

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		searchNeighborsResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		searchNeighborsResultTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		searchNeighborsResultTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		searchNeighborsResultTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		searchNeighborsResultTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	public void createNeighborsDataModel()
	{
		searchNeighborsResultTable.setModel(new DefaultTableModel(new Object[][] {}, new String[]
		{ "ID", "Name", "Size", "Client Address" })

		{
			private static final long	serialVersionUID	= 1L;

			@SuppressWarnings("rawtypes")
			Class[]						types				= new Class[]
			{ String.class, String.class, Integer.class, Integer.class };
			boolean[]					canEdit				= new boolean[]
			{ false, false, false, false };

			@SuppressWarnings(
			{ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex)
			{
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return canEdit[columnIndex];
			}
		});

	}

	/**
	 * This method creates the search panel for searching files.
	 * 
	 *
	 */
	private void addTabbedPanel()
	{
		panelSearch = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSearch, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSearch, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * This method creates the search panel for searching files from servers.
	 * 
	 *
	 */
	private void addServerPanel()
	{
		panelServers = new JPanel();
		panelSearch.addTab("Servers", null, panelServers, null);
	}

	private void addServerScrollPanel()
	{
		scrollPaneServers = new JScrollPane();
	}

	private void addServerDownloadButton()
	{
		btnDownloadServers = new JButton("Download");
		btnDownloadServers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backend.engageSwarm(
						(String) searchServersResultTable.getValueAt(searchServersResultTable.getSelectedRow(), 0));
				System.out.println(searchServersResultTable.getValueAt(searchServersResultTable.getSelectedRow(), 0));
			}
		});
	}

	private void addServerRefreshButton()
	{
		btnRefreshServers = new JButton("Refresh");
		btnRefreshServers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				List<SwarmMetadataShort> swarms = backend.getSwarms();

				createTableDataModel(swarms);

				scrollPaneServers.setViewportView(searchServersResultTable);
			}
		});
	}

	private void addServerTextField()
	{
		txtServers = new JTextField();
		txtServers.setToolTipText("Search on server is not available in this version!");
		txtServers.setEnabled(false);
		txtServers.setColumns(10);
		GroupLayout gl_panelServers = new GroupLayout(panelServers);
		gl_panelServers.setHorizontalGroup(
			gl_panelServers.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServers.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panelServers.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneServers, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addGroup(gl_panelServers.createSequentialGroup()
							.addComponent(btnRefreshServers, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(txtServers, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(btnDownloadServers, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
					.addGap(6))
		);
		gl_panelServers.setVerticalGroup(
			gl_panelServers.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelServers.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPaneServers, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(gl_panelServers.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRefreshServers)
						.addComponent(txtServers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDownloadServers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(6))
		);
		panelServers.setLayout(gl_panelServers);
	}

	/**
	 * This method creates the search panel for searching files from neighbors.
	 * 
	 *
	 */
	
	private void addNeighborsPanel()
	{
		panelNeighbors = new JPanel();
		panelSearch.addTab("Neighbors", null, panelNeighbors, null);
	}

	private void addNeighborsScrollPanel()
	{
		scrollPaneNeighbors = new JScrollPane();
	}

	private void addNeighborsSearchButton()
	{
		btnSearchNeighbors = new JButton("Search");
		btnSearchNeighbors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//txtNeighbors.getText()
				backend.searchSwarm(txtNeighbors.getText());				

			}

	});
}
	private void addNeighborsDownloadButton()
	{
		btnDownloadNeighbors = new JButton("Download");
		btnDownloadNeighbors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("GUI download: "+searchNeighborsResultTable.getValueAt(searchNeighborsResultTable.getSelectedRow(), 0));
				backend.engageSwarm(
						(String) searchNeighborsResultTable.getValueAt(searchNeighborsResultTable.getSelectedRow(), 0));
			}
		});
	}

	private void addNeighborsTextField()
	{
		txtNeighbors = new JTextField();
		txtNeighbors.setToolTipText("What is it that you are looking for.!?");
		txtNeighbors.setColumns(10);
		GroupLayout gl_panelNeighbors = new GroupLayout(panelNeighbors);
		gl_panelNeighbors.setHorizontalGroup(
			gl_panelNeighbors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNeighbors.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panelNeighbors.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneNeighbors, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addGroup(gl_panelNeighbors.createSequentialGroup()
							.addComponent(btnSearchNeighbors, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(txtNeighbors, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(btnDownloadNeighbors, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
					.addGap(6))
		);
		gl_panelNeighbors.setVerticalGroup(
			gl_panelNeighbors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNeighbors.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPaneNeighbors, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(gl_panelNeighbors.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchNeighbors, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtNeighbors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDownloadNeighbors))
					.addGap(6))
		);
		panelNeighbors.setLayout(gl_panelNeighbors);
	}

	private void createTableDataModel(List<SwarmMetadataShort> swarms)
	{

		DefaultTableModel model = (DefaultTableModel) searchServersResultTable.getModel();
		model.setRowCount(0);

		for (SwarmMetadataShort swarm : swarms)
		{
			model.addRow(new Object[]
			{ swarm.getId(), swarm.getFilename(), null, String.join(", ", swarm.getPeers()) });

		}

	}

	public static void fillSearchTable(String clientAddress, String id, String filename, int blockCount)
	{
		DefaultTableModel model = (DefaultTableModel) searchNeighborsResultTable.getModel();
		model.setRowCount(0);
		model.addRow(new Object[]
		{ id, filename, blockCount, clientAddress });
		scrollPaneNeighbors.setViewportView(searchNeighborsResultTable);

	}

	public JTable getSearchResultTable()
	{
		return searchNeighborsResultTable;
	}

	public void UpdateSearchResult() {
		List<SwarmMetadata> searchResults = new ArrayList<SwarmMetadata>();
		Client client = new Client();
		searchResults=backend.getsearchResult();
		System.out.print(searchResults.size());
		for(int i=0; searchResults.size()>i; i++) {
		client.searchResult(searchResults.get(i).getPeers().get(i), searchResults.get(i).getId(), searchResults.get(i).getFilename(), searchResults.get(i).getBlockCount());
		}	
		
		
		
		
	}

}
