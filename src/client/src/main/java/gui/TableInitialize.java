package gui;

import java.util.ArrayList;
import java.util.List;

public class TableInitialize
{
	String	priority;
	String	name;
	String	progress;
	String	size;
	String	speed;
	String	peers;
	String	etc;
	String	dateAdded;

	public TableInitialize(String priority, String name, String progress, String size, String speed, String peers,
			String etc, String dateAdded)
	{
		this.priority = priority;
		this.name = name;
		this.progress = progress;
		this.size = size;
		this.speed = speed;
		this.peers = peers;
		this.etc = etc;
		this.dateAdded = dateAdded;
	}

	public String getPriority()
	{
		return priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProgress()
	{
		return progress;
	}

	public void setProgress(String progress)
	{
		this.progress = progress;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getSpeed()
	{
		return speed;
	}

	public void setSpeed(String speed)
	{
		this.speed = speed;
	}

	public String getPeers()
	{
		return peers;
	}

	public void setPeers(String peers)
	{
		this.peers = peers;
	}

	public String getEtc()
	{
		return etc;
	}

	public void setEtc(String etc)
	{
		this.etc = etc;
	}

	public String getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(String dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public List<String> rowCreation()
	{
		List<String> columnList = new ArrayList<>();

		columnList.add(priority);
		columnList.add(name);
		columnList.add(progress);
		columnList.add(size);
		columnList.add(speed);
		columnList.add(peers);
		columnList.add(etc);
		columnList.add(dateAdded);

		return columnList;
	}

}
