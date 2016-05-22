package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import backend.json.Peers;
import backend.json.PeersInfo;
import backend.json.SwarmsInfo;
import backend.json.Sync;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;

public class DatabaseCalls implements DatabaseAPI {

	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	sqlconnector sc = new sqlconnector("serverdb");

	ResultSet rs = null;

	//This method writes to 'bootstrapserver' table
	public boolean addBootstrapServer(String ip, String name, int clientcount, int servercount){  
		return sc.Update("INSERT INTO bootstrapserver (ip, name, timestamp, clientcount, servercount) " + 
					"VALUES ('"+ip+"', '"+name+"', default,"+clientcount+", "+servercount+")");
	}

	 //This method writes to 'serverswarm' table
	public boolean addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid){  
		return sc.Update("INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " +
				"VALUES ('"+filename+"', "+totalblocks+", '"+peers+"', "+peercount+", "+uniquefileid+",'filechecksum', 'metadatachecksum')");
	}
	

	//This method writes to 'serverpeers' table
	public void addPeers(String id, String latestIP, boolean blacklist, String timestamp ){  
		sc.Update("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
					"VALUES ('"+id+"', '"+latestIP+"', "+blacklist+", '"+timestamp+"')");
	}
	
	public boolean isClientOnSwarm(String swarmID, String clientID){
		String readquery="";
		ResultSet result;
		String data="";
		readquery="select distinct clientid from peersarray where swarmID='" + swarmID + "'";
		result = sc.runquery(readquery);

		try {
			while(result.next()){
				//Retrieve by column name			
				data = result.getString("clientid");
				if (data==clientID)
					return true;
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		
		return false;
	}
	
	
	public boolean updateSwarm(String swarmID, String clientID){
		String readquery="";
		readquery = "update peersarray set clientid= '" + clientID+ "'"
					+ "' where swarmid='"+ swarmID + "'";
		
		try {
			sc.Update(readquery);
			return true;
			}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		return false;
	}
	
	
	public boolean addPeer(String id, String latestIP, boolean blacklist, String timestamp){
		boolean updateFlag = false;
		
		if(isPeerIDExisting(id)) {
			// update peer
			updateFlag = updatePeer(latestIP, id, timestamp);
		} else {
			// Adding new peer if peer don't exist
			sc.Update("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
					"VALUES ('"+id+"', '"+latestIP+"', "+blacklist+", '"+timestamp+"')");
		}
		return updateFlag;
	}
	
	
	private boolean updatePeer(String ip, String id, String timestamp, boolean blacklist){
		boolean updateflag=false;
		String updatequery = "update serverpeers set latestip= + '"+ip+ "',"
				+ " timestamp='"+ timestamp + " blacklist='"+ blacklist + "' where id='"+ id + "'";
		updateflag=sc.Update(updatequery);
		return updateflag;
	}
	
	public boolean addPeerArray(String uniquefileid, String clientIP, String clientID) {
		return sc.Update("INSERT INTO peersarray (uniquefileid, peers, clientid) VALUES ('"+uniquefileid+"', '"+clientIP+"', '"+clientID+"')");
	}

	public boolean isPeerIDExisting(String id){

		String query = "SELECT distinct id FROM serverpeers WHERE id =" + "'"+ id + "'";	
		rs=sc.runquery(query);
		try {
			if(rs.next())
				return true;			
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}
		return false;
	}

	public boolean updatePeer(String ip, String id, String timestamp){

		boolean updateflag=false;
		String updatequery = "update serverpeers set latestip= + '"+ip+ "',"
				+ " timestamp='"+ timestamp + "' where id='"+ id + "'";
		updateflag=sc.Update(updatequery);
		return updateflag;
	}

	public final class getIPoStatus {
		private final String IP;
		private final String BLACKLISTED;

		public getIPoStatus(String ip, String blacklisted) {
			this.IP = ip;
			this.BLACKLISTED = blacklisted;
		}

		public String getIP() {
			return IP;
		}

		public String getBlackListed() {
			return BLACKLISTED;
		}
	}

	public getIPoStatus getPeers(){   //This method reads from 'serverpeers' table
		String latestIP = null;
		String blackList = null;
		rs = sc.runquery("SELECT * FROM serverpeers");

		try {
			while (rs.next()) {
//				String id = rs.getString("id");
				latestIP = rs.getString("latestIP");
				blackList = rs.getString("blacklist");
//				String timestamp = rs.getString("timestamp");

//				LOG.log(Level.INFO, "\nID: " + id.toString() + 
//						"\nLastestIP: " + latestIP.toString() + 
//						"\nBlacklist: " + blackList.toString() +
//						"\nTimestamp: " + timestamp.toString()
//						);

			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}

		return new getIPoStatus(latestIP, blackList);

	} 

	public List<String> getpeers(){
		ResultSet result;
		String data="";
		int counter=0;

		String readquery="SELECT distinct latestIP FROM serverpeers";

		result = sc.runquery(readquery);
		List<String> ip = new ArrayList<String>();

		try {
			while(result.next()){
				//Retrieve by column name			
				data = result.getString("latestIP");	         
				if (counter<3){
					ip.add(data);
				}
				counter++;
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		return ip;
	}

	public List<String> getBootstraps(){
		ResultSet result;
		String readquery="SELECT distinct ip FROM bootstrapserver";
		result = sc.runquery(readquery);

		List<String> ip = new ArrayList<String>();
		try {
			while(result.next()){
				//Retrieve by column name			         
				ip.add(result.getString("ip"));
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		return ip;
	}

	public List<String> getBlacklist()	{
		ResultSet result;
		String readquery="SELECT distinct latestip FROM serverpeers where blacklist='1';";
		result = sc.runquery(readquery);
		List<String> ip = new ArrayList<String>();
		
		try {
			while(result.next()){
				//Retrieve by column name			         
				ip.add(result.getString("latestip"));
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		return ip;
	}
	
	private List<String> getPeers(String fileId) {
		List<String> peers = new ArrayList<String>();
		
		ResultSet peerSet = sc.runquery("SELECT peers FROM peersarray WHERE uniquefileid = '" + fileId + "'");
		
		try {
			while (peerSet.next()) {
				peers.add(peerSet.getString("peers"));
			}
		} catch (SQLException e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		
		return peers;
	}
	
	public SwarmsHelper getSwarms(){
		SwarmsHelper swarmHelp = new SwarmsHelper();
		String readquery="";
		ResultSet result;
		readquery="select * from serverswarm";
		result = sc.runquery(readquery);
		//Swarms swarm = new Swarms();
		List<Swarms> swarms = new ArrayList<Swarms>();

		try {
			while (result.next()) { 
				Swarms swarm = new Swarms();
				System.out.println(result.getString("filename"));
				System.out.println(result.getString("uniquefileid"));
				
				swarm.setfilename(result.getString("filename"));
				
				String fileId = result.getString("uniquefileid");
				swarm.setid(fileId);
				
				swarm.setPeers(getPeers(fileId));
				
				swarms.add(swarm);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		swarmHelp.setSwarms(swarms);		
		return swarmHelp;
	}

	public Swarm getSwarm(String swarmID){
		Swarm swarm = new Swarm();
		String readquery="";
		ResultSet result;
		int blockcount=0;
		String filename="";

		String filechecksum="";
		String metadatachecksum="";
		String filepeers="";
		
		readquery="select * from serverswarm where uniquefileid ='"+swarmID+"';";  //"+ swarmid;
		result = sc.runquery(readquery);

		LOG.log(Level.INFO, swarmID);

		List<String> peers = new ArrayList<String>();
		try {
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
			LOG.log(Level.WARNING, e.toString(), e);
		}
		readquery="select distinct peers from peersarray where uniquefileid ='"+swarmID+"';"; //" + swarmid;
		result = sc.runquery(readquery);
		try {
			while(result.next()){
				//Retrieve by column name			
				filepeers = result.getString("peers");	         
				peers.add(filepeers);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}	
		swarm.setPeers(peers);
		return swarm;
	}

	public Sync getSync()
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

	public boolean isSwarmExisting(String swarmID){
		return true;
	}
	
	//Close DB Connection
	public void closedbconnect(){
		sc.closeconnect();
		sc=null;
	}
	
	/**
	 * Function to compare incoming IP whit the ones that
	 * are in database to see if it is blacklisted
	 * @param request is the incoming rest request from remote client
	 * @return true if ip was blacklisted in db
	 */
	public boolean isBlacklisted(String ip) {
		String readquery="";
		ResultSet result;
		//String data="";
		readquery="select distinct latestip from serverpeers where blacklist='1'";
		result = sc.runquery(readquery);

		try {
			while (result.next())
			{
			if(ip.equals(result.getString("latestip")))			
				return true;	         
			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean addSwarmDB(String uuidClient, int totalBlocks, String filename, String fileChecksum,
			String metadataChecksum, String SwarmID) {
		return sc.Update("INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
				"VALUES ('"+filename+"', "+totalBlocks+", '"+uuidClient+"', 1, '"+SwarmID+"','"+fileChecksum+"', '"+metadataChecksum+"')");
	}
	
	public boolean removePeers(String ClientUUID) {
		boolean result = false;
		String readquery="DELETE FROM serverpeers WHERE peers='"+ClientUUID+"'";

		try {
			result = sc.Update(readquery);
//		LOG.log(Level.INFO, ClientUUID + " : removed from server (inactive for 3 minutes)");
		return result;
		
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		return result;
	}

	public Peers getInactivePeers(int timeout) {
		
		Peers inactivePeers = new Peers();
		String readquery="";
		long diff;
		ResultSet result;
		Timestamp time;
		//Must use ntp time stamp
		Timestamp currentTime = new Timestamp((new java.util.Date()).getTime());
		readquery="SELECT * FROM serverpeers";
		result = sc.runquery(readquery);
		List<String> ip = new ArrayList<String>();
		
		try {
			while(result.next()){
				//Retrieve by column name
				String peer = result.getString("latestIP");
				time = result.getTimestamp("timestamp");
				
				//Calculations to find timestamps older than 3 minutes 
				long seconds1 = time.getTime();
				long seconds2 = currentTime.getTime();
				diff = seconds2 - seconds1;
				long diffSeconds = diff / 1000;

				if (diffSeconds > timeout){  //see if the difference is at least 3 minutes
				//remove inactive peers here
					ip.add(peer);
			}
				
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		
		inactivePeers.setpeers(ip) ;
		
		return inactivePeers;

	}

	@Override
	public boolean removePeerArray(String uniquefileid, String clientID) {
		return sc.Update("DELETE FROM peersarray WHERE uniquefileid = '"+uniquefileid+"' AND clientid = '"+clientID+"'");
	}	
}
