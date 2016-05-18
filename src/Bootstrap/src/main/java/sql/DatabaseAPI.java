package sql;

public interface DatabaseAPI {

	public void addBootstrapServer(String ip, String name, int clientcount, int servercount);
	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid);
	public void addPeers(String id, String latestIP, int blacklist, String timestamp);
	public void addPeerArray(String uniquefileid, String peers);
	
	//How can they be void when its a get? 
	//Is the meaning that we write to log file and then read from logfile and parse the information that way?
	public void getBootstrapServer();
	public void getSwarm();
	public void getPeers();
	public void getPeerArray();
	//public blacklist getBlacklist();
	//public Sync getSync();
	//public Swarmshelper getSwarms();
	//public Swarm getSwarm(String swarmID)
	//public Bootstraps getBootstraps();
	
	//public void addSwarmDB(String uuidClient, int totalBlocks), String filename, String fileChecksum, String metadataChecksum, String clientID)
	
}
