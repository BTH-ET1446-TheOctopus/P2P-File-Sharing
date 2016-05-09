package backend.json;

import java.util.List;

/*
 * 
 * 
 * Work in progress
 */

public class Sync {
	
	private List <PeersInfo> peers;
	private List <String> bootstraps;
	private List <String> blacklist;
	private List <SwarmsInfo> swarmsInfo;
	
	public Sync() {
	}
	
	public Sync(List<PeersInfo> peers, List<String> bootstraps, List<String> blacklist, List<SwarmsInfo> swarmsInfo) {
		super();
		this.peers = peers;
		this.bootstraps = bootstraps;
		this.blacklist = blacklist;
		this.swarmsInfo = swarmsInfo;
	}
	
	public List<PeersInfo> getPeers() {
		return peers;
	}
	public List<String> getBootstraps() {
		return bootstraps;
	}

	public void setBootstraps(List<String> bootstraps) {
		this.bootstraps = bootstraps;
	}

	public List<String> getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(List<String> blacklist) {
		this.blacklist = blacklist;
	}

	public void setPeers(List<PeersInfo> peers) {
		this.peers = peers;
	}
	public List<SwarmsInfo> getSwarmsInfo() {
		return swarmsInfo;
	}
	public void setSwarmsInfo(List<SwarmsInfo> swarmsInfo) {
		this.swarmsInfo = swarmsInfo;
	}
	
	
}
