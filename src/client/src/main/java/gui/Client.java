package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Client
{
	public JTable mainTable;
	
	static class MyChooser extends JFileChooser {
	    protected JDialog createDialog(Component parent)
	            throws HeadlessException {
	        JDialog dlg = super.createDialog(parent);
	        dlg.setLocationRelativeTo(null);
	        dlg.setTitle("Select File");
	        return dlg;
	    }
	}

	public JFrame frame;
	public JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				try
//				{
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//					
//					Client window = new Client();
//					window.frame.setVisible(true);
//				} catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		
		
		frame = new JFrame("Octopus P2P");
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Client.class.getResource("/resources/Octopus.png")));
		frame.setBounds(100, 100, 680, 400);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(680, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel iconBar = new JPanel();
		iconBar.setBorder(null);
		iconBar.setBackground(new Color(245, 245, 245));
		iconBar.setName("First Tab");
		
		JPanel bottomStatus = new JPanel();
		bottomStatus.setBackground(new Color(220, 220, 220));
		
		JPanel topStatus = new JPanel();
		topStatus.setBackground(new Color(255, 255, 255));
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Client.class.getResource("/resources/settings.png")));
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Client.class.getResource("/resources/slowDown.png")));
		
				JButton createTorrentFile = new JButton("");
				createTorrentFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						MyChooser file = new MyChooser();
						file.showOpenDialog(createTorrentFile);
						file.setDialogTitle("Kamran");
						System.out.println("This is the file that you want to make a torrent from : "+file.getSelectedFile());
					}
				});
				createTorrentFile.setToolTipText("Create Torrent File");
				createTorrentFile.setIcon(new ImageIcon(Client.class.getResource("/resources/fileNew.png")));
		
				JButton openTorrentFile = new JButton("");
				openTorrentFile.setSize(new Dimension(18, 12));
				
				openTorrentFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						MyChooser file = new MyChooser();
						file.showOpenDialog(openTorrentFile);
						file.setDialogTitle("Kamran");
						System.out.println("This is the file that you want to open : "+file.getSelectedFile());

					}
				});
				openTorrentFile.setIcon(new ImageIcon(Client.class.getResource("/resources/fileOpen.png")));
				openTorrentFile.setToolTipText("Open Torrent Files");
		
				JButton removeTransfer = new JButton("");
				removeTransfer.setToolTipText("Remove Selected Transfer");
				removeTransfer.setIcon(new ImageIcon(Client.class.getResource("/resources/fileRemove.png")));
		
				JButton pauseTransfer = new JButton("");
				pauseTransfer.setToolTipText("Pause Transfer");
				pauseTransfer.setIcon(new ImageIcon(Client.class.getResource("/resources/filePause.png")));
		
				JButton resumeTransfer = new JButton("");
				resumeTransfer.setIcon(new ImageIcon(Client.class.getResource("/resources/fileResume.png")));
				resumeTransfer.setToolTipText("Resume Transfer");
		
		JButton searchFile = new JButton("");
		searchFile.setToolTipText("Search");
		searchFile.setIcon(new ImageIcon(Client.class.getResource("/resources/fileSearch.png")));
		
		JButton fileInfo = new JButton("");
		fileInfo.setIcon(new ImageIcon(Client.class.getResource("/resources/fileInfo.png")));
		
		
		// Setting the layout of the Icons bar! ||vvvvvvvvvvvv
		
		GroupLayout gl_iconBar = new GroupLayout(iconBar);
		gl_iconBar.setHorizontalGroup(
			gl_iconBar.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_iconBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(createTorrentFile, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(openTorrentFile, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(removeTransfer, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(resumeTransfer, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pauseTransfer, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
					.addComponent(searchFile, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fileInfo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_iconBar.setVerticalGroup(
			gl_iconBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_iconBar.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_iconBar.createParallelGroup(Alignment.TRAILING)
						.addComponent(fileInfo)
						.addComponent(searchFile)
						.addComponent(createTorrentFile)
						.addComponent(openTorrentFile)
						.addComponent(removeTransfer)
						.addComponent(pauseTransfer)
						.addComponent(resumeTransfer))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		iconBar.setLayout(gl_iconBar);
		
		// Setting the layout of the Icons bar! ||^^^^^^^^^^^^
		
		
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

		
		JPanel mainTable = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(iconBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(bottomStatus, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
				.addComponent(mainTable, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
				.addComponent(topStatus, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(iconBar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
							.addComponent(bottomStatus, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(mainTable, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
							.addGap(34))
						.addComponent(topStatus, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
		);
		

		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setEnabled(false);

		
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
		
		
		
		
		GroupLayout gl_mainTable = new GroupLayout(mainTable);
		gl_mainTable.setHorizontalGroup(
			gl_mainTable.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
		);
		gl_mainTable.setVerticalGroup(
			gl_mainTable.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
		);
		mainTable.setLayout(gl_mainTable);
		
		JLabel totalTransfers = new JLabel("1 Transfer");
		totalTransfers.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GroupLayout gl_bottomStatus = new GroupLayout(bottomStatus);
		gl_bottomStatus.setHorizontalGroup(
			gl_bottomStatus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomStatus.createSequentialGroup()
					.addGap(6)
					.addComponent(button)
					.addGap(12)
					.addComponent(button_1)
					.addPreferredGap(ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
					.addComponent(totalTransfers)
					.addContainerGap())
		);
		gl_bottomStatus.setVerticalGroup(
			gl_bottomStatus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomStatus.createSequentialGroup()
					.addGroup(gl_bottomStatus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_bottomStatus.createSequentialGroup()
							.addGap(6)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_bottomStatus.createSequentialGroup()
							.addGap(6)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_bottomStatus.createSequentialGroup()
							.addGap(11)
							.addComponent(totalTransfers)))
					.addContainerGap(7, Short.MAX_VALUE))
		);
		bottomStatus.setLayout(gl_bottomStatus);
		
		JLabel uploadSpeed = new JLabel("999.9 KB/s");
		uploadSpeed.setIcon(new ImageIcon(Client.class.getResource("/resources/upload.png")));
		uploadSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
		uploadSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JLabel downloadSpeed = new JLabel("999.9 KB/s");
		downloadSpeed.setIcon(new ImageIcon(Client.class.getResource("/resources/download.png")));
		downloadSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		downloadSpeed.setAlignmentX(0.5f);
		GroupLayout gl_topStatus = new GroupLayout(topStatus);
		gl_topStatus.setHorizontalGroup(
			gl_topStatus.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_topStatus.createSequentialGroup()
					.addContainerGap(522, Short.MAX_VALUE)
					.addComponent(downloadSpeed)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(uploadSpeed)
					.addContainerGap())
		);
		gl_topStatus.setVerticalGroup(
			gl_topStatus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topStatus.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_topStatus.createParallelGroup(Alignment.BASELINE)
						.addComponent(uploadSpeed)
						.addComponent(downloadSpeed))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topStatus.setLayout(gl_topStatus);
		frame.getContentPane().setLayout(groupLayout);

	}
}
