package gui;

import java.util.List;

import json.Blacklist;
import json.Peers;
import json.Swarms;

public interface P2pObserver {
	public enum BlockState {
		FINISHED,
		ONGOING,
		NOT_STARTED
	}
	
	public void updateBootstrapSwarms(List<Swarms> swarms);
	public void updateBootstrapBlacklist(Blacklist blacklist);
	public void updateBootstrapPeers(Peers peers);
	
	public void newSwarm(String id, String filename, int blockCount);
	public void updatePeers(String id, Peers swarm);
	
	public void updateSwarmBlock(String id, int blockNumber, BlockState blockState);
	public void updateSwarmBlockAlt(String id, int blockNumber, double progress);
	
	public void searchResult(Swarms swarm);
	
}
