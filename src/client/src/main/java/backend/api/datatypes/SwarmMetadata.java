package backend.api.datatypes;

import java.util.List;

public class SwarmMetadata {
	String id;
	String filename;
	String blockCount;
	String fileChecksum;
	String metadataChecksum;
	List<String> peers;

	public SwarmMetadata() {
	}
	
	public SwarmMetadata(String id, String filename, String blockCount, String fileChecksum, String metadataChecksum,
			List<String> peers) {
		this.id = id;
		this.filename = filename;
		this.blockCount = blockCount;
		this.fileChecksum = fileChecksum;
		this.metadataChecksum = metadataChecksum;
		this.peers = peers;
	}

	public String getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public String getBlockCount() {
		return blockCount;
	}

	public String getFileChecksum() {
		return fileChecksum;
	}

	public String getMetadataChecksum() {
		return metadataChecksum;
	}

	public List<String> getPeers() {
		return peers;
	}
}
