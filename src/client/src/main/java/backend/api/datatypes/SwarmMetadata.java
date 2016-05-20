package backend.api.datatypes;

import java.util.List;

public class SwarmMetadata {
	private String id;
	private String filename;
	private int blockCount;
	private String fileChecksum;
	private String metadataChecksum;
	private List<String> peers;
	private boolean dark;

	public SwarmMetadata() {}

	public SwarmMetadata(String id, String filename, int blockCount, String fileChecksum, String metadataChecksum,
			List<String> peers) {
		this.id = id;
		this.filename = filename;
		this.blockCount = blockCount;
		this.fileChecksum = fileChecksum;
		this.metadataChecksum = metadataChecksum;
		this.peers = peers;
		this.dark = false;
	}

	public SwarmMetadata(String id, String filename, int blockCount, String fileChecksum, String metadataChecksum,
			List<String> peers, boolean isDark) {
		this.id = id;
		this.filename = filename;
		this.blockCount = blockCount;
		this.fileChecksum = fileChecksum;
		this.metadataChecksum = metadataChecksum;
		this.peers = peers;
		this.dark = isDark;
	}

	public String getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public int getBlockCount() {
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

	public boolean isDark() {
		return dark;
	}
}
