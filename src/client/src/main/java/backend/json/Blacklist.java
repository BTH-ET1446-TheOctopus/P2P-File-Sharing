package backend.json;

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
	
	@Override
	public String toString() 
	{
		String stringBuilder = "blacklist: ";
		for(int i = 0; i < blacklist.size(); i++)
		{
			stringBuilder += blacklist.get(i) + "\n";	
		}
		
		return stringBuilder;
		
	}
}
