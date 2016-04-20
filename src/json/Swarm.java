package json;

import java.util.List;

public class Swarm {
	private int numOfBlocks;
	private String Filename;
	private String fileCheckSum;
	private List<Block> blocks;
	private String metadataCheckSum;
	
	
	public Swarm() {
	
	}
	
	public Swarm(int numOfBlocks, String filename, String fileCheckSum, List<Block> blocks, String metadataCheckSum) {
		super();
		this.numOfBlocks = numOfBlocks;
		Filename = filename;
		this.fileCheckSum = fileCheckSum;
		this.blocks = blocks;
		this.metadataCheckSum = metadataCheckSum;
	}
	public int getNumOfBlocks() {
		return numOfBlocks;
	}
	public void setNumOfBlocks(int numOfBlocks) {
		this.numOfBlocks = numOfBlocks;
	}
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
	}
	public String getFileCheckSum() {
		return fileCheckSum;
	}
	public void setFileCheckSum(String fileCheckSum) {
		this.fileCheckSum = fileCheckSum;
	}
	public List<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	public String getMetadataCheckSum() {
		return metadataCheckSum;
	}
	public void setMetadataCheckSum(String metadataCheckSum) {
		this.metadataCheckSum = metadataCheckSum;
	}
	
}
