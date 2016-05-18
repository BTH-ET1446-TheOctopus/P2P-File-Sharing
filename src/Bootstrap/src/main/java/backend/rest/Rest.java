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
import backend.json.ID;
import backend.json.Peers;
import backend.json.PeersInfo;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;
import backend.json.SwarmsInfo;
import backend.json.Sync;
import backend.json.TestAddress;
import sql.DatabaseAPI;
import sql.DatabaseCalls;
import sql.sqlconnector;

import java.sql.*;
import java.util.UUID;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/rest")
public class Rest {
	private static final Logger LOG = Logger.getLogger(Rest.class.getName());
	private DatabaseAPI database = new DatabaseCalls();
	
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
	public ID getHello(@Context org.glassfish.grizzly.http.server.Request caller, @QueryParam("id") String id)
	{
		ID respons = new ID();
		/*
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			respons = null;
			return respons;
		}
		else
		{
		*/
			System.out.println(id);
			if(database.isPeerIDExisting(id))
			{
				respons.setid(id);
				database.updatePeer(caller.getRemoteAddr(), respons.getid(), Settings.getNTP());
				LOG.log(Level.INFO, "Peer update validy time and ip: " + id);
				
			}else
			{
				UUID uuid = UUID.randomUUID();
				respons.setid(uuid.toString());
				database.addPeers(respons.getid(), caller.getRemoteAddr(), false, Settings.getNTP());
				LOG.log(Level.INFO, "New peer connected id: " + respons.toString());
			}
			return respons;
		//}

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
		Peers peers = new Peers();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			peers = null;
			return peers;
		}
		else
		{
			//List<String> ip = new ArrayList<String>();
			peers=database.getpeers();
			return peers;
		}
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
		Bootstraps bootstraps = new Bootstraps();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			bootstraps = null;
			return bootstraps;
		}
		else
		{
			String readquery="";
	 		sqlconnector test = new sqlconnector();
	 		ResultSet result;
	 		String data="";
	 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
	 		readquery="select distinct ip from bootstrapserver";
	 		result = test.runquery(readquery);
	 		
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
		Blacklist blacklist = new Blacklist();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			blacklist = null;
			return blacklist;
		}
		else
		{	
			String readquery="";
	 		sqlconnector test = new sqlconnector();
	 		ResultSet result;
	 		String data="";
	 		test.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
	 		readquery="select distinct latestip from serverpeers where blacklist='1';";
	 		result = test.runquery(readquery);
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
	}
		
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
		SwarmsHelper swarmHelp = new SwarmsHelper();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			swarmHelp = null;
			return swarmHelp;
		}
		else
		{	
			//Swarms swarm = new Swarms();
			//List<Swarms> swarms = new ArrayList<Swarms>(); 
			swarmHelp=database.getSwarms();
	 		System.out.println(((Swarms)swarmHelp.getSwarms().get(0)).getfilename());
			return swarmHelp;
		}
	}
	/**
	 * The client wants to download a certain file and use the UUID as a id. This 
	 * gives the client access to the information needed to start download the file
	 * from different peers
	 * @param re used to access clients IP
	 * @param id the UUID of the file the client wants
	 * @return detailed information about a specific swarm
	 */
	
	@GET
	@Path("/swarms/{swarmID}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@Context org.glassfish.grizzly.http.server.Request caller, @PathParam("swarmID") String swarmID, @QueryParam("clientID") String clientID)
	{
		Swarm swarm = new Swarm();
		swarm = null;
		return swarm;
		
		/*
		Swarm swarm = new Swarm();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			swarm = null;
			return swarm;
		}
		else
		{	
			
			if(database.isSwarmIDExisting(swarmID))
			{
				if(database.isClientOnSwarm(swarmID, clientID) == false)
				{
					if(database.updateSwarm(swarmID, clientID))
					{
						// Success on update swarm
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
					else
					{
						LOG.log(Level.WARNING, "Could not update swarm " + swarmID + " whit: " + clientID);
					}
				}
				else
				{	
					//Client already is on swarm
					LOG.log(Level.INFO, "Client " + clientID + " already on swarm: " + swarmID);
				}
			} 
			//Return null if
			swarm = null;
			return swarm;
		}
		*/
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
	 * @param filename 
	 * @param fileChecksum the SHA sum used for file
	 * @param metadataChecksum The SHA sum used for the files header
	 * @return 
	 */
	@POST
    @Path("/swarms/")
    @Produces(MediaType.APPLICATION_JSON)
    public String addSwarmDB(@Context org.glassfish.grizzly.http.server.Request caller, @QueryParam("blockCount") int blockCount, @QueryParam("filename") String filename, @QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum, @QueryParam("clientID") String clientID) 
	{
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			String respons = null;
			return respons;
		}
		else
		{	
		
			if(blockCount <= 0)
			{
				return "respons";
			}
			
			//Generate UUID for the swarm so it can be downloade later
			UUID uuid = UUID.randomUUID();
			uuid.toString();
			
			return filename;
		}
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
		Sync sync = new Sync();
		if(database.isBlacklisted(caller.getRemoteAddr()))
		{
			sync = null;
			return sync;
		}
		else
		{	
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
}
