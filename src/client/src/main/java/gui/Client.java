package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Client
{

	public JFrame frame;
	public JTable table;
	public JScrollPane scrollPane;
	public String[] columnHeaders;
	public String[][] fileStatistics;

	/**
	 * Create the application.
	 */

	public Client()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize()
	{

		// Creating the main frame

		frame = new JFrame("The Octopus P2P");
		frame.setSize(new Dimension(680, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// Creating the iconBar Panel

		JPanel iconBar = new JPanel();
		iconBar.setBackground(new Color(245, 245, 245));
		iconBar.setBounds(0, 0, 680, 34);
		iconBar.setLayout(null);
		frame.getContentPane().add(iconBar);
		
		// Creating the statusBar Panel

		JPanel statusBar = new JPanel();
		statusBar.setBackground(new Color(255,255,255));
		statusBar.setBounds(0, 34, 680, 20);
		statusBar.setLayout(null);
		frame.getContentPane().add(statusBar);

		// Creating the settingBar Panel

		JPanel settingBar = new JPanel();
		settingBar.setBackground(new Color(245, 245, 245));
		settingBar.setBounds(0, 344, 680, 34);
		settingBar.setLayout(null);
		frame.getContentPane().add(settingBar);
		
		// Adding Create Torrent Button to the iconBar
		
		JButton newTorrent = new JButton();
		newTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileNew.png")));
		newTorrent.setBounds(0, 0, 34, 34);
		iconBar.add(newTorrent);
		
		// Adding Remove Torrent Button to the iconBar
		
		JButton removeTorrent = new JButton();
	    removeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileRemove.png")));
	    removeTorrent.setBounds(34, 0, 34, 34);
	    iconBar.add(removeTorrent);
	    
		// Adding Pause Selected Row Button to the iconBar
		
		JButton pauseTorrent = new JButton();
		pauseTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/filePause.png")));
		pauseTorrent.setBounds(102, 0, 34, 34);
	    iconBar.add(pauseTorrent);
	    
		// Adding Resume Selected Row Button to the iconBar
		
		JButton resumeTorrent = new JButton();
		resumeTorrent.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileResume.png")));
		resumeTorrent.setBounds(136, 0, 34, 34);
	    iconBar.add(resumeTorrent);
	    
		// Adding More Info Button to the iconBar
		
		JButton moreInfo = new JButton();
		moreInfo.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileInfo.png")));
		moreInfo.setBounds(612, 0, 34, 34);
	    iconBar.add(moreInfo);
	    
		// Adding Search Button to the iconBar
		
		JButton search = new JButton();
		search.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/fileSearch.png")));
		search.setBounds(646, 0, 34, 34);
	    iconBar.add(search);
	    
		// Adding Setting Button to the Setting Bar
		
		JButton setting = new JButton();
		setting.setIcon(new ImageIcon(Client.class.getResource("/gui/resources/settings.png")));
		setting.setBounds(0, 0, 34, 34);
	    settingBar.add(setting);
						
		// Creating the Table Model Variables
		
		String[] columnHeaders = { "Priority", "Name", "Progress", "Size", "Speed", "Peers", "ETA", "Date Added" };
		String[][] fileStatistics = {
				{ "1", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "3","1h:35m", "23,Sep,16 / 22:28:30" },
				{ "2", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "3", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "3", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "4", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "3", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "5", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "3", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "6", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "7", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "3", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "8", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "3", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "9", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "10", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "3", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "11", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "3", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "12", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "3", "0h:23m", "23,Sep,16 / 20:08:00" }, };

		DefaultTableModel model = new DefaultTableModel(fileStatistics,columnHeaders); 
		
		// Creating the tableScroll Panel

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 680, 291);
		frame.getContentPane().add(scrollPane);
		
		// Creating the Table using the model above

		table = new JTable(model);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);


		// Setting the table columns to align to Center
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		
	}
}
