package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Client
{
	public boolean		darkStatus;
	public JFrame		frame;
	public JPanel		iconBar;
	public JPanel		statusBar;
	public JPanel		settingBar;
	public JButton		newTorrent;
	public JButton		removeTorrent;
	public JButton		pauseTorrent;
	public JButton		resumeTorrent;
	public JButton		moreInfo;
	public JButton		search;
	public JLabel		download;
	public JLabel		upload;
	public JTable		table;
	public JScrollPane	scrollPane;
	public String[]		columnHeaders;
	public String[][]	fileStatistics;
	public String		selectedFile;
	public JDialog		settings;
	public JCheckBox	darkPeer;
	public JButton		applySetting;
	public JButton		darkPeerbtn;

	/**
	 * Create the application.
	 */

	public Client()
	{
		initialize();
	}

	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	///////////////// Setting DarkPeer Button //////////////////////////

	private void setDarkPeerBtn(boolean status)
	{
		if (status == true)
		{
			darkStatus = true;
			darkPeerbtn.setToolTipText("Your machine is invisible to the servers!");
			darkPeerbtn.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/darkPeer.png")));

		} else
		{
			darkStatus = false;
			darkPeerbtn.setToolTipText("Your machine is visible to the servers!");
			darkPeerbtn.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/brightPeer.png")));

		}
	}

	///////////////// Setting DarkPeer Button //////////////////////////
	////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////

	private void createTable(List<TableInitialize> transfers)
	{

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		/////////////////////// Table Contents /////////////////////////////

		// Creating the Table Model Variables

		fileStatistics = new String[transfers.size()][8];

		String[] columnHeaders =
		{ "Priority", "Name", "Progress", "Size", "Speed", "Peers", "ETC", "Date Added" };

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

		DefaultTableModel model = new DefaultTableModel(fileStatistics, columnHeaders);

		/////////////////////// Table Contents /////////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		/////////////////////// Table ScrollBar ////////////////////////////

		// Creating the tableScroll Panel

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 680, 291);
		frame.getContentPane().add(scrollPane);

		/////////////////////// Table ScrollBar ////////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		//////////////////////////// Table /////////////////////////////////

		// Creating the Table using the model above

		table = new JTable(model);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		// Setting the table columns to align to Center

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

		//////////////////////////// Table /////////////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

	}

	/**
	 * Initialize the contents of the frame.
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
		createMoreInfoButton();
		createSearchButton();
		createDownloadLable();
		createUploadLable();

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////// Setting Bar//////////////////////////////

		// Adding Go Dark Button to the Setting Bar

		darkPeerbtn = new JButton();
		if (darkStatus == false)
		{
			setDarkPeerBtn(false);

		} else
		{
			setDarkPeerBtn(true);

		}
		darkPeerbtn.setBounds(0, 0, 34, 34);
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

		////////////////////////// Setting Bar//////////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		//////////////////// Table Creation Sample /////////////////////////

		// This is how back-end should update and create the table!

		List<TableInitialize> tableRows = new ArrayList<>();

		TableInitialize sampleRow1 = new TableInitialize("1", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "3",
				"1h:35m", "23,Sep,16 / 22:28:30");

		TableInitialize sampleRow2 = new TableInitialize("2", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps",
				"3", "2h:15m", "23,Sep,16 / 21:13:19");

		TableInitialize sampleRow3 = new TableInitialize("3", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3",
				"0h:23m", "23,Sep,16 / 20:08:00");

		tableRows.add(sampleRow1);
		tableRows.add(sampleRow2);
		tableRows.add(sampleRow3);

		createTable(tableRows);

		//////////////////// Table Creation Sample /////////////////////////
		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////

	}

	private void createMainJFrame()
	{
		frame = new JFrame("The Octopus P2P");
		frame.setSize(new Dimension(680, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}

	private void createIconBar()
	{
		iconBar = new JPanel();
		iconBar.setBackground(new Color(245, 245, 245));
		iconBar.setBounds(0, 0, 680, 34);
		iconBar.setLayout(null);
		frame.getContentPane().add(iconBar);
	}

	private void createStatusBar()
	{
		statusBar = new JPanel();
		statusBar.setBackground(new Color(255, 255, 255));
		statusBar.setBounds(0, 34, 680, 20);
		statusBar.setLayout(null);
		frame.getContentPane().add(statusBar);
	}

	private void createSettingBar()
	{
		settingBar = new JPanel();
		settingBar.setBackground(new Color(245, 245, 245));
		settingBar.setBounds(0, 344, 680, 34);
		settingBar.setLayout(null);
		frame.getContentPane().add(settingBar);
	}

	private void createNewTorrentButton()
	{
		newTorrent = new JButton();
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

	private void createRemoveTorrentButton()
	{
		removeTorrent = new JButton();
		removeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileRemove.png")));
		removeTorrent.setBounds(34, 0, 34, 34);
		iconBar.add(removeTorrent);
	}

	private void createPauseTorrentButton()
	{
		pauseTorrent = new JButton();
		pauseTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/filePause.png")));
		pauseTorrent.setBounds(102, 0, 34, 34);
		iconBar.add(pauseTorrent);
	}

	private void createResumeTorrentButton()
	{
		resumeTorrent = new JButton();
		resumeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileResume.png")));
		resumeTorrent.setBounds(136, 0, 34, 34);
		iconBar.add(resumeTorrent);
	}

	private void createMoreInfoButton()
	{
		moreInfo = new JButton();
		moreInfo.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileInfo.png")));
		moreInfo.setBounds(612, 0, 34, 34);
		iconBar.add(moreInfo);
	}

	private void createSearchButton()
	{
		search = new JButton();
		search.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileSearch.png")));
		search.setBounds(646, 0, 34, 34);
		iconBar.add(search);
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				Search search = new Search(frame);

			}
		});
	}

	private void createDownloadLable()
	{
		download = new JLabel();
		download.setText("999.9 MB");
		download.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/download.png")));
		download.setBounds(595, 0, 85, 20);
		statusBar.add(download);
	}
	
	private void createUploadLable()
	{
		upload = new JLabel();
		upload.setText("999.9 MB");
		upload.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/upload.png")));
		upload.setBounds(510, 0, 85, 20);
		statusBar.add(upload);		
	}

}
