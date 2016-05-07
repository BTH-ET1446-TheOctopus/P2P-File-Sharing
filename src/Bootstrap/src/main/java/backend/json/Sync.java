package backend.json;

import java.util.List;

/*
 * 
 * 
 * Work in progress
 */

public class Sync {
	
	List <PeersInfo> peers;
	List <Bootstraps> bootstraps;
	List <Blacklist> blacklist;
	List <SwarmsInfo> swarms;
	
	public Sync() {
	}
	public Sync(List<PeersInfo> peers, List<Bootstraps> bootstraps, List<Blacklist> blacklist, List<SwarmsInfo> swarms) {
		super();
		this.peers = peers;
		this.bootstraps = bootstraps;
		this.blacklist = blacklist;
		this.swarms = swarms;
	}
	public List<PeersInfo> getPeers() {
		return peers;
	}
	public void setPeers(List<PeersInfo> peers) {
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
	public List<SwarmsInfo> getSwarms() {
		return swarms;
	}
	public void setSwarms(List<SwarmsInfo> swarms) {
		this.swarms = swarms;
	}
	
	
	
}
