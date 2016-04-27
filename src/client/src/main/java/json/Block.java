package json;

public class Block {
	private int blockNr;
	private int blockSize;
	private String checksum;
	
	
	public Block() {
	}
	public Block(int blockNr, int blockSize, String checksum) {
		super();
		this.blockNr = blockNr;
		this.blockSize = blockSize;
		this.checksum = checksum;
	}
	public int getBlockNr() {
		return blockNr;
	}
	public void setBlockNr(int blockNr) {
		this.blockNr = blockNr;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
}
