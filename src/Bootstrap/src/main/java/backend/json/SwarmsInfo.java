package backend.json;

public class SwarmsInfo extends Swarms{
	private int blockCount;
	private String fileChecksum;
	private String metadataChecksum;
	private Peers peers;
	
	public SwarmsInfo() {}
	
	public SwarmsInfo(String swarmID, String fileName, int blockCount, String fileChecksum, String metadataChecksum, Peers peers) 
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
	public Peers getPeers() 
	{
		return peers;
	}
	public void setPeers(Peers peers) 
	{
		this.peers = peers;
	}
	
	
}
