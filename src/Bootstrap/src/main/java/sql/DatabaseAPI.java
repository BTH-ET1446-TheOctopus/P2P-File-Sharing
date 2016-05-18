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
	
	//How can they be void when its a get? 
	//Is the meaning that we write to log file and then read from logfile and parse the information that way?
	public void getBootstrapServer();
	public void getSwarm();
	public void getPeerArray();
	
	public Peers getpeers();
	
	public boolean isPeerIDExisting(String id);
	public boolean updatePeer(String ip, String id, String timestamp);
	
	public getIPoStatus getPeers();
	public Blacklist getBlacklist();
	public Sync getSync();
	public boolean isBlacklisted(String ip);
	
	public SwarmsHelper getSwarms();
	public Swarm getSwarm(String swarmID);
	public Bootstraps getBootstraps();
	
	//public void addSwarmDB(String uuidClient, int totalBlocks), String filename, String fileChecksum, String metadataChecksum, String clientID)
	
}
