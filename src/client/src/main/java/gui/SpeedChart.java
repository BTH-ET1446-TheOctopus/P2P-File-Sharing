package gui;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A demonstration application showing how to create a combined chart.
 */
public class SpeedChart extends JFrame
{
	public static String fileName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new demonstration application.
	 * 
	 * @param title
	 *            the frame title.
	 */
	public SpeedChart(String title)
	{

		super("Live Speed Charts");
		fileName = title;
		JFreeChart chart = createCombinedChart();
		ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(panel);

	}

	/**
	 * Creates a combined chart.
	 * 
	 * @return The combined chart.
	 */
	private static JFreeChart createCombinedChart()
	{

		// create downloadSpeed...
		
		XYDataset downloadSpeed = createDatasetDownload();
		XYItemRenderer downloadRenderer = new StandardXYItemRenderer();
		NumberAxis rangeAxis1 = new NumberAxis("Download");
		XYPlot downloadPlot = new XYPlot(downloadSpeed, null, rangeAxis1, downloadRenderer);
		downloadPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

		// create uploadSpeed...
		
		XYDataset uploadSpeed = createDatasetUpload();
		XYItemRenderer uploadRenderer = new StandardXYItemRenderer();
		NumberAxis rangeAxis2 = new NumberAxis("Upload");
		XYPlot uploadPlot = new XYPlot(uploadSpeed, null, rangeAxis2, uploadRenderer);
		uploadPlot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		// parent plot...
		
		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Time"));
		plot.setGap(1.0);

		// add the subplots...
		
		plot.add(uploadPlot, 1);
		plot.add(downloadPlot, 1);
		plot.setOrientation(PlotOrientation.VERTICAL);

		// return a new chart containing the overlaid plot...
		return new JFreeChart(fileName, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return downloadSeries.
	 */
	private static XYDataset createDatasetDownload()
	{

		// create downloadSeries dataset...
		XYSeries downloadSeries = new XYSeries("Download Speed");
		downloadSeries.add(1800, 1.3);
		downloadSeries.add(1801, 2.4);
		downloadSeries.add(1802, 4.3);
		downloadSeries.add(1803, 5.3);
		downloadSeries.add(1804, 4.4);
		downloadSeries.add(1805, 3.3);
		downloadSeries.add(1806, 4.5);
		downloadSeries.add(1807, 5.3);
		downloadSeries.add(1808, 7.4);
		downloadSeries.add(1809, 6.6);
		downloadSeries.add(1810, 7.3);
		downloadSeries.add(1811, 7.2);
		downloadSeries.add(1812, 8.2);
		downloadSeries.add(1803, 8.2);
		downloadSeries.add(1804, 8.1);

		return new XYSeriesCollection(downloadSeries);

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return uploadSeries.
	 */
	private static XYDataset createDatasetUpload()
	{

		// create uploadSeries dataset...

		XYSeries uploadSeries = new XYSeries("Upload Speed");
		uploadSeries.add(1800, 1.3);
		uploadSeries.add(1801, 2.4);
		uploadSeries.add(1802, 4.3);
		uploadSeries.add(1803, 5.3);
		uploadSeries.add(1804, 4.4);
		uploadSeries.add(1805, 3.3);
		uploadSeries.add(1806, 4.5);
		uploadSeries.add(1807, 5.3);
		uploadSeries.add(1808, 7.4);
		uploadSeries.add(1809, 6.6);
		uploadSeries.add(1810, 7.3);
		uploadSeries.add(1811, 7.2);
		uploadSeries.add(1812, 8.2);
		uploadSeries.add(1803, 8.2);
		uploadSeries.add(1804, 8.1);

		return new XYSeriesCollection(uploadSeries);

	}

}