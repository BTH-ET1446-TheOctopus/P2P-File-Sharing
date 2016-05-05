package backend.json;

public class Chunk {
	private Integer sequenceNumber;
	private Integer size;
	private String checksum;
	private String data;
	
	
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	} 
	
	
}

