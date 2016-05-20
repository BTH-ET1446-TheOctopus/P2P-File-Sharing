package sql;

import java.util.List;
import backend.api.datatypes.SwarmMetadata;

public interface DatabaseAPI {

	public void addSwarm(String id, String filename, String fileChecksum, String metadataChecksum, int blockCount);
	public void addPeers(String swarmId, String ip); //What is the difference from the one bellow? 
	//public void addPeerArray(String uniquefileid, String peers); //What is the difference from the one above
	
	public SwarmMetadata getSwarmByName(String filename);
	//public SwarmMetadata getSwarm(String id);
	public List<String> getPeers();
	public List<String> getconnPeers();
	public void getPeerArray();
	
	public void closedbconnect();
	//public List<String> getBlacklist();
	//public List<String> getBootstraps();
	//public List<Integer> getChunks(String fileID);
	//public boolean checkChunkAvaible(String id, Integer chunkNr);
	//public boolean isBlacklisted(String ip);
	
	
	//public void addBootstraps(ArrayList<String> ip);
	//public void addBlacklist(ArrayList<String> ip);
	//public void addChunks(String id, Integear chunk);
	
	
	
	
	
}
