package sql.api;

import java.util.List;
import backend.api.datatypes.SwarmMetadata;

public interface DatabaseAPI {

	public void addSwarm(String id, String filename, String fileChecksum, String metadataChecksum, int blockCount);
	public void addPeers(String swarmId, String ip);
	public void addPeerArray();
	
	public SwarmMetadata getSwarm();
	public List<String> getPeers();
	public void getPeerArray();
	
}
