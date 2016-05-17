package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import backend.api.BackendObserver;
import backend.api.datatypes.SwarmMetadataShort;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Client implements BackendObserver
{
	private boolean						darkStatus;
	private JFrame						frame;
	private JPanel						iconBar;
	private JPanel						statusBar;
	private JPanel						settingBar;
	private JButton						newTorrent;
	private JButton						removeTorrent;
	private JButton						pauseTorrent;
	private JButton						resumeTorrent;
	private JButton						speedChart;
	private JButton						search;
	private JButton						darkPeerbtn;
	private JLabel						download;
	private JLabel						upload;
	private JScrollPane					scrollPane;
	private JTable						table;
	private String[]					columnHeaders	=
	{ "ID", "Name", "Progress", "Size", "Speed", "Peers", "Due", "Added" };
	private String[][]					swarmData;
	private List<SwarmMetadataShort>	tableRows;
	private Search						searchWindows;
	private Mode						mode;
	private SpeedChart					speedChartWindow;
	
	private Backend backend;

	/**
	 * This method creates the Octopus P2P client GUI.
	 * 
	 * @author Kamran Alipoursimakani
	 */

	public Client()
	{
		initialize();
	}

	/**
	 * This method initializes the contents of the main frame in the Octopus P2P
	 * client.
	 * 
	 * @author Kamran Alipoursimakani
	 */

	private void initialize()
	{

		createMainJFrame();
		createIconBar();
		createStatusBar();
		createSettingBar();
		createNewTorrentButton();
		createRemoveTorrentButton();
		createPauseTorrentButton();
		createResumeTorrentButton();
		createSpeedChartButton();
		createSearchButton();
		createDownloadLable();
		createUploadLable();
		createDarkPeerButton();
		createScrollPanel();
		createTable();

		backend = Backend.getInstance();
		backend.setObserver(this);
	}

	/**
	 * This method creates the main frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createMainJFrame()
	{
		frame = new JFrame("The Octopus P2P");
		frame.setSize(new Dimension(680, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
		frame.setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * This method creates the icon bar at the top of the main frame of the
	 * Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createIconBar()
	{
		iconBar = new JPanel();
		iconBar.setBackground(new Color(245, 245, 245));
	}

	/**
	 * This method creates the status bar at the top of the main frame of the
	 * Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createStatusBar()
	{
		statusBar = new JPanel();
		statusBar.setBackground(new Color(255, 255, 255));
	}

	/**
	 * This method creates the setting bar at the bottom of the main frame of
	 * the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createSettingBar()
	{
		settingBar = new JPanel();
		settingBar.setBackground(new Color(245, 245, 245));
		JDialog.setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * This method creates the "Create New Torrent" button at the icon bar of
	 * the main frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createNewTorrentButton()
	{
		newTorrent = new JButton();
		newTorrent.setToolTipText("Create New Torrent");
		newTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileNew.png")));

		newTorrent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(frame);
				String name = file.getSelectedFile().getName().toString();
				String address = file.getSelectedFile().getPath();
				long s = file.getSelectedFile().length();
				String size = Long.toString(s);
				mode = new Mode(frame,name,size,address);
//				Backend be = new Backend(null);
//				be.createSwarm(file.getSelectedFile().toString());
			}
		});
	}

	/**
	 * This method creates the "Remove Torrent" button at the icon bar of the
	 * main frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createRemoveTorrentButton()
	{
		removeTorrent = new JButton();
		removeTorrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backend be = Backend.getInstance();
				be.removeSwarm((String) table.getValueAt(table.getSelectedRow(), 0));
    	        DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.removeRow(table.getSelectedRow());
			}
		});
		removeTorrent.setToolTipText("Remove Selected Transmition");
		removeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileRemove.png")));
	}

	/**
	 * This method creates the "Pause Selected Torrent" button at the icon bar
	 * of the main frame of the Octopus P2P client. It is not part of the final
	 * design due to lack of time, but if we find enough time at the end, we
	 * might keep it!
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createPauseTorrentButton()
	{
		pauseTorrent = new JButton();
		pauseTorrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			newSwarm("1", "Negin", 1000);

			}
		});
		pauseTorrent.setToolTipText("Pause Selected Transmition");
		pauseTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/filePause.png")));
	}

	/**
	 * This method creates the "Resume Selected Torrent" button at the icon bar
	 * of the main frame of the Octopus P2P client. It is not part of the final
	 * design due to lack of time, but if we find enough time at the end, we
	 * might keep it!
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createResumeTorrentButton()
	{
		resumeTorrent = new JButton();
		resumeTorrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> l = new ArrayList<>();
				l.add("129.22.12.11");
				l.add("129.22.12.11");
				l.add("129.22.12.11");
				updateSwarm("1", 70.5, 2.2 , l, "Tomorrow");
				
			}
		});
		resumeTorrent.setToolTipText("Resume Selected Transmition");
		resumeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileResume.png")));
	}

	/**
	 * This method creates the "More Info" button at the icon bar of the main
	 * frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createSpeedChartButton()
	{
		speedChart = new JButton();
		speedChart.setToolTipText("Speed Chart For Selected Transmition");
		speedChart.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileInfo.png")));

		speedChart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setSpeedChartWindow(new SpeedChart(table.getValueAt(table.getSelectedRow(), 1).toString(), frame));
			}

		});
	}

	/**
	 * This method creates the "Search" button at the icon bar of the main frame
	 * of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createSearchButton()
	{
		search = new JButton();
		search.setToolTipText("Search");
		search.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileSearch.png")));
		GroupLayout gl_iconBar = new GroupLayout(iconBar);
		gl_iconBar.setHorizontalGroup(
			gl_iconBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_iconBar.createSequentialGroup()
					.addGap(10)
					.addComponent(newTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addComponent(removeTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(pauseTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addComponent(resumeTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(432)
					.addComponent(speedChart, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addComponent(search, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		gl_iconBar.setVerticalGroup(
			gl_iconBar.createParallelGroup(Alignment.LEADING)
				.addComponent(newTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addComponent(removeTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addComponent(pauseTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addComponent(resumeTorrent, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addComponent(speedChart, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addComponent(search, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
		);
		iconBar.setLayout(gl_iconBar);
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchWindows = new Search(frame);
			}
		});
	}

	/**
	 * This method creates the "Download" label at the status bar of the main
	 * frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createDownloadLable()
	{
		download = new JLabel();
		download.setText("999.9 MB");
		download.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/download.png")));
	}

	/**
	 * This method creates the "Upload" label at the status bar of the main
	 * frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createUploadLable()
	{
		upload = new JLabel();
		upload.setText("999.9 MB");
		upload.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/upload.png")));
		GroupLayout gl_statusBar = new GroupLayout(statusBar);
		gl_statusBar.setHorizontalGroup(
			gl_statusBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusBar.createSequentialGroup()
					.addGap(500)
					.addComponent(upload, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addComponent(download, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_statusBar.setVerticalGroup(
			gl_statusBar.createParallelGroup(Alignment.LEADING)
				.addComponent(upload, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addComponent(download, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		);
		statusBar.setLayout(gl_statusBar);
	}

	/**
	 * This method creates the "Dark Peer" button at the setting bar of the main
	 * frame of the Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createDarkPeerButton()
	{
		darkPeerbtn = new JButton();

		if (this.darkStatus == false)
		{
			setDarkPeerBtn(false);

		} else
		{
			setDarkPeerBtn(true);

		}
		GroupLayout gl_settingBar = new GroupLayout(settingBar);
		gl_settingBar.setHorizontalGroup(
			gl_settingBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settingBar.createSequentialGroup()
					.addGap(10)
					.addComponent(darkPeerbtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		gl_settingBar.setVerticalGroup(
			gl_settingBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settingBar.createSequentialGroup()
					.addGap(3)
					.addComponent(darkPeerbtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		settingBar.setLayout(gl_settingBar);

		darkPeerbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (darkStatus == false)
				{
					setDarkPeerBtn(true);
				} else
				{
					setDarkPeerBtn(false);
				}

			}
		});
	}

	/**
	 * This method adds sample data to the table in the main frame of the
	 * Octopus P2P client. This method should be removed when the connection
	 * between back and front end is established!
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	// private void addSampleDataToTable(String id, String filename)
	// {
	// tableRows = new ArrayList<>();
	// List<String> row = new ArrayList<>();
	// row.add("1");
	// row.add("Man on the moon.mp4");
	// SwarmMetadataShort swarm = new SwarmMetadataShort(id, filename);
	// swarm. setRowTable(row);
	// tableRows.add(swarm);
	//
	// createTableDataModel(tableRows);
	// }

	/**
	 * This method changes the status of the "status" variable and also changes
	 * the icon of the DarkPeer button.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void setDarkPeerBtn(boolean status)
	{
		if (status == true)
		{
			this.darkStatus = true;
			darkPeerbtn.setToolTipText("Your machine is invisible to the servers!");
			darkPeerbtn.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/darkPeer.png")));

		} else
		{
			this.darkStatus = false;
			darkPeerbtn.setToolTipText("Your machine is visible to the servers!");
			darkPeerbtn.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/brightPeer.png")));

		}
	}

	/**
	 * This method creates the ScrollPane for the table in the main frame of the
	 * Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createScrollPanel()
	{
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(iconBar, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
				.addComponent(statusBar, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
				.addComponent(settingBar, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(iconBar, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addComponent(statusBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
					.addComponent(settingBar, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * This method creates the Table in the main frame of the Octopus P2P
	 * client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createTable()
	{
		table = new JTable();
		table.setBackground(new Color(212, 239, 253));
		table.setGridColor(new Color(192, 192, 192));
		table.setToolTipText("Your Active Transmitions");
		table.setRowHeight(40);
		createDataModel();
		scrollPane.setViewportView(table);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(0).setWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setWidth(156);
		table.getColumnModel().getColumn(1).setMinWidth(156);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setWidth(60);
		table.getColumnModel().getColumn(2).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setMinWidth(60);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setWidth(70);
		table.getColumnModel().getColumn(3).setMaxWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(70);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setWidth(90);
		table.getColumnModel().getColumn(4).setMaxWidth(90);
		table.getColumnModel().getColumn(4).setMinWidth(90);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setWidth(40);
		table.getColumnModel().getColumn(5).setMaxWidth(40);
		table.getColumnModel().getColumn(5).setMinWidth(40);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setWidth(70);
		table.getColumnModel().getColumn(6).setMaxWidth(70);
		table.getColumnModel().getColumn(6).setMinWidth(70);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
	}

	@Override
	public void newSwarm(String id, String filename, int blockCount)
	{
		String size = (blockCount *1024)/1024 +" MB";
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        String dateAdded = Calendar.getInstance().getTime().toString();
        model.addRow(new Object[]{ id,  filename,  "0.0%",  size,  "0.0 KBs",  0,  "Unknown",  dateAdded});
		
	}

//	@Override
//	public void updateSwarmBlock(String id, int blockNumber, boolean correctChecksum)
//	{
//		
//for (int i = 0; i < table.getRowCount(); i++)
//{
//	if (table.getValueAt(i, 0) == id){
//		double size = (double) table.getValueAt(i, 3);
//		int totalBlock = (int) (size / 1024);
//		int currentPercent = (blockNumber * 100) /totalBlock;
//		table.setValueAt(currentPercent, i, 2);
//	}
//	
//}		
//		
//		model.setValueAt("Changed", model.getRowCount()-1, 1);
//		Random rnd = new Random();
//		System.out.println(rnd.nextInt(101));
//		
//		model.setValueAt((rnd.nextInt(100)+"%"), model.getRowCount()-1, 3);
//		
//
//	}

	@Override
	public void updateSwarm(String id, double progress, double speed, List<String> peers, String timeToCompletion)
	{
		for (int i = 0; i < table.getRowCount(); i++)
		{
			if (table.getValueAt(i, 0) == id)
			{
				table.setValueAt(String.valueOf(speed) + " KB/s", i, 4);
				table.setValueAt(String.valueOf(Math.round(progress * 100))+" %", i, 2);
				table.setValueAt(timeToCompletion, i, 6);
				table.setValueAt(peers.size(), i, 5);
			}
		}
	}

	@Override
	public void searchResult(String clientAddress, String id, String filename, int blockCount)
	{
		Search.fillSearchTable(clientAddress, id, filename, blockCount);
	}


	public JFrame getFrame()
	{
		return frame;
	}


	public void setSpeedChartWindow(SpeedChart speedChartWindow)
	{
		this.speedChartWindow = speedChartWindow;
	}

	public void createDataModel()
	{
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[]
		{ "ID", "Name", "Progress", "Size", "Speed", "Peers", "TTC", "Added" })

		{
			private static final long	serialVersionUID	= 1L;
			Class[]						types				= new Class[]
			{ Integer.class, String.class, String.class, String.class, String.class, Integer.class, String.class,
					String.class };
			boolean[]					canEdit				= new boolean[]
			{ false, false, false, false, false, false, false, false };

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

 	@Override
 	public void SpeedChartUpdate(double up, double down) {
 		// TODO Auto-generated method stub
 		
 	}

}
