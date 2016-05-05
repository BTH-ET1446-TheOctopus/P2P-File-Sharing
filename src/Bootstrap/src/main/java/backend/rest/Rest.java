package backend.rest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Peers;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;
import backend.json.Sync;
import backend.json.TestAddress;
import sql.sqlconnector;
import java.sql.*;
import java.util.Properties;

@Path("/rest")
public class Rest {
	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public TestAddress convertCtoF(){
		TestAddress adr = new TestAddress();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");
		return adr;
	}
	@GET
	@Path("/hello/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello(@QueryParam("id") String ide)
	{
		String id = ide;
		return id;
		
	}
	
	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public Peers getPeers()
	{
		String readquery="";
		sqlconnector test = new sqlconnector();
		ResultSet result;
		int counter=0;
		String data="";
		System.out.println("get 2-3 random peers");
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select distinct peers from peersarray";
		result = test.runquery(readquery);
		//test.printresult(result);		
		Peers peers = new Peers();
		List<String> ip = new ArrayList<String>();
		
		try {
			System.out.println();
			while(result.next()){
		         //Retrieve by column name			
		         //uniquefileid = rs.getString("uniquefileid");
		         data = result.getString("peers");	         
		         //Display values 
		         //System.out.printf("uniquefileid: %s ", uniquefileid);
		         //System.out.printf("peers: %s ", peers);
		         //System.out.println();
		         if(counter<3)
		         {
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
	
	@GET
	@Path("/bootstraps/")
	@Produces(MediaType.APPLICATION_JSON)
	public Bootstraps getBootstraps()
	{
	
		Bootstraps bootstraps = new Bootstraps();
		List<String> ip = new ArrayList<String>();
		ip.add("1.2.3.4");
		ip.add("1.2.3.6");
		bootstraps.setbootstraps(ip);
		
		return bootstraps;
	}
	
	@GET
	@Path("/blacklist/")
	@Produces(MediaType.APPLICATION_JSON)
	public Blacklist getBlacklist()
	{
		Blacklist blacklist = new Blacklist();
		List<String> ip = new ArrayList<String>();
		ip.add("1.2.3.4");
		ip.add("1.2.3.6");
		blacklist.setblacklist(ip);
		
		return blacklist;
	}
	
	
	/*
	 * Should be an array that is called swarms
	 */
	@GET
	@Path("/swarms/")
	@Produces(MediaType.APPLICATION_JSON)
	public SwarmsHelper getSwarms()
	{
		SwarmsHelper swarmHelp = new SwarmsHelper();
		Swarms swarm = new Swarms();
		List<Swarms> swarms = new ArrayList<Swarms>(); 
		swarm.setfilename("ninda.exe");
		swarm.setid("test");
		
		swarms.add(swarm);
		swarm.setfilename("virus.exe");
		swarm.setid("DDDDEEEFFF");
		swarms.add(swarm);
		swarmHelp.setSwarms(swarms);
		
		
		return swarmHelp;
	}
	
	@GET
	@Path("/swarms/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@PathParam("id") int id)
	{

		Swarm swarm = new Swarm();
		System.out.println(id);

		if(id == 1)
		{
			swarm.setBlockCount(2);
			swarm.setFilename("virus.exe");
			swarm.setFileChecksum("XXXYYY");
			swarm.setMetadataChecksum("XXXYYY");
			
			List<String> peers = new ArrayList<String>();
			peers.add("1.2.3.4");
			peers.add("1.2.3.5");
			
			swarm.setPeers(peers);
		}
		return swarm;
	}
	
	
	/*
	 * In progress
	 * 
	 * 
	 */
	@POST
    @Path("/swarms/")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPlainText(@QueryParam("blockCount") int blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum) 
	{
        
		
		return filename;
    }
	/*
	 * Work in progress
	 * 
	 * 
	@GET
	@Path("/sync/")
	@Produces(MediaType.APPLICATION_JSON)
	public Sync sync()
	{
		
		return sync;
	}
    */
}
