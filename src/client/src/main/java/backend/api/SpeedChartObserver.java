package backend.api;

/**
 * This is an interface that is to be implemented by the GUI.
 * 
 * @author iiMaXii
 *
 */
public interface SpeedChartObserver {

	/**
	 * Called repeatedly after a successful call to
	 * {@link BackendController#subscribeSpeedChart(String, SpeedChartObserver)}
	 * 
	 * @param down The download speed since last call
	 */
	public void updateSpeedChart(double down);

}
