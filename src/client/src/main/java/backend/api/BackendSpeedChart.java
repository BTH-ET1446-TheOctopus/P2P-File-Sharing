package backend.api;
import java.util.List;

public interface BackendSpeedChart {

	public void SubscribeSpeedChart(String id);
	/**
	 * Subscribed only to the file being selected 
	 */
		
		
	public void UnsubscribeSpeedChart(String id);
	/**
	 * Unsubscribed so that file previously being selected can be un-linked 
	 * and we can be able to subscribe to any other file
	 */
		
}
