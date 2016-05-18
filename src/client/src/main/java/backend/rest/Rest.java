package backend.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.file.BlockBuffer;
import backend.file.FileHandler;
import backend.json.Address;
import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;
import sql.sqlconnector;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/rest")
public class Rest {

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
		List<String> ip = new ArrayList<String>();
		String readquery="";
 		sqlconnector test = new sqlconnector("clientdb");
 		ResultSet result;
 		String data="";
 		int counter=0;
 		readquery="select distinct peers from peersarray";
 		result = test.runquery(readquery);
		
		try {
 			System.out.println();
 			while(result.next()){
 		         //Retrieve by column name			
 		         data = result.getString("peers");	         
 		         if (counter<3){
 		        	 ip.add(data);
 		         }
 		         counter++;
 		      }
 	    }
 	    catch (Exception e) {
 	        System.out.println("Exception in query method:\n" + e.getMessage());
 	    }
 		test.closeconnect();
 		
 		peers.setpeers(ip);
		return peers;
	}

	@POST
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchFile(@PathParam("filename") String filename, @QueryParam("ip") String ip,
			@QueryParam("hopLimit") Integer hopLimit) {

		return filename;
	}

	@POST
	@Path("/searchresult/")
	@Produces(MediaType.APPLICATION_JSON)
	public void searchResult(@QueryParam("id") String id, @QueryParam("blockCount") Integer blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum)
	{
		

	}

	@GET
	@Path("/file/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Chunks getFileChunks(@PathParam("id") String id) {
		Chunks chunks = new Chunks();
		LOG.log(Level.INFO, id.toString());
		if (id.equals("abc123")) {
			List<Integer> chunk = Arrays.asList(0, 1, 2, 3);

			chunks.setchunks(chunk);
		}
		return chunks;
	}

	@GET
	@Path("/file/{id}/{chunk}")
	@Produces(MediaType.APPLICATION_JSON)

	public Chunk getFile(@PathParam("id") String id, @PathParam("chunk") Integer chunk) {
		LOG.log(Level.INFO, "Received request to download file id {0}, block {1}", new Object[] { id, chunk });

		// Database code:
		// Get the filename from 'id'
 		sqlconnector test = new sqlconnector("clientdb");
 		ResultSet result;
 		String fname="";
 		String readquery="select distinct filename from clientswarm where filename='id'";
 		result = test.runquery(readquery);
		//Retrieve by column name
 		try {
 			fname = result.getString("filename");
 	    }
 	    catch (Exception e) {
 	        LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
 	    }
			         
 		test.closeconnect();
		// Check if the block number 'chunk' is downloaded
 		
		Chunk chunko = new Chunk();
		chunko.setSequenceNumber(chunk);

		if (id.equals("abc123")) {
			String filename = "octopus.jpg";

			BlockBuffer blockBuffer = FileHandler.read(filename);
			if (blockBuffer == null) {
				LOG.log(Level.WARNING, "Unable to open file {0} for reading", filename);
				return chunko;
			}

			byte[] data = new byte[BlockBuffer.BLOCK_SIZE];
			int readCount = 0;
			try {
				readCount = blockBuffer.getBlock(data, chunk);
			} catch (IOException e) {
				LOG.log(Level.WARNING, "Unable to read block {0} in file {1}", new Object[] { chunk, filename });
				return chunko;
			} catch (IndexOutOfBoundsException e) {
				LOG.log(Level.WARNING, "Unable to read block {0} in file {1}", new Object[] { chunk, filename });
				return chunko;
			}

			chunko.setData(data);
			chunko.setSize(readCount);

			chunko.setChecksum("Wololosum");
		}

		return chunko;
	}

}
