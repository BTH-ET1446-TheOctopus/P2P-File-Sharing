package sql;

import sql.DatabaseCalls.getIPoStatus;
import backend.json.SwarmsHelper;
import backend.json.Swarm;
import backend.json.Peers;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Sync;


public interface DatabaseAPI {

	public void addBootstrapServer(String ip, String name, int clientcount, int servercount);
	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid);
	public void addPeers(String id, String latestIP, boolean blacklist, String timestamp);
	public void addPeerArray(String uniquefileid, String peers);
	
	public boolean isPeerIDExisting(String id);
	public boolean updatePeer(String ip, String id, String timestamp);
	public boolean isBlacklisted(String ip);
	
	//public boolean isSwarmExisting(String swarmID);
	//public boolean isClientOnSwarm(String swarmID, String clientID);
	//public boolean updateSwarm(String swarmID, String clientID);
	
	public getIPoStatus getPeers();
	public Blacklist getBlacklist();
	public Sync getSync();
	public Peers getpeers();
	public SwarmsHelper getSwarms();
	public Swarm getSwarm(String swarmID);
	public Bootstraps getBootstraps();
	public boolean getSwarmByName(String filename);
	
	//public void addSwarmDB(String uuidClient, int totalBlocks), String filename, String fileChecksum, String metadataChecksum, String clientID)
	
}
