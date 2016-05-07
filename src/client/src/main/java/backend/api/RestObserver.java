package backend.api;

import backend.json.Peers;
import backend.json.Swarms;

public interface RestObserver {
	public enum BlockState {
		OK,
		INVALID_CHECKSUM
	}

	public void newSwarm(String id, String filename, int blockCount);
	public void updatePeers(String id, Peers swarm);
	public void updateSwarmBlock(String id, int blockNumber, BlockState blockState);

	public void searchResult(Swarms swarm);
}
