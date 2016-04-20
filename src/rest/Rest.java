package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import json.Address;
import json.Block;
import json.PeersBootstrapBlacklist;
import json.Swarm;
import json.Swarms;
import json.IPS;

@Path("/rest")
public class Rest {
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
	
	/*
	 * The thing that differ between peers,servers and blacklist
	 * is the ID, that is either Blacklist, Servers or Peers
	 * 
	 */
	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public PeersBootstrapBlacklist getPeers()
	{
		PeersBootstrapBlacklist peers = new PeersBootstrapBlacklist();
		
		//Creating static example
		peers.setId("Peers");
		peers.setTime("1461151586");
		List<IPS> ips = new ArrayList<IPS>();
		IPS ip = new IPS();
		ip.setIP("192.168.30.2");
		ips.add(ip);
		ip.setIP("192.168.30.3");
		ips.add(ip);
		peers.setIPS(ips);
		
		
		return peers;
	}
	
	@GET
	@Path("/bootstrapServers/")
	@Produces(MediaType.APPLICATION_JSON)
	public PeersBootstrapBlacklist getBootstrapServers()
	{
		PeersBootstrapBlacklist peers = new PeersBootstrapBlacklist();
		
		//Creating static example
		peers.setId("Servers");
		peers.setTime("1461151586");
		List<IPS> ips = new ArrayList<IPS>();
		IPS ip = new IPS();
		ip.setIP("192.168.31.4");
		ips.add(ip);
		ip.setIP("192.168.31.5");
		ips.add(ip);
		peers.setIPS(ips);
		
		
		return peers;
	}
	
	@GET
	@Path("/blacklist/")
	@Produces(MediaType.APPLICATION_JSON)
	public PeersBootstrapBlacklist getBlacklist()
	{
		PeersBootstrapBlacklist peers = new PeersBootstrapBlacklist();
		
		//Creating static example
		peers.setId("Blacklist");
		peers.setTime("1461151586");
		List<IPS> ips = new ArrayList<IPS>();
		IPS ip = new IPS();
		ip.setIP("192.168.31.6");
		ips.add(ip);
		ip.setIP("192.168.31.7");
		ips.add(ip);
		peers.setIPS(ips);
		
		return peers;
	}
	
	@GET
	@Path("/swarmList/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Swarms> getSwarmList()
	{
		Swarms swarm = new Swarms();
		List<Swarms> swarms = new ArrayList<Swarms>(); 
		swarm.setFileName("ninda.exe");
		swarm.setSwarmID("test");
		
		swarms.add(swarm);
		swarm.setFileName("virus.exe");
		swarm.setSwarmID("DDDDEEEFFF");
		swarms.add(swarm);
		
		
		return swarms;
	}
	
	@GET
	@Path("/swarm/{swarmID}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@PathParam("swarmID") String swarmID)
	{

		Swarm swarm = new Swarm();
		System.out.println(swarmID);

		if(swarmID.equals("test"))
		{
			Block block = new Block();
			block.setBlockNr(0);
			block.setBlockSize(1024);
			block.setChecksum("XXXYYY");
			List<Block> blocks = new ArrayList<Block>();
			
			blocks.add(block);
			block.setBlockNr(1);
			block.setBlockSize(123);
			block.setChecksum("XXXYYY");
			blocks.add(block);
			
			swarm.setNumOfBlocks(2);
			swarm.setFilename("virus.exe");
			swarm.setFileCheckSum("XXXYYY");
			swarm.setBlocks(blocks);
		}
		
		return swarm;
	}
	
	
	
	/*
	@POST
    @Path("/createSwarm/{a}/{b}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPlainText(@PathParam("a") double a, @PathParam("b") double b) 
	{
        return (a + b) + "";
    }
    */
}
