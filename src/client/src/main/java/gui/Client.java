package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;

public class Client
{
	private boolean					darkStatus;
	private JFrame					frame;
	private JPanel					iconBar;
	private JPanel					statusBar;
	private JPanel					settingBar;
	private JButton					newTorrent;
	private JButton					removeTorrent;
	private JButton					pauseTorrent;
	private JButton					resumeTorrent;
	private JButton					speedChart;
	private JButton					search;
	private JButton					darkPeerbtn;
	private JLabel					download;
	private JLabel					upload;
	private JScrollPane				scrollPane;
	private JTable					table;
	private String[]				columnHeaders	=
	{ "ID", "Name", "Progress", "Size", "Speed", "Peers", "Due", "Added" };
	private String[][]				fileStatistics;
	private String					selectedFile;
	private DefaultTableModel		model;
	private List<TableInitialize>	tableRows;
	private Search					searchWindows;
	private SpeedChart				speedChartWindow;

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
		addSampleDataToTable();
		createScrollPanel();
		createTable();

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
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
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
		iconBar.setBounds(0, 0, 680, 34);
		iconBar.setLayout(null);
		frame.getContentPane().add(iconBar);
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
		statusBar.setBounds(0, 34, 680, 20);
		statusBar.setLayout(null);
		frame.getContentPane().add(statusBar);
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
		settingBar.setBounds(0, 344, 680, 34);
		settingBar.setLayout(null);
		frame.getContentPane().add(settingBar);
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
		newTorrent.setBounds(0, 0, 34, 34);
		iconBar.add(newTorrent);

		newTorrent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(frame);
				selectedFile = file.getSelectedFile().toString();
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
		removeTorrent.setToolTipText("Remove Selected Transmition");
		removeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileRemove.png")));
		removeTorrent.setBounds(34, 0, 34, 34);
		iconBar.add(removeTorrent);
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
		pauseTorrent.setToolTipText("Pause Selected Transmition");
		pauseTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/filePause.png")));
		pauseTorrent.setBounds(102, 0, 34, 34);
		iconBar.add(pauseTorrent);
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
		resumeTorrent.setToolTipText("Resume Selected Transmition");
		resumeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileResume.png")));
		resumeTorrent.setBounds(136, 0, 34, 34);
		iconBar.add(resumeTorrent);
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
		speedChart.setBounds(612, 0, 34, 34);
		iconBar.add(speedChart);

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
		search.setBounds(646, 0, 34, 34);
		iconBar.add(search);
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
		download.setBounds(595, 0, 85, 20);
		statusBar.add(download);
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
		upload.setBounds(510, 0, 85, 20);
		statusBar.add(upload);
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
		darkPeerbtn.setBounds(0, 0, 34, 34);
		
		
		// For Enabling and Disabling a peer as DarkPeer.
		darkPeerbtn.addActionListener(new ActionListener()
				{
		public void actionPerformed(ActionEvent e)
		{
	
			TabbedPane tp =new TabbedPane();
			tp.setVisible(true);							
		}
	});


		if (this.darkStatus == false)
		{
			setDarkPeerBtn(false);

		} else
		{
			setDarkPeerBtn(true);

		}
		settingBar.add(darkPeerbtn);

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

	private void addSampleDataToTable()
	{
		tableRows = new ArrayList<>();

		TableInitialize sampleRow1 = new TableInitialize("1", "Man on the moon.mp4", "100%", "999.9 MB", "999.0 Mbps",
				"3", "99h:59m", "23,Sep,16 / 22:28:30");

		TableInitialize sampleRow2 = new TableInitialize("2", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps",
				"3", "2h:15m", "23,Sep,16 / 21:13:19");

		TableInitialize sampleRow3 = new TableInitialize("3", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3",
				"0h:23m", "23,Sep,16 / 20:08:00");

		tableRows.add(sampleRow1);
		tableRows.add(sampleRow2);
		tableRows.add(sampleRow3);

		createTableDataModel(tableRows);
	}

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
		scrollPane.setBounds(0, 54, 680, 291);
		frame.getContentPane().add(scrollPane);
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
		table = new JTable(model);
		table.setBackground(new Color(212, 239, 253));
		table.setGridColor(new Color(192, 192, 192));
		table.setToolTipText("Your Active Transmitions");
		table.setRowHeight(40);
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

	/**
	 * This method creates the Data Model for the Table in the main frame of the
	 * Octopus P2P client.
	 * 
	 * @author Kamran Alipoursimakani
	 *
	 */

	private void createTableDataModel(List<TableInitialize> transfers)
	{

		fileStatistics = new String[transfers.size()][8];

		int rowIndex = 0;

		for (TableInitialize transfer : transfers)
		{

			List<String> row = transfer.rowCreation();
			for (int columnIndex = 0; columnIndex < columnHeaders.length; columnIndex++)
			{
				fileStatistics[rowIndex][columnIndex] = row.get(columnIndex);
			}

			rowIndex++;
		}

		model = new DefaultTableModel(fileStatistics, columnHeaders);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public String getSelectedFile()
	{
		return selectedFile;
	}

	public void setSelectedFile(String selectedFile)
	{
		this.selectedFile = selectedFile;
	}

	public Search getSearchWindows()
	{
		return searchWindows;
	}

	public void setSearchWindows(Search searchWindows)
	{
		this.searchWindows = searchWindows;
	}

	public SpeedChart getSpeedChartWindow()
	{
		return speedChartWindow;
	}

	public void setSpeedChartWindow(SpeedChart speedChartWindow)
	{
		this.speedChartWindow = speedChartWindow;
	}

}
