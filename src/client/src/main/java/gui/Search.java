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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import backend.api.datatypes.SwarmMetadataShort;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Search extends JDialog
{

	private JDialog			frame;
	private JTabbedPane		panelSearch;
	private JPanel			panelServers;
	private JPanel			panelNeighbors;
	private JTextField		txtServers;
	private JTextField		txtNeighbors;
	private static JScrollPane		scrollPaneNeighbors;
	private JScrollPane		scrollPaneServers;
	private JTable			searchServersResultTable;
	private static JTable	searchNeighborsResultTable;
	Backend backend = Backend.getInstance();
	

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
		frame.getContentPane().setLayout(null);
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
			Class[]	types = new Class[] { String.class, String.class, Integer.class, Integer.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

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

	private void addTabbedPanel()
	{
		panelSearch = new JTabbedPane(JTabbedPane.TOP);
		panelSearch.setBounds(0, 0, 600, 358);
		frame.getContentPane().add(panelSearch);
	}

	private void addServerPanel()
	{
		panelServers = new JPanel();
		panelSearch.addTab("Servers", null, panelServers, null);
		panelServers.setLayout(null);
	}

	private void addServerScrollPanel()
	{
		scrollPaneServers = new JScrollPane();
		scrollPaneServers.setBounds(6, 6, 567, 259);
		panelServers.add(scrollPaneServers);
	}

	private void addServerDownloadButton()
	{
		JButton btnDownloadServers = new JButton("Download");
		btnDownloadServers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			backend.engageSwarm((String) searchServersResultTable.getValueAt(searchServersResultTable.getSelectedRow(), 0));
			System.out.println(searchServersResultTable.getValueAt(searchServersResultTable.getSelectedRow(), 0));
			}
		});
		btnDownloadServers.setBounds(456, 277, 117, 29);
		panelServers.add(btnDownloadServers);
	}

	private void addServerRefreshButton()
	{
		JButton btnRefreshServers = new JButton("Refresh");
		btnRefreshServers.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("unused")
				Backend back = Backend.getInstance();
				List<SwarmMetadataShort> swarms = back.getSwarms();
//				@SuppressWarnings("rawtypes")
//				List swarms=new ArrayList<>();
//				swarms.add(new SwarmMetadataShort("1", "Negin"));
//				swarms.add(new SwarmMetadataShort("2", "Kamran"));
//				swarms.add(new SwarmMetadataShort("3", "khar"));
//				swarms.add(new SwarmMetadataShort("4", "Negin"));
//				swarms.add(new SwarmMetadataShort("5", "Kamran"));
//				swarms.add(new SwarmMetadataShort("6", "khar"));
//				swarms.add(new SwarmMetadataShort("7", "Negin"));
//				swarms.add(new SwarmMetadataShort("8", "Kamran"));
//				swarms.add(new SwarmMetadataShort("9", "khar"));
//				swarms.add(new SwarmMetadataShort("10", "Negin"));
//				swarms.add(new SwarmMetadataShort("11", "Kamran"));
//				swarms.add(new SwarmMetadataShort("12", "khar"));
				
				createTableDataModel(swarms);
				
				scrollPaneServers.setViewportView(searchServersResultTable);
			}
		});
		btnRefreshServers.setBounds(6, 277, 117, 29);
		panelServers.add(btnRefreshServers);
	}

	private void addServerTextField()
	{
		txtServers = new JTextField();
		txtServers.setToolTipText("Search on server is not available in this version!");
		txtServers.setEnabled(false);
		txtServers.setBounds(135, 277, 309, 26);
		txtServers.setColumns(10);
		panelServers.add(txtServers);
	}

	private void addNeighborsPanel()
	{
		panelNeighbors = new JPanel();
		panelSearch.addTab("Neighbors", null, panelNeighbors, null);
		panelNeighbors.setLayout(null);
	}

	private void addNeighborsScrollPanel()
	{
		scrollPaneNeighbors = new JScrollPane();
		scrollPaneNeighbors.setBounds(6, 6, 567, 259);
		panelNeighbors.add(scrollPaneNeighbors);
	}

	private void addNeighborsSearchButton()
	{
		JButton btnSearchNeighbors = new JButton("Search");
		btnSearchNeighbors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backend.searchSwarm(txtNeighbors.getText());
				
				Client client = new Client();
				client.searchResult("test", "test", "Test", 12);

			}
		});
		btnSearchNeighbors.setBounds(6, 277, 117, 29);
		panelNeighbors.add(btnSearchNeighbors);

	}

	private void addNeighborsDownloadButton()
	{
		JButton btnDownloadNeighbors = new JButton("Download");
		btnDownloadNeighbors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDownloadNeighbors.setBounds(456, 277, 117, 29);
		panelNeighbors.add(btnDownloadNeighbors);
	}

	private void addNeighborsTextField()
	{
		txtNeighbors = new JTextField();
		txtNeighbors.setToolTipText("What is it that you are looking for.!?");
		txtNeighbors.setBounds(135, 277, 309, 26);
		panelNeighbors.add(txtNeighbors);
		txtNeighbors.setColumns(10);
	}
	
	private void createTableDataModel(List<SwarmMetadataShort> swarms)
	{

		 DefaultTableModel model = (DefaultTableModel)searchServersResultTable.getModel();
		 model.setRowCount(0);
	
		for (SwarmMetadataShort swarm : swarms)
		{
			 model.addRow(new Object[]{ swarm.getId(),swarm.getFilename()});

		}

	}

	
	public static void fillSearchTable(String clientAddress, String id, String filename, int blockCount)
	{
        DefaultTableModel model = (DefaultTableModel) searchNeighborsResultTable.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{ id, filename, blockCount, clientAddress});
		scrollPaneNeighbors.setViewportView(searchNeighborsResultTable);
		

	}
	
	public JTable getSearchResultTable()
	{
		return searchNeighborsResultTable;
	}

	


}





