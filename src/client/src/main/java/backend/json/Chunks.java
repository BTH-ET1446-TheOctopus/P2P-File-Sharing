package backend.json;

import java.util.List;

public class Chunks {
	private List<Integer> chunks;

	public Chunks() {}
	
	public Chunks(List<Integer> chunks) {
		this.chunks = chunks;
	}

	public List<Integer> getchunks() {
		return chunks;
	}

	public void setchunks(List<Integer> chunks) {
		this.chunks = chunks;
	}
	
	@Override
	public String toString() 
	{
		String stringBuilder = "chunks: ";
		for(int i = 0; i < chunks.size(); i++)
		{
			stringBuilder += chunks.get(i) + "\n";	
		}
		
		return stringBuilder;
		
	}
}
