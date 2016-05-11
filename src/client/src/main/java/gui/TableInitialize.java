package gui;

import java.util.ArrayList;
import java.util.List;

public class TableInitialize
{
	String	id;
	String	name;
	String	progress;
	String	size;
	String	speed;
	String	peers;
	String	due;
	String	dateAdded;

	public TableInitialize(String id, String name, String progress, String size, String speed, String peers,
			String due, String dateAdded)
	{
		this.id = id;
		this.name = name;
		this.progress = progress;
		this.size = size;
		this.speed = speed;
		this.peers = peers;
		this.due = due;
		this.dateAdded = dateAdded;
	}

	public String getPriority()
	{
		return id;
	}

	public void setPriority(String priority)
	{
		this.id = priority;
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
		return due;
	}

	public void setEtc(String etc)
	{
		this.due = etc;
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

		columnList.add(id);
		columnList.add(name);
		columnList.add(progress);
		columnList.add(size);
		columnList.add(speed);
		columnList.add(peers);
		columnList.add(due);
		columnList.add(dateAdded);

		return columnList;
	}

}
