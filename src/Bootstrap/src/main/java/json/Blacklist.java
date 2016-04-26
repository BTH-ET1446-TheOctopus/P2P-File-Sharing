package json;

import java.util.List;

public class Blacklist {
	private List<String> blacklist;

	public Blacklist() {}
	
	public Blacklist(List<String> blacklist) {
		this.blacklist = blacklist;
	}

	public List<String> getblacklist() {
		return blacklist;
	}

	public void setblacklist(List<String> blacklist) {
		this.blacklist = blacklist;
	}
}
