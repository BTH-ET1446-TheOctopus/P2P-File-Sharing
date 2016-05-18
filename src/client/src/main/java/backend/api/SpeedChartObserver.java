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
	 * {@link BackendController#subscribeSpeedChart(String)}
	 * 
	 * @param up
	 * @param down
	 */
	public void updateSpeedChart(double up, double down);

}
