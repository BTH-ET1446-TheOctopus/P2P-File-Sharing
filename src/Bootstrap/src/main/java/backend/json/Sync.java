package backend.json;

import java.util.List;

/*
 * 
 * 
 * Work in progress
 */

public class Sync {
	
	List <PeersInfo> peers;
	Bootstraps bootstraps;
	Blacklist blacklist;
	//List <String> bootstraps;
	//List <String> blacklist;
	List <SwarmsInfo> swarmsInfo;
	
	public Sync() {
	}
	
	public Sync(List<PeersInfo> peers, Bootstraps bootstraps, Blacklist blacklist, List<SwarmsInfo> swarmsInfo) {
		super();
		this.peers = peers;
		this.bootstraps = bootstraps;
		this.blacklist = blacklist;
		this.swarmsInfo = swarmsInfo;
	}
	/*
	public Sync(List<PeersInfo> peers, List<String> bootstraps, List<String> blacklist, List<SwarmsInfo> swarmsInfo) {
		super();
		this.peers = peers;
		this.bootstraps = bootstraps;
		this.blacklist = blacklist;
		this.swarmsInfo = swarmsInfo;
	}
	*/
	/*
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
*/
	public void setPeers(List<PeersInfo> peers) {
		this.peers = peers;
	}
	
	
	
	public Bootstraps getBootstraps() {
		return bootstraps;
	}
	public void setBootstraps(Bootstraps bootstraps) {
		this.bootstraps = bootstraps;
	}
	public Blacklist getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(Blacklist blacklist) {
		this.blacklist = blacklist;
	}
	
	public List<SwarmsInfo> getSwarmsInfo() {
		return swarmsInfo;
	}
	public void setSwarmsInfo(List<SwarmsInfo> swarmsInfo) {
		this.swarmsInfo = swarmsInfo;
	}
	
	
	
}
