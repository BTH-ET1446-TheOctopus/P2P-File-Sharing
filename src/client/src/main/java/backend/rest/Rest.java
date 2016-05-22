package backend.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.Backend;
import backend.api.datatypes.SwarmMetadata;
import backend.file.BlockBuffer;
import backend.file.FileHandler;
import backend.json.Address;
import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;
import sql.DatabaseAPI;
import sql.DatabaseCalls;

@Path("/rest")
public class Rest {
	DatabaseAPI database = new DatabaseCalls();
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public Address convertCtoF() {
		Address adr = new Address();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");
		return adr;
	}

	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public Peers getPeers() {
		Peers peers = new Peers();
		List<String> peersdb = database.getPeers();
		
 		peers.setpeers(peersdb);
		return peers;
	}

	@POST
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchFile(@QueryParam("filename") String filename, @QueryParam("ip") String ip,
			@QueryParam("hopLimit") Integer hopLimit) {
		
		System.out.print("Running search algorith");
		System.out.print(filename + ip+hopLimit);
		//(new ClientSearchThread(filename, ip, hopLimit)).start();
		DatabaseCalls databaseCalls = new DatabaseCalls();	
		ClientCalls clientCalls = new ClientCalls();
		//Check if the filename is in the database
		 SwarmMetadata swarm = databaseCalls.getSwarmByName(filename);		
		 System.out.print("Swarm:"+swarm);
		//if the file exist, get file information and send
		if(swarm != null)	{		
			System.out.print("/n"+"Swarm!=Null, about to do clientCall.searchResult");
			clientCalls.searchResult(ip, swarm.getId(), swarm.getBlockCount(), filename, swarm.getFileChecksum(), swarm.getMetadataChecksum());
			}
		//If the file doesn't exist, send search request to nearby Peers
		else {
			System.out.print("Sending searches to nearby peers");
			List<String> peers = new ArrayList<String>();	
			peers = databaseCalls.getconnPeers();
			hopLimit = hopLimit-1;
			for(int i=0; i<peers.size() && (peers.get(i)!=ip) &&hopLimit!=0;i++)	{
			clientCalls.search(peers.get(i),filename, ip, hopLimit);
			}							
		}
	
		return filename;
	}

	@POST
	@Path("/searchresult/")
	@Produces(MediaType.APPLICATION_JSON)
	public void searchResult(@QueryParam("clientIP") String clientIP, @QueryParam("id") String id, @QueryParam("blockCount") Integer blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum)
	{
		System.out.print("In searchresults");
		Backend.getInstance().searchResult(id, blockCount, filename, fileChecksum, metadataChecksum, clientIP);
	}

	@GET
	@Path("/file/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Chunks getFileChunks(@PathParam("id") String id) {
		LOG.log(Level.INFO, "Received request to get block availability file id {0}", new Object[] { id });
		
		Chunks chunks = new Chunks();
		List<Integer> blocks;
		
		String filename = database.getSwarmName(id);
		if (filename == null) {
			LOG.log(Level.WARNING, "No such file {0} in database", filename);
			return chunks;
		}
		
		BlockBuffer blockBuffer = FileHandler.read(filename);
		if (blockBuffer == null) {
			LOG.log(Level.WARNING, "Unable to open file {0} for reading", filename);
			return chunks;
		}
		
		try {
			blocks = new ArrayList<Integer>(blockBuffer.getBlockCount());
			
			for (int i = 0; i < blockBuffer.getBlockCount(); ++i) {
				blocks.add(i);
			}
		} catch (IOException e) {
			LOG.log(Level.WARNING, e.toString(), e);
			return chunks;
		}
		
		chunks.setchunks(blocks);
		
		return chunks;
	}

	@GET
	@Path("/file/{id}/{chunk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Chunk getFile(@PathParam("id") String id, @PathParam("chunk") Integer blockNumber) {
		LOG.log(Level.INFO, "Received request to download file id {0}, block {1}", new Object[] { id, blockNumber });
		
		Chunk chunk = new Chunk();
		String filename = database.getSwarmName(id);
		if (filename == null) {
			LOG.log(Level.WARNING, "No such file {0} in database", filename);
			return chunk;
		}
		
		BlockBuffer blockBuffer = FileHandler.read(filename);
		if (blockBuffer == null) {
			LOG.log(Level.WARNING, "Unable to open file {0} for reading", filename);
			return chunk;
		}
		
		try {
			if (blockNumber >= blockBuffer.getBlockCount()) {
				LOG.log(Level.WARNING, "Block number out of bounds {0}", filename);
			}
		} catch (IOException e) {
			LOG.log(Level.WARNING, e.toString(), e);
			return chunk;
		}
		
		chunk.setSequenceNumber(blockNumber);
		
		byte[] data = new byte[BlockBuffer.BLOCK_SIZE];
		int readCount = 0;
		try {
			readCount = blockBuffer.getBlock(data, blockNumber);
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Unable to read block {0} in file {1}", new Object[] { chunk, filename });
			return chunk;
		} catch (IndexOutOfBoundsException e) {
			LOG.log(Level.WARNING, "Unable to read block {0} in file {1}", new Object[] { chunk, filename });
			return chunk;
		}

		chunk.setData(data);
		chunk.setSize(readCount);

		chunk.setChecksum("Wololosum");

		return chunk;
	}

}
