package json;

import java.util.List;

public class PeersBootstrapBlacklist {
	String time;
	String id;
	List<IPS> ip;
	
	public PeersBootstrapBlacklist() {
	}
	
	public PeersBootstrapBlacklist(String time, String id, List<IPS> ip) {
		this.time = time;
		this.id = id;
		this.ip = ip;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<IPS> getIPS() {
		return ip;
	}
	public void setIPS(List<IPS> IPS) {
		ip = IPS;
	}
}
