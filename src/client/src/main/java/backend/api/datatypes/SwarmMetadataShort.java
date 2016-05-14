package backend.api.datatypes;

public class SwarmMetadataShort {
	private String id;
	private String filename;
	private Double progress;
	private Double size;
	private Double speed;
	private int peers;
	private String due;
	private String added;

	
	public SwarmMetadataShort(String id, String filename, Double progress, Double size, Double speed, int peers, String due, String added) {
		this.id = id;
		this.filename = filename;
		this.setProgress(progress);
		this.size = size;
		this.speed = speed;
		this.peers = peers;
		this.due = due;
		this.added = added;
	}

	public String getId() {
		return id;
	}

	public Double getSize()
	{
		return size;
	}

	public void setSize(Double size)
	{
		this.size = size;
	}

	public Double getSpeed()
	{
		return speed;
	}

	public void setSpeed(Double speed)
	{
		this.speed = speed;
	}

	public int getPeers()
	{
		return peers;
	}

	public void setPeers(int peers)
	{
		this.peers = peers;
	}

	public String getDue()
	{
		return due;
	}

	public void setDue(String due)
	{
		this.due = due;
	}

	public String getAdded()
	{
		return added;
	}

	public void setAdded(String added)
	{
		this.added = added;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public Double getProgress()
	{
		return progress;
	}

	public void setProgress(Double progress)
	{
		this.progress = progress;
	}
}
