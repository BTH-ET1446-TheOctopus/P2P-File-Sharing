package json;

import java.util.List;

public class Peers {
	private List<String> peers;

	public Peers() {}
	
	public Peers(List<String> peers) {
		this.peers = peers;
	}

	public List<String> getpeers() {
		return peers;
	}

	public void setpeers(List<String> peers) {
		this.peers = peers;
	}
	
}
