package backend.json;

import java.util.List;

public class Swarm {
	private int blockCount;
	private String filename;
	private String fileChecksum;
	private String metadataChecksum;
	private List<String> peers;
	
	
	public Swarm() {
	}
	public Swarm(int blockCount, String filename, String fileChecksum, String metadataChecksum, List<String> peers) {
		super();
		this.blockCount = blockCount;
		this.filename = filename;
		this.fileChecksum = fileChecksum;
		this.metadataChecksum = metadataChecksum;
		this.peers = peers;
	}



	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileChecksum() {
		return fileChecksum;
	}


	public void setFileChecksum(String fileChecksum) {
		this.fileChecksum = fileChecksum;
	}


	public String getMetadataChecksum() {
		return metadataChecksum;
	}


	public void setMetadataChecksum(String metadataChecksum) {
		this.metadataChecksum = metadataChecksum;
	}


	public List<String> getPeers() {
		return peers;
	}


	public void setPeers(List<String> peers) {
		this.peers = peers;
	}
	
	@Override
	public String toString() 
	{
		String stringBuilder = "peers: " + peers.toString();
		stringBuilder += "metadataChecksum: " + this.metadataChecksum + "\n"
				+ "fileChecksum:" + this.fileChecksum + "\n"
				+ "blockCount:" + this.blockCount + "\n"
				+ "filename:" + this.filename + "\n"; 
		
		return stringBuilder;
		
	}
}
