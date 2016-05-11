package backend.api.datatypes;

import java.util.List;

public interface SwarmMetadata {
	public String getId();
	public String getFilename();
	public int getBlockCount();
	public String getFileChecksum();
	public String getMetadataChecksum();
	public List<String> getPeers();
}
