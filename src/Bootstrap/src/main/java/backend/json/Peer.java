package backend.json;

public class Peer {
	String id;
	String ip;
	String lastSeen;
	
	public Peer() {
		super();
	}
	public Peer(String id, String ip, String lastSeen) {
		super();
		this.id = id;
		this.ip = ip;
		this.lastSeen = lastSeen;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	
}
