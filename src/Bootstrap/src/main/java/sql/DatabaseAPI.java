package sql;

public interface DatabaseAPI {

	public void addBootstrapServer(String ip, String name, int clientcount, int servercount);
	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid);
	public void addPeers(String id, String latestIP, int blacklist);
	public void addPeerArray(String uniquefileid, String peers);
	
	public void getBootstrapServer();
	public void getSwarm();
	public void getPeers();
	public void getPeerArray();
	
}
