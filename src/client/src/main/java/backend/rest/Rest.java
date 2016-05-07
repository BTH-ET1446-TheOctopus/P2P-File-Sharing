package backend.rest;

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

import backend.json.Address;
import backend.json.Chunk;
import backend.json.Chunks;
import backend.json.Peers;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/rest")
public class Rest {
	
	private static final Logger LOG = Logger.getLogger(Rest.class.getName());
	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public Address convertCtoF(){
		Address adr = new Address();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");
		return adr;
	}
	
	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public Peers getPeers()
	{
		Peers peers = new Peers();
		List<String> ip = new ArrayList<String>();
		ip.add("1.2.3.4");
		ip.add("1.2.3.6");
		
		peers.setpeers(ip);
		return peers;
	}
	
	@POST
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchFile(@PathParam("filename") String filename, @QueryParam("ip") String ip, @QueryParam("hopLimit") Integer hopLimit)
	{
		
		return filename;
	}
	
	
	@POST
	@Path("/searchresult/")
	@Produces(MediaType.APPLICATION_JSON)
	public void searchResult(@QueryParam("id") Integer id, @QueryParam("blockCount") Integer blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum)
	{
		
	}
	
	@GET
	@Path("/file/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Chunks getFileChunks(@PathParam("id") Integer id)
	{	
		Chunks chunks = new Chunks();
		LOG.log(Level.INFO, id.toString());
		if(id != 1){
			List<Integer> chunk = new ArrayList<Integer>();
			chunk.add(1);
			chunk.add(2);
			
			chunks.setchunks(chunk);
		}
		return chunks;
	}

	@GET
	@Path("/file/{id}/{chunk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Chunk getFile(@PathParam("id") Integer id, @PathParam("chunk") Integer chunk)
	{
		/*
		 * Here we should use id and chunk to detetermine what the respons should be
		 * 
		 */
		
		Chunk chunko = new Chunk();
		chunko.setSequenceNumber(0);
		chunko.setSize(20);
		
		byte[] data = new byte[20];
		Arrays.fill(data, (byte)'A');
		
		chunko.setData(data);
		chunko.setChecksum("XXXY");

		return chunko;
	}

}
