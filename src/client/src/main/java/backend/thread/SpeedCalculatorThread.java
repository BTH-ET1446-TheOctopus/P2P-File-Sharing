package backend.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.SpeedChartObserver;

public class SpeedCalculatorThread extends Thread {
	private final static Logger LOG = Logger.getLogger(SpeedCalculatorThread.class.getName());

	public final static int UPDATE_INTERVAL = 3; // seconds

	private SpeedChartObserver speedChartObserver;

	private int uplinkBytes;
	private int downlinkBytes;

	public SpeedCalculatorThread(SpeedChartObserver speedChartObserver) {
		this.speedChartObserver = speedChartObserver;
		
		uplinkBytes = 0;
		downlinkBytes = 0;
	}

	public void reportDownload(int bytes) {
		downlinkBytes += bytes;
	}

	public void reportUpload(int bytes) {
		uplinkBytes += bytes;
	}

	@Override
	public void run() {
		try {
			long timestampStart;
			long elapsedTime;
			long waitTime;

			while (true) {
				timestampStart = System.currentTimeMillis();

				double up = uplinkBytes;
				uplinkBytes = 0;
				double down = downlinkBytes;
				downlinkBytes = 0;

				speedChartObserver.updateSpeedChart(up / UPDATE_INTERVAL, down / UPDATE_INTERVAL);
				LOG.log(Level.INFO, "uplink={0}, downlink={1}", new Object[] {up / UPDATE_INTERVAL, down / UPDATE_INTERVAL});

				elapsedTime = System.currentTimeMillis() - timestampStart;
				waitTime = (UPDATE_INTERVAL * 1000 <= elapsedTime) ? 0 : UPDATE_INTERVAL * 1000 - elapsedTime;
				Thread.sleep(waitTime);
			}
		} catch (InterruptedException e) {
		}
	}

}
