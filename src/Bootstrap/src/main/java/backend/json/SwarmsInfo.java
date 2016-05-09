package backend.json;

import java.util.List;

public class SwarmsInfo extends Swarms{
	private int blockCount;
	private String fileChecksum;
	private String metadataChecksum;
	private List<String> peers;
	
	public SwarmsInfo() {}
	
	public SwarmsInfo(String swarmID, String fileName, int blockCount, String fileChecksum, String metadataChecksum, List<String> peers) 
	{
		super(swarmID, fileName);
		this.blockCount = blockCount;
		this.fileChecksum = fileChecksum;
		this.metadataChecksum = metadataChecksum;
		this.peers = peers;
	}
	
	public int getBlockCount() 
	{
		return blockCount;
	}
	public void setBlockCount(int blockCount) 
	{
		this.blockCount = blockCount;
	}
	public String getFileChecksum() 
	{
		return fileChecksum;
	}
	public void setFileChecksum(String fileChecksum) 
	{
		this.fileChecksum = fileChecksum;
	}
	public String getMetadataChecksum() 
	{
		return metadataChecksum;
	}
	public void setMetadataChecksum(String metadataChecksum) 
	{
		this.metadataChecksum = metadataChecksum;
	}
	public List<String> getPeers() 
	{
		return peers;
	}
	public void setPeers(List<String> peers) 
	{
		this.peers = peers;
	}
	
	
}
