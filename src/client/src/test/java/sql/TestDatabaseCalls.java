package sql;

import static org.junit.Assert.*;

import backend.api.datatypes.SwarmMetadata;

public class TestDatabaseCalls {
	DatabaseAPI database = new DatabaseCalls();

	
	@org.junit.Test
	public void isSwarmExisting() {
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
		assertEquals(true, database.isSwarmExisting(swarmID1));
		assertEquals(false, database.isSwarmExisting("XQE"));
		
		//Clean up
		database.deleteSwarmID("06806786-1dd1-11e6-b6ba-3e1d05defe78");
	}

	@org.junit.Test
	public void deleteSwarmID(){
		//prepare
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
			
		//Execute
		assertEquals(true, database.deleteSwarmID(swarmID1));
		assertEquals(false, database.deleteSwarmID(swarmID1));
			
	}
	
	@org.junit.Test
	public void addSwarm()
	{
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		assertEquals(true, database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1));
		assertEquals(false, database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1));
		database.deleteSwarmID(swarmID1);
	}
	
	
	@org.junit.Test
	public void addPeers()
	{
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";

		String ip = "190.160.12.1";
		String fakeSwarm = "XD%¤%¤%";
		
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
		
		assertEquals(true, database.addPeers(swarmID1, ip));
		assertEquals(false, database.addPeers(fakeSwarm, ip));
	}
	
	@org.junit.Test
	public void getSwarmByName()
	{
		//Prepare
		SwarmMetadata swarm = new SwarmMetadata();
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
		swarm = database.getSwarmByName(filename);
		//Execute
		SwarmMetadata swarm2 = database.getSwarmByName("Pretty woman");
	
		assertEquals(filename, swarm.getFilename());
		assertNull(swarm2);
	}
	
	@org.junit.Test
	public void getSwarmName()
	{
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
		
		assertEquals(filename, database.getSwarmName(swarmID1));
		assertNull(database.getSwarmName("LULLLLLL"));
	}
	/*
	@org.junit.Test
	public void checkChunkAvaible(){
		SwarmMetadata swarm = new SwarmMetadata();
		String uuidClient1 = "fdf27a64-1dd0-11e6-b6ba-3e1d05defe78";
		String swarmID1 = "06806786-1dd1-11e6-b6ba-3e1d05defe78";
		String filename = "Frozen";
		String fileChecksum1 ="";
		int blockCount = 10;
		String metadataChecksum1 = "";
		
		database.addSwarm(swarmID1, filename, fileChecksum1, metadataChecksum1, blockCount, uuidClient1);
		
		database.checkChunkAvaible(id, chunkNr);
		
	}
	*/
}
