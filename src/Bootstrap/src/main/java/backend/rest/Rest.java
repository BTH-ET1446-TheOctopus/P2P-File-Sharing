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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
/*
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
*/

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
	//@javax.ws.rs.core.Context HttpServletRequest req;

	  //@Resource WebServiceContext webServiceContext;
	/*
	@Context UriInfo uriInfo;
	@Context Request request;
	public Rest(UriInfo uriInfo, Request request)
	{
		this.uriInfo = uriInfo;
		this.request = request;
	}
	*/
	
	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public TestAddress convertCtoF(){
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
	//public String getHello(@Context HttpServletRequest req, @QueryParam("id") String id)
	public String getHello(@QueryParam("id") String id)
	{
		/*
		MessageContext messageContext = webServiceContext.getMessageContext();
	      HttpServletRequest request = (HttpServletRequest) messageContext.get(MessageContext.SERVLET_REQUEST); 
	      String callerIpAddress = request.getRemoteAddr();

	      System.out.println("Caller IP = " + callerIpAddress); 
		//System.out.println(req.getRemoteAddr());
		 * */
		 
		//String remoteHost = req.getRemoteHost();
		//System.out.println(remoteHost);
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
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MM yyyy HH:mm:ss");
		String currentTime = sdf.format(timestamp);
		
		System.out.println(currentTime);
		
		return uuid.toString();
		
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
	public Swarm getSwarm(@PathParam("id") String id)
	{

		Swarm swarm = new Swarm();
		System.out.println(id);

		//if(id == 1)
		//{
			swarm.setBlockCount(2);
			swarm.setFilename("virus.exe");
			swarm.setFileChecksum("XXXYYY");
			swarm.setMetadataChecksum("XXXYYY");
			
			List<String> peers = new ArrayList<String>();
			peers.add("1.2.3.4");
			peers.add("1.2.3.5");
			
			swarm.setPeers(peers);
		//}
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
