package backend.api.datatypes;

import java.util.List;

public class SwarmMetadataShort {
	private String id;
	private String filename;
	private List<String> peers;

	public SwarmMetadataShort(String id, String filename, List<String> peers) {
		this.id = id;
		this.filename = filename;
		this.peers = peers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public List<String> getPeers() {
		return peers;
	}

	public void setPeers(List<String> peers) {
		this.peers = peers;
	}
}
