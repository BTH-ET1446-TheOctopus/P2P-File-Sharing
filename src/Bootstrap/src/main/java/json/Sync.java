package json;

import java.util.List;

/*
 * 
 * 
 * Work in progress
 */

public class Sync {
	List <Peer> peers;
	List <Bootstraps> bootstraps;
	List <Blacklist> blacklist;
	List <SwarmId> swarms;
	
	public Sync() {
	}
	public Sync(List<Peer> peers, List<Bootstraps> bootstraps, List<Blacklist> blacklist, List<SwarmId> swarms) {
		super();
		this.peers = peers;
		this.bootstraps = bootstraps;
		this.blacklist = blacklist;
		this.swarms = swarms;
	}
	public List<Peer> getPeers() {
		return peers;
	}
	public void setPeers(List<Peer> peers) {
		this.peers = peers;
	}
	public List<Bootstraps> getBootstraps() {
		return bootstraps;
	}
	public void setBootstraps(List<Bootstraps> bootstraps) {
		this.bootstraps = bootstraps;
	}
	public List<Blacklist> getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(List<Blacklist> blacklist) {
		this.blacklist = blacklist;
	}
	public List<SwarmId> getSwarms() {
		return swarms;
	}
	public void setSwarms(List<SwarmId> swarms) {
		this.swarms = swarms;
	}
	
	
	
}
