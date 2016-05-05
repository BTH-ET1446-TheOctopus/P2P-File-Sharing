package backend.json;

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
	
	@Override
	public String toString() 
	{
		String stringBuilder = "peers: ";
		for(int i = 0; i < peers.size(); i++)
		{
			stringBuilder += peers.get(i) + "\n";	
		}
		
		return stringBuilder;
		
	}
	
}
