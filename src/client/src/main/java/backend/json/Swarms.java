package backend.json;

import java.util.List;

public class Swarms {
	private String id;
	private String filename;
	private List<String> peers;

	public Swarms() {
	}
	
	public Swarms(String swarmID, String fileName) {
		this.id = swarmID;
		this.filename = fileName;
	}
	public String getid() {
		return id;
	}
	public void setid(String swarmID) {
		this.id = swarmID;
	}
	public String getfilename() {
		return filename;
	}
	public void setfilename(String fileName) {
		this.filename = fileName;
	}
	public List<String> getPeers() {
		return peers;
	}
	public void setPeers(List<String> peers) {
		this.peers = peers;
	}
	@Override
	public String toString() {
		return "id="+id+", filename="+filename+", peers="+String.join(", ", peers);
	}
}
