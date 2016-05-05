package backend.json;

import java.util.List;

public class SwarmsHelper {
	private List<Swarms> swarms;

	public SwarmsHelper() {
	}
	public SwarmsHelper(List<Swarms> swarms) {
		super();
		this.swarms = swarms;
	}

	public List<Swarms> getSwarms() {
		return swarms;
	}

	public void setSwarms(List<Swarms> swarms) {
		this.swarms = swarms;
	}
	
	@Override
	public String toString() 
	{
		String stringBuilder = "swarms: ";
		for(int i = 0; i < swarms.size(); i++)
		{
			stringBuilder += swarms.get(i) + "\n";	
		}
		
		return stringBuilder;
		
	}
	
}
