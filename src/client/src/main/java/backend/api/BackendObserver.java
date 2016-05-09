package backend.api;

/**
 * This is an interface that is to be implemented by the GUI.
 * 
 * @author iiMaXii
 *
 */
public interface BackendObserver {
	/**
	 * Called when the metadata has been downloaded after a successful
	 * {@link BackendController#getSwarm(String)}.
	 * 
	 * @param id
	 * @param filename
	 * @param blockCount
	 */
	public void newSwarm(String id, String filename, int blockCount);

	/**
	 * Called when a block has been downloaded. Will not be called unless
	 * {@link BackendController#getSwarm(String)} was previously called.
	 * 
	 * @param id
	 * @param blockNumber
	 * @param correctChecksum
	 *            Whether the block was correctly downloaded
	 */
	public void updateSwarmBlock(String id, int blockNumber, boolean correctChecksum);

	/**
	 * Search result from another peer. Will not be called unless
	 * {@link BackendController#searchSwarm(String)} was previously called. This
	 * method may be called zero to several times for one search.
	 * 
	 * @param clientAddress
	 * @param id
	 * @param filename
	 * @param blockCount
	 */
	public void searchResult(String clientAddress, String id, String filename, int blockCount);
}
