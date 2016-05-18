package backend.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import backend.Settings;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Peers;
import backend.json.PeersInfo;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;
import backend.json.SwarmsInfo;
import backend.json.Sync;
import backend.json.TestAddress;
import sql.sqlconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.UUID;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/rest")
public class Rest {
	private static final Logger LOG = Logger.getLogger(Rest.class.getName());
	
	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public TestAddress convertCtoF(@Context org.glassfish.grizzly.http.server.Request re){
		System.out.println(re.getRemoteAddr());
		TestAddress adr = new TestAddress();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");
		
		//System.out.println(uriInfo.getBaseUri());
		return adr;
	}
	/**
	 * Gives the client an UUID if it has none and and update the 
	 * Valid time for the client.  
	 * 
	 * @param id The clients UUID if it has any 
	 * @return the client UUID
	 */
	@GET
	@Path("/hello/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHello(@Context org.glassfish.grizzly.http.server.Request caller, @QueryParam("id") String id)
	{
		
		caller.getRemoteAddr();
		UUID uuid = UUID.randomUUID();
		
		//After uuid checking generate timestamp from NTP server
        String timestamp = Settings.getNTP();
		
		return uuid.toString();
	}
	/**
	 * Used by the client to retrive data about the peers that the server is knowing of
	 * @return Peers, list of peers connected to bootstrap
	 */
	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public Peers getPeers(@Context org.glassfish.grizzly.http.server.Request caller)
	{
		//check if ip is blacklisted
		caller.getRemoteAddr();
		
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet result;
 		String data="";
 		int counter=0;
 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
 		readquery="select distinct peers from peersarray";
 		result = test.runquery(readquery);
		Peers peers = new Peers();
		List<String> ip = new ArrayList<String>();
		
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
	/**
	 * Used by the client to get all the bootstrap servers the bootstrap server is aware of. 
	 * This is in case somethings happen to our main bootstrap
	 * @return list of all bootstraps that the bootstrap knows of
	 */
	
	@GET
	@Path("/bootstraps/")
	@Produces(MediaType.APPLICATION_JSON)
	public Bootstraps getBootstraps(@Context org.glassfish.grizzly.http.server.Request caller)
	{
		//check if ip is blacklisted
		caller.getRemoteAddr();
		
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet result;
 		String data="";
 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
 		readquery="select distinct ip from bootstrapserver";
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
	/**
	 * Used by the client to get the blacklist from the bootstrap. 
	 * @return list of blacklisted peers
	 */
	
	@GET
	@Path("/blacklist/")
	@Produces(MediaType.APPLICATION_JSON)
	public Blacklist getBlacklist(@Context org.glassfish.grizzly.http.server.Request caller)
	{
		//check if ip is blacklisted
		caller.getRemoteAddr();
				
		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet result;
 		String data="";
 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
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
	
	/**
	 * Gives the client a list of all swarms that the bootstrap has. In order to get more informaiton
	 * about those swarms one need to use the UUID that is provided in the response
	 * @return list of swarms
	 */
	@GET
	@Path("/swarms/")
	@Produces(MediaType.APPLICATION_JSON)
	public SwarmsHelper getSwarms(@Context org.glassfish.grizzly.http.server.Request caller)
	{
		//check if ip is blacklisted
		caller.getRemoteAddr();
		
 		String readquery="";
 		sqlconnector test = new sqlconnector("serverdb");
 		ResultSet result;
 		String filename="";
 		String uniquefileid="";
 		//test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
 		readquery="select * from serverswarm";
 		result = test.runquery(readquery);
		SwarmsHelper swarmHelp = new SwarmsHelper();
		//Swarms swarm = new Swarms();
		List<Swarms> swarms = new ArrayList<Swarms>(); 
		try {
 			System.out.println();
 			while (result.next()) { 
 				Swarms swarm = new Swarms();
 			        System.out.println(result.getString("filename"));
 			        System.out.println(result.getString("uniquefileid"));
 			        swarm.setfilename(result.getString("filename"));
 	 				swarm.setid(result.getString("uniquefileid"));
 	 				swarms.add(swarm);
 			}
 	    }
 	    catch (Exception e) {
 	        System.out.println("Exception in query method:\n" + e.getMessage());
 	    }
 		test.closeconnect();
 		swarmHelp.setSwarms(swarms);
 		System.out.println(((Swarms)swarmHelp.getSwarms().get(0)).getfilename());
		return swarmHelp;
	}
	/**
	 * The client wants to downloade a certain file and use the UUID as a id. This 
	 * gives the client access to the information needed to start downloade the file
	 * from different peers
	 * @param re used to access clients ip
	 * @param id the UUID of the file the client wants
	 * @return detaild information about a specific swarm
	 */
	
	@GET
	@Path("/swarms/{swarmID}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@Context org.glassfish.grizzly.http.server.Request re, @PathParam("swarmID") String swarmID, @QueryParam("clientID") String clientID)
	{
		 
		
		//check if blacklisted
		String callerIP = re.getRemoteAddr();
		
		//Check if clientID already on the swarm 
		//if()
		//{
			//if not add it to swarm 
			//Suggest that you add it after the reading from the db
		//}
		
 		String readquery="";
 		sqlconnector test = new sqlconnector();
 		ResultSet result;
 		int blockcount=0;
 		String filename="";
	
 		String filechecksum="";
 		String metadatachecksum="";
 		String filepeers="";
 		String swarmid="'"+swarmID+"'";
 		
 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
 		readquery="select * from serverswarm where uniquefileid = "+ swarmid;
 		result = test.runquery(readquery);

		Swarm swarm = new Swarm();
		LOG.log(Level.INFO, swarmID);

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
 		readquery="select distinct peers from peersarray where uniquefileid =" + swarmid;
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
	
	/**
	 * Used by the client to push a new swarm to the bootstraps database to make it 
	 * public and may be used by otheres peers to access the file
	 * 
	 * @param re used to access pers ip
	 * @param blockCount number of blocks the file contains of
	 * @param filename what the name of the file is
	 * @param fileChecksum the SHA sum used for file
	 * @param metadataChecksum The SHA sum used for the files header
	 * @return 
	 */
	@POST
    @Path("/swarms/")
    @Produces(MediaType.APPLICATION_JSON)
    public String addSwarmDB(@Context org.glassfish.grizzly.http.server.Request re, @QueryParam("blockCount") int blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum, @QueryParam("clientID") String clientID) 
	{
		
		
		if(blockCount <= 0)
		{
			return "Can't have zero blocks";
		}
		
		
		String callerIP = re.getRemoteAddr();
		//Generate UUID for the swarm so it can be downloaded later
		UUID uuid = UUID.randomUUID();
		uuid.toString();
		
		System.out.println(callerIP);
		
		return filename;
    }
	
	/**
	 * Function used by bootstrap servers to sync whit each other. Could
	 * @return Bootstrap information for other servers
	 */
	@GET
	@Path("/sync/")
	@Produces(MediaType.APPLICATION_JSON)
	public Sync sync(@Context org.glassfish.grizzly.http.server.Request caller)
	{
		//check if blacklisted
		String callerIP = caller.getRemoteAddr();
		
		Sync sync = new Sync();
		
		// Create fake peer data
		PeersInfo peer = new PeersInfo();
		peer.setId("278f6d83-a707-4aee-8471-affc03c662a9");
		peer.setIp("129.160.10.2");
		peer.setLastSeen("07 MAY 2016 14:21:42 UTC");
		
		List<PeersInfo> peers = new ArrayList<PeersInfo>();
		peers.add(peer);
		
		//Create bootstrap data
		List<String> bootstraps = new ArrayList<String>();
		bootstraps.add("1.2.3.4");
		bootstraps.add("1.2.3.6");
		
		//Create blacklist data
		List<String> blacklist = new ArrayList<String>();
		blacklist.add("1.2.3.4");
		blacklist.add("1.2.3.6");
 		
 		
		
 		//SwarmsInfo 
 		List<SwarmsInfo> swarmInfoList = new ArrayList<SwarmsInfo>();
 	
 		SwarmsInfo swarmInfo = new SwarmsInfo();
 		swarmInfo.setBlockCount(4);
 		swarmInfo.setFileChecksum("XXXD");
 		swarmInfo.setfilename("Movie.mp3");
 		swarmInfo.setid("278f6d83-a707-4aee-8471-affc03c662a9");
 		swarmInfo.setMetadataChecksum("XYYYX");
 		

 		List<String> swarmPeers = new ArrayList<String>();
 		
 		swarmPeers.add("1.2.3.4");
 		swarmPeers.add("1.2.3.5");
 		
 		swarmInfo.setPeers(swarmPeers);
 		
 		swarmInfoList.add(swarmInfo);
 		
 		//add data to sync class
 		sync.setPeers(peers);
 		sync.setBootstraps(bootstraps);
		sync.setBlacklist(blacklist);
		sync.setSwarmsInfo(swarmInfoList);
		
		return sync;
	}
}
