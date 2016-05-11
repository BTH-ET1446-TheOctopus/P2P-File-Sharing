package gui;

import java.util.List;

import backend.api.BackendObserver;

public class Calls implements BackendObserver
{

	@Override
	public void newSwarm(String id, String filename, int blockCount, String dateAdded)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSwarmBlock(String id, int blockNumber, boolean correctChecksum)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSwarm(double progress, double speed, List<String> peers, String timeToCompletion)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchResult(String clientAddress, String id, String filename, int blockCount)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newSwarm(String id, String filename, int blockCount)
	{
		// TODO Auto-generated method stub
		
	}



}
