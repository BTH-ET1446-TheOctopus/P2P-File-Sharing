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
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select distinct peers from peersarray";
		result = test.runquery(readquery);	
		Peers peers = new Peers();
		List<String> ip = new ArrayList<String>();
		
		try {
			System.out.println();
			while(result.next()){
		         //Retrieve by column name			
		         data = result.getString("peers");	         
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
		String readquery="";
		sqlconnector test = new sqlconnector();
		ResultSet result;
		String data="";
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select distinct ip from servers";
		result = test.runquery(readquery);
		
		Bootstraps bootstraps = new Bootstraps();
		List<String> ip = new ArrayList<String>();
		try {
			System.out.println();
			while(result.next()){
		         //Retrieve by column name			
		         data = result.getString("ip");	         
		         ip.add(data);
		      }
	    }
	    catch (Exception e) {
	        System.out.println("Exception in query method:\n" + e.getMessage());
	    }
		test.closeconnect();
		bootstraps.setbootstraps(ip);
		
		return bootstraps;
	}
	
	@GET
	@Path("/blacklist/")
	@Produces(MediaType.APPLICATION_JSON)
	public Blacklist getBlacklist()
	{
		String readquery="";
		sqlconnector test = new sqlconnector();
		ResultSet result;
		String data="";
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select distinct latestip from serverpeers where blacklist='1';";
		result = test.runquery(readquery);

		Blacklist blacklist = new Blacklist();
		List<String> ip = new ArrayList<String>();
		try {
			System.out.println();
			while(result.next()){
		         //Retrieve by column name			
		         data = result.getString("latestip");	         
		         ip.add(data);
		      }
	    }
	    catch (Exception e) {
	        System.out.println("Exception in query method:\n" + e.getMessage());
	    }
		test.closeconnect();
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
		String readquery="";
		sqlconnector test = new sqlconnector();
		ResultSet result;
		String filename="";
		String uniquefileid="";
		
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select * from serverfile";
		result = test.runquery(readquery);
		
		SwarmsHelper swarmHelp = new SwarmsHelper();
		Swarms swarm = new Swarms();
		List<Swarms> swarms = new ArrayList<Swarms>(); 
		
		try {
			System.out.println();
			while(result.next()){
		        //Retrieve by column name			
		        filename = result.getString("filename");
		        uniquefileid = result.getString("uniquefileid");
		        swarm.setfilename(filename);
				swarm.setid(uniquefileid);
				swarms.add(swarm);
		    }
	    }
	    catch (Exception e) {
	        System.out.println("Exception in query method:\n" + e.getMessage());
	    }
		test.closeconnect();
		
		swarmHelp.setSwarms(swarms);
		
		
		return swarmHelp;
	}
	
	@GET
	@Path("/swarms/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@PathParam("id") int id)
	{
		String readquery="";
		sqlconnector test = new sqlconnector();
		ResultSet result;
		int blockcount=0;
		String filename="";
		String filechecksum="";
		String metadatachecksum="";
		String filepeers="";
		String sid="";
		
		test.connector("root", "farhan", "serverdb", "127.0.0.1", "3306");
		readquery="select * from serverfile where uniquefileid = "+ "'abc123'";
		result = test.runquery(readquery);
		
		Swarm swarm = new Swarm();
		System.out.println(id);
		List<String> peers = new ArrayList<String>();
		try {
			System.out.println();
			while(result.next()){
		        //Retrieve by column name			
		        filename = result.getString("filename");	         
		        blockcount= result.getInt("totalblocks");
		     	filechecksum=result.getString("filechecksum");
		     	metadatachecksum=result.getString("metadatachecksum");
	         	swarm.setBlockCount(blockcount);
				swarm.setFilename(filename);
				swarm.setFileChecksum(filechecksum);
				swarm.setMetadataChecksum(metadatachecksum);
		    }
	    }
	    catch (Exception e) {
	        System.out.println("Exception in query method:\n" + e.getMessage());
	    }
		readquery="select distinct peers from peersarray where uniquefileid =" + "'abc123'";
		result = test.runquery(readquery);
		try {
			System.out.println();
			while(result.next()){
		        //Retrieve by column name			
		        filepeers = result.getString("peers");	         
		        peers.add(filepeers);
		    }
	    }
	    catch (Exception e) {
	        System.out.println("Exception in query method:\n" + e.getMessage());
	    }
		test.closeconnect();	
		swarm.setPeers(peers);

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
