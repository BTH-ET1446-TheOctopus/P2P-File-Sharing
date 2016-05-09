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
import java.util.Properties;
import java.util.UUID;

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
	public String getHello(@QueryParam("id") String id)
	{
		
		
		//if id is -1 or it don't exist in db give it a new one save it to db
		UUID uuid = UUID.randomUUID();
		
		//After uuid checking generate timestamp from NTP server
		Socket so = null;
		try {
			so = new Socket(Settings.ntpServer, Settings.daytimeport);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader (so.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String timestamp = null;
		try {
			timestamp = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(timestamp);
		
		return uuid.toString();
		
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
	public Swarm getSwarm(@PathParam("id") String id)
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
		//Generate UUID for the swarm so it can be downloaded later
		UUID uuid = UUID.randomUUID();
		uuid.toString();
		
		return filename;
    }
	
	/**
	 * Function used by bootstrap servers to sync whit each other. Could
	 * @return Bootstrap information for other servers
	 */
	@GET
	@Path("/sync/")
	@Produces(MediaType.APPLICATION_JSON)
	public Sync sync()
	{
		Sync sync = new Sync();
		
		// Create fake peer data
		PeersInfo peer = new PeersInfo();
		peer.setId("278f6d83-a707-4aee-8471-affc03c662a9");
		peer.setIp("129.160.10.2");
		peer.setLastSeen("07 MAY 2016 14:21:42 UTC");
		
		List<PeersInfo> peers = new ArrayList<PeersInfo>();
		peers.add(peer);
		
		//Create bootstrap data
		
		Bootstraps bootstraps = new Bootstraps();
		List<String> bootstrap = new ArrayList<String>();
		bootstrap.add("1.2.3.4");
		bootstrap.add("1.2.3.6");
		bootstraps.setbootstraps(bootstrap);
		
		
		//Create blacklist data
		
		Blacklist blacklist = new Blacklist();
		List<String> ip = new ArrayList<String>();
 		ip.add("1.2.3.4");
 		ip.add("1.2.3.6");
 		blacklist.setblacklist(ip);
 		
		
 		//SwarmsInfo 
 		List<SwarmsInfo> swarmInfoList = new ArrayList<SwarmsInfo>();
 	
 		SwarmsInfo swarmInfo = new SwarmsInfo();
 		swarmInfo.setBlockCount(4);
 		swarmInfo.setFileChecksum("XXXD");
 		swarmInfo.setfilename("Movie.mp3");
 		swarmInfo.setid("278f6d83-a707-4aee-8471-affc03c662a9");
 		swarmInfo.setMetadataChecksum("XYYYX");
 		
 		Peers swarmPeers = new Peers();
 		List<String> swarmPeer = new ArrayList<String>();
 		
 		swarmPeer.add("1.2.3.4");
 		swarmPeer.add("1.2.3.5");
 		
 		swarmPeers.setpeers(swarmPeer);
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
