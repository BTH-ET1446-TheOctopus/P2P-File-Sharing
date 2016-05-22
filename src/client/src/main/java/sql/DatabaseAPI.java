package sql;

import java.util.ArrayList;
import java.util.List;
import backend.api.datatypes.SwarmMetadata;

public interface DatabaseAPI {

	//add functions
	public boolean addSwarm(String swarmid, String filename, String fileChecksum, String metadataChecksum, int blockCount, String clientID);
	public boolean addPeers(String swarmId, String ip); //What is the difference from the one bellow? 
	public boolean addPeerArray(String uniquefileid, String peers); //What is the difference from the one above
	//public void addChunks(String id, ArrayList<Integer> chunk);
		
	//get functions
	public List<String> getPeers();
	public List<String> getconnPeers();
	public void getPeerArray();
	public String getSwarmName(String swarmID);
	public SwarmMetadata getSwarmByName(String filename);
	
	//Check Existing or Availability
	public boolean isSwarmExisting(String swarmId);
	public boolean checkChunkAvaible(String id, Integer chunkNr);
	
	//Closing DB Connection
	public void closedbconnect();
	
	//Delete Swarm
	public boolean deleteSwarmID(String swarmID);
	
	//Need to implement these
	//public List<String> getBlacklist();
	//public List<String> getBootstraps();
	//public List<Integer> getChunks(String fileID);
	//public boolean isBlacklisted(String ip);
	//public SwarmMetadata getSwarm(String id);
}