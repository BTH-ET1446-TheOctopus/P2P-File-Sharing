package backend.api;

import java.util.List;

import backend.api.datatypes.SwarmMetadataShort;
import backend.json.Swarm;

/**
 * This is an interface that is to be implemented by the backend.
 * 
 * @author iiMaXii
 *
 */
public interface BackendController {

	/**
	 * Get a list of all swarms on the bootstrap server.
	 * 
	 * @return A list of swarm metadata
	 */
	public List<SwarmMetadataShort> getSwarms();

	/**
	 * Get a list of known peers.
	 * 
	 * @return A list of IP addresses
	 */
	public List<String> getPeers();

	/**
	 * Get a list of bootstrap servers.
	 * 
	 * @return A list of IP addresses
	 */
	public List<String> getBootstraps();

	/**
	 * Get a list of blacklisted IP addresses.
	 * 
	 * @return A list of IP addresses
	 */
	public List<String> getBlacklist();

	/**
	 * Fetch swarm metadata and start downloading the file. The function will
	 * return immediately. When the metadata has been retrieved the backend will
	 * issue a call to {@link BackendObserver#newSwarm(String, String, int)}.
	 * Later the backend will call
	 * {@link BackendObserver#updateSwarmBlock(String, int, boolean)}
	 * 
	 * @param id
	 *            The swarm uuid
	 * @return True on success or false if the swarm has already been added
	 */
	public boolean engageSwarm(String id);

	/**
	 * Remove a swarm.
	 * 
	 * @param id
	 *            The swarm uuid
	 * @return True on success or false if the swarm doesn't exist
	 */
	public boolean disengageSwarm(String id);

	/**
	 * @deprecated Replaced by {@link #engageSwarm(String)}
	 */
	@Deprecated
	public Swarm getSwarm(String id);

	/**
	 * @deprecated Replaced by {@link #disengageSwarm(String)}
	 */
	@Deprecated
	public void removeSwarm(String id);

	/**
	 * Create a new swarm. The function will return immediately. When the swarm
	 * has been created the backend will issue a call to
	 * {@link BackendObserver#newSwarm(String, String, int, String)}.
	 * 
	 * @param filename
	 */
	public void createSwarm(String filename);

	/**
	 * Search for a file among other peers. The function will return
	 * immediately. When or if a result is found the backend will issue a call
	 * to {@link BackendObserver#searchResult(String, String, String, int)}.
	 * Note that you can receive multiple calls or even zero calls to this
	 * function.
	 * 
	 * @param filename
	 */
	public void searchSwarm(String filename);

	/**
	 * Set the mode to dark, meaning that the backend it will not attempt to
	 * communicate with the bootstrap server.
	 * 
	 * @param dark
	 *            Whether to set the backend to dark mode
	 */
	public void setDark(boolean dark);

	/**
	 * Tell the backend to send speed up/down statistics using the
	 * {@link BackendObserver#speedChartUpdate(double, double)}.
	 * 
	 * @param id
	 *            The swarm identifier
	 */
	public void subscribeSpeedChart(String id, SpeedChartObserver callback);

	/**
	 * Tell the backend to stop sending up/down statistics.
	 * 
	 * @param id
	 *            The swarm identifier
	 */
	public void unsubscribeSpeedChart();

}
