package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Client {

	public JFrame frmOctopusP2P;
	public JTable tbOverview;
	public JTable tbPeers;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Client window = new Client();
//					window.frmOctopusP2P.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmOctopusP2P = new JFrame();
		frmOctopusP2P.setTitle("Octopus P2P");
		frmOctopusP2P.setResizable(false);
		frmOctopusP2P.setBounds(100, 100, 800, 630);
		frmOctopusP2P.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOctopusP2P.getContentPane().setLayout(null);
		frmOctopusP2P.setLocationRelativeTo(null);

		/// Top Tabbed Panel

		JTabbedPane tabTop = new JTabbedPane(JTabbedPane.TOP);
		tabTop.setName("");
		tabTop.setBounds(6, 6, 788, 340);

		frmOctopusP2P.getContentPane().add(tabTop);

		JPanel Overview = new JPanel();
		tabTop.addTab("Overview", null, Overview, null);
		Overview.setLayout(null);

		String[] columnHeaders = { "Order", "Name", "Progress", "Size", "Speed", "ETA", "Date Added" };
		String[][] fileStatistics = {
				{ "1", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "2", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "3", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "4", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "5", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "6", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "7", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "8", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "9", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "0h:23m", "23,Sep,16 / 20:08:00" },
				{ "10", "Man on the moon.mp4", "65%", "410 MB", "2.0 Mbps", "1h:35m", "23,Sep,16 / 22:28:30" },
				{ "11", "Woman on the earth.mp4", "32%", "610 MB", "1.2 Mbps", "2h:15m", "23,Sep,16 / 21:13:19" },
				{ "12", "Boy on the mars.mp4", "15%", "330 MB", "4.5 Mbps", "0h:23m", "23,Sep,16 / 20:08:00" }, };

		JScrollPane scrOverview = new JScrollPane();
		scrOverview.setBounds(6, 38, 755, 250);
		Overview.add(scrOverview);

		tbOverview = new JTable(fileStatistics, columnHeaders);
		tbOverview.setBackground(Color.WHITE);
		tbOverview.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tbOverview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbOverview.setGridColor(SystemColor.controlText);
		tbOverview.setFillsViewportHeight(true);
		tbOverview.setAutoCreateRowSorter(true);
		tbOverview.setRowMargin(0);
		tbOverview.setRowHeight(30);
		scrOverview.setViewportView(tbOverview);

		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(67, 6, 117, 29);
		Overview.add(btnPause);

		JButton btnMoveUp = new JButton("Move Up");
		btnMoveUp.setBounds(196, 6, 117, 29);
		Overview.add(btnMoveUp);

		JButton btnMoveDown = new JButton("Move Down");
		btnMoveDown.setBounds(325, 6, 117, 29);
		Overview.add(btnMoveDown);

		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(454, 6, 117, 29);
		Overview.add(btnStop);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(583, 6, 117, 29);
		Overview.add(btnDelete);

		JPanel Download = new JPanel();
		tabTop.addTab("Download", null, Download, null);
		Download.setLayout(null);

		JPanel Upload = new JPanel();
		tabTop.addTab("Upload", null, Upload, null);

		JPanel Search = new JPanel();
		tabTop.addTab("Search", null, Search, null);

		/// Bottom Tabbed Panel

		JTabbedPane tabBottom = new JTabbedPane(JTabbedPane.TOP);
		tabBottom.setBounds(6, 365, 788, 237);
		frmOctopusP2P.getContentPane().add(tabBottom);

		JPanel general = new JPanel();
		tabBottom.addTab("General", null, general, null);
		general.setLayout(null);

		JProgressBar pbDownloaded = new JProgressBar();
		pbDownloaded.setValue(32);
		pbDownloaded.setBounds(104, 6, 596, 16);
		general.add(pbDownloaded);

		JLabel lbpbDownloaded = new JLabel("Downloaded");
		lbpbDownloaded.setBounds(6, 6, 86, 16);
		general.add(lbpbDownloaded);

		JLabel lbpbDownloadedPercent = new JLabel("32%");
		lbpbDownloadedPercent.setBounds(712, 6, 49, 16);
		general.add(lbpbDownloadedPercent);

		JProgressBar pbAvailablity = new JProgressBar();
		pbAvailablity.setValue(95);
		pbAvailablity.setBounds(104, 34, 596, 16);
		general.add(pbAvailablity);

		JLabel lbpbAvailability = new JLabel("Availability");
		lbpbAvailability.setBounds(6, 34, 86, 16);
		general.add(lbpbAvailability);

		JLabel lbpbAvailabilityPercent = new JLabel("95%");
		lbpbAvailabilityPercent.setBounds(712, 34, 49, 16);
		general.add(lbpbAvailabilityPercent);

		JPanel TransferPanel = new JPanel();
		TransferPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		TransferPanel.setName("Transfer");
		TransferPanel.setBounds(6, 62, 755, 123);
		general.add(TransferPanel);
		TransferPanel.setLayout(null);

		JLabel lbTimeElapsed = new JLabel("Time Elapsed:");
		lbTimeElapsed.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbTimeElapsed.setBounds(6, 11, 73, 16);
		TransferPanel.add(lbTimeElapsed);

		JLabel lbDownloaded = new JLabel("Downloaded:");
		lbDownloaded.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbDownloaded.setBounds(6, 38, 73, 16);
		TransferPanel.add(lbDownloaded);

		JLabel lbUploaded = new JLabel("Uploaded:");
		lbUploaded.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbUploaded.setBounds(6, 65, 73, 16);
		TransferPanel.add(lbUploaded);

		JLabel lbSeeds = new JLabel("Seeds:");
		lbSeeds.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbSeeds.setBounds(6, 92, 73, 16);
		TransferPanel.add(lbSeeds);

		JLabel lbTimeElapsedValue = new JLabel("52s");
		lbTimeElapsedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbTimeElapsedValue.setBounds(91, 11, 87, 16);
		TransferPanel.add(lbTimeElapsedValue);

		JLabel lbDownloadedValue = new JLabel("116.15 MB");
		lbDownloadedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbDownloadedValue.setBounds(91, 38, 87, 16);
		TransferPanel.add(lbDownloadedValue);

		JLabel lbUploadedValue = new JLabel("12,4 MB");
		lbUploadedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbUploadedValue.setBounds(91, 65, 87, 16);
		TransferPanel.add(lbUploadedValue);

		JLabel lbSeedsValue = new JLabel("46 Connected");
		lbSeedsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbSeedsValue.setBounds(91, 92, 87, 16);
		TransferPanel.add(lbSeedsValue);

		JLabel lbCompleted = new JLabel("Completed:");
		lbCompleted.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbCompleted.setBounds(487, 92, 73, 16);
		TransferPanel.add(lbCompleted);

		JLabel lbCompletedValue = new JLabel("?");
		lbCompletedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbCompletedValue.setBounds(572, 92, 87, 16);
		TransferPanel.add(lbCompletedValue);

		JLabel lbRemaining = new JLabel("Remaining:");
		lbRemaining.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbRemaining.setBounds(230, 11, 91, 16);
		TransferPanel.add(lbRemaining);

		JLabel lbRemainingValue = new JLabel("1h 12m 52s - 512 MB");
		lbRemainingValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbRemainingValue.setBounds(333, 11, 142, 16);
		TransferPanel.add(lbRemainingValue);

		JLabel lbDownadedSpeed = new JLabel("Download Speed:");
		lbDownadedSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbDownadedSpeed.setBounds(230, 38, 91, 16);
		TransferPanel.add(lbDownadedSpeed);

		JLabel lbDownloadSpeedValue = new JLabel("116.15 MB");
		lbDownloadSpeedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbDownloadSpeedValue.setBounds(333, 38, 142, 16);
		TransferPanel.add(lbDownloadSpeedValue);

		JLabel lbUploadedSpeed = new JLabel("Upload Speed:");
		lbUploadedSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbUploadedSpeed.setBounds(230, 65, 91, 16);
		TransferPanel.add(lbUploadedSpeed);

		JLabel lbUploadSpeedValue = new JLabel("12,4 MB");
		lbUploadSpeedValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbUploadSpeedValue.setBounds(333, 65, 142, 16);
		TransferPanel.add(lbUploadSpeedValue);

		JLabel lbPeers = new JLabel("Peers:");
		lbPeers.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbPeers.setBounds(230, 92, 91, 16);
		TransferPanel.add(lbPeers);

		JLabel lbPeersValue = new JLabel("46 Connected");
		lbPeersValue.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lbPeersValue.setBounds(333, 92, 142, 16);
		TransferPanel.add(lbPeersValue);

		JPanel sources = new JPanel();
		tabBottom.addTab("Sources", null, sources, null);
		sources.setLayout(null);

		JPanel peers = new JPanel();
		tabBottom.addTab("Peers", null, peers, null);
		peers.setLayout(null);

		JScrollPane scrPeers = new JScrollPane();
		scrPeers.setBounds(6, 6, 755, 179);
		peers.add(scrPeers);

		tbPeers = new JTable(fileStatistics, columnHeaders);
		tbPeers.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tbPeers.setGridColor(Color.LIGHT_GRAY);
		scrPeers.setViewportView(tbPeers);

		JPanel swarm = new JPanel();
		tabBottom.addTab("Swarm", null, swarm, null);
		
	////////////////////// PIECES CHART ///////////////////////////


		JPanel pieces = new JPanel();
		tabBottom.addTab("Pieces", null, pieces, null);
		
		

	////////////////////// TIME CHART ///////////////////////////
		
		JPanel speed = new JPanel();
		tabBottom.addTab("Speed", null, speed, "Download and Upload Speed");
		speed.setLayout(new java.awt.BorderLayout());

        final XYSeries series = new XYSeries("Download Speed");
        series.add(1, 400);
        series.add(2, 294);
        series.add(3, 100);
        series.add(3, 734);
        series.add(5, 453);
        series.add(6, 500);
        series.add(7, 600);
        series.add(8, 734);
        series.add(9, 453);
        final XYSeriesCollection pieDataset = new XYSeriesCollection(series);
        
        
        
        final JFreeChart piechart = ChartFactory.createTimeSeriesChart("Speed", "Time", "MBps", pieDataset); //("XY Series Demo","X", "Y", pieDataset,PlotOrientation.VERTICAL,true,true,true);
		ChartPanel CP = new ChartPanel(piechart);
		CP.setMouseWheelEnabled(true);

		CP.setAutoscrolls(true);
		CP.setMouseWheelEnabled(false);
		speed.add(CP,BorderLayout.CENTER);
		speed.validate();

	////////////////////// 3D PIE CHART ///////////////////////////		

		JPanel files = new JPanel();
		tabBottom.addTab("Files", null, files, null);
		
		files.setLayout(new java.awt.BorderLayout());

		DefaultPieDataset PieDataset3D = new DefaultPieDataset();
		PieDataset3D.setValue("One", new Integer(10));
		PieDataset3D.setValue("Two", new Integer(20));
		PieDataset3D.setValue("Three", new Integer(30));
		PieDataset3D.setValue("Four", new Integer(40));
		
		JFreeChart piechart3d = ChartFactory.createPieChart3D("3D", PieDataset3D);
		ChartPanel CP3D = new ChartPanel(piechart3d);
		CP3D.setMouseWheelEnabled(true);
		files.add(CP3D,BorderLayout.CENTER);
		files.validate();

		
		
	}
}
