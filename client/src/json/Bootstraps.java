package json;

import java.util.List;

public class Bootstraps {
	private List<String> bootstraps;

	public Bootstraps() {}
	
	public Bootstraps(List<String> bootstraps) 
	{
		this.bootstraps = bootstraps;
	}

	public List<String> getbootstraps() 
	{
		return bootstraps;
	}

	public void setbootstraps(List<String> bootstraps) 
	{
		this.bootstraps = bootstraps;
	}
	@Override
	public String toString() 
	{
		String stringBuilder = "bootstraps: ";
		for(int i = 0; i < bootstraps.size(); i++)
		{
			stringBuilder += bootstraps.get(i) + "\n";	
		}
		
		return stringBuilder;
		
	}
}
