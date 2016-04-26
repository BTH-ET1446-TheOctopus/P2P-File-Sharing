package json;

public class Swarms {
	private String id;
	private String filename;
	
	
	public Swarms() {
	}
	
	public Swarms(String swarmID, String fileName) {
		this.id = swarmID;
		this.filename = fileName;
	}
	public String getid() {
		return id;
	}
	public void setid(String swarmID) {
		this.id = swarmID;
	}
	public String getfilename() {
		return filename;
	}
	public void setfilename(String fileName) {
		this.filename = fileName;
	}
	
	@Override
	public String toString() 
	{
		return "id: " + this.id + "\n" + "filename" + this.filename;
		
	}
	
}
