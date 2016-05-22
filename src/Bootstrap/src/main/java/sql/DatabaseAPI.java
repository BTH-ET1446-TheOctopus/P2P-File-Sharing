package sql;

import sql.DatabaseCalls.getIPoStatus;
import backend.json.SwarmsHelper;
import backend.json.Swarm;
import backend.json.Peers;

import java.util.List;

import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Sync;


public interface DatabaseAPI {

	public boolean addBootstrapServer(String ip, String name, int clientcount, int servercount);
	public boolean addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid);
	public void addPeers(String id, String latestIP, boolean blacklist, String timestamp);
	//public void addPeerArray(String uniquefileid, String peers);
	
	public boolean isPeerIDExisting(String id);
	public boolean updatePeer(String ip, String id, String timestamp);
	public boolean isBlacklisted(String ip);
	
	public boolean isSwarmExisting(String swarmID);
	public boolean isClientOnSwarm(String swarmID, String clientID);
	public boolean updateSwarm(String swarmID, String clientID);
	
	public boolean addPeer(String id, String latestIP, boolean blacklist, String timestamp);
	public void closedbconnect();
	
	public getIPoStatus getPeers();
	public List<String> getBlacklist();
	public Sync getSync();
	public List<String> getpeers();
	public SwarmsHelper getSwarms();
	public Swarm getSwarm(String swarmID);
	public List<String> getBootstraps();
	
	public Peers getInactivePeers(int timeout);
	public boolean removePeers(String CientUUID); 
	
	//public boolean getSwarmByName(String filename);
	public boolean addSwarmDB(String uuidClient, int totalBlocks, String filename, String fileChecksum, String metadataChecksum, String SwarmID);
	
}
