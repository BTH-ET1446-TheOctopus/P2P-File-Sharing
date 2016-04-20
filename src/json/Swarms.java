package json;

public class Swarms {
	private String swarmID;
	private String fileName;
	
	
	public Swarms() {
	}
	
	public Swarms(String swarmID, String fileName) {
		this.swarmID = swarmID;
		this.fileName = fileName;
	}
	public String getSwarmID() {
		return swarmID;
	}
	public void setSwarmID(String swarmID) {
		this.swarmID = swarmID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
