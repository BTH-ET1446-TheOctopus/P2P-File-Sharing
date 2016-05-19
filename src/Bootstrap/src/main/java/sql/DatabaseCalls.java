package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.Peers;
import backend.json.PeersInfo;
import backend.json.SwarmsInfo;
import backend.json.Sync;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;

public class DatabaseCalls implements DatabaseAPI {

	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());


	ResultSet rs = null;

	public void addBootstrapServer(String ip, String name, int clientcount, int servercount){  //This method writes to 'servers' table
		sqlconnector sc = new sqlconnector("serverdb");
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO bootstrapserver (ip, name, timestamp, clientcount, servercount) " + 
					"VALUES ('"+ip+"', '"+name+"', default,"+clientcount+", "+servercount+")");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
//		finally {  //close all connection to database
//			sc.closeconnect();
//		}
	}

	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid){   //This method writes to 'serverfile' table
		sqlconnector sc = new sqlconnector("serverdb");
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('"+filename+"', "+totalblocks+", '"+peers+"', "+peercount+", "+uniquefileid+",'filechecksum', 'metadatachecksum')");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
//		finally {  //close all connection to database
//			sc.closeconnect();
//		}
	}
	

	public void addPeers(String id, String latestIP, boolean blacklist, String timestamp ){  //This method writes to 'serverpeers' table
		sqlconnector sc = new sqlconnector("serverdb");
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
					"VALUES ('"+id+"', '"+latestIP+"', "+blacklist+", '"+timestamp+"')");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
//		finally {  //close all connection to database
//			sc.closeconnect();
//		}
	}
	
	public boolean addPeer(String id, String latestIP, boolean blacklist, String timestamp){
		sqlconnector sc = new sqlconnector("serverdb");
		Statement stmnt = sc.getStatement();
		boolean updateFlag = false;
		
		if(isPeerIDExisting(id)) {
			// update peer
			updateFlag = updatePeer(latestIP, id, timestamp);
		} else {
			// Adding new peer if peer don't exist
			try {
				stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
						"VALUES ('"+id+"', '"+latestIP+"', "+blacklist+", '"+timestamp+"')");
			} catch (SQLException e) {
				LOG.log(Level.INFO, e.getMessage(), e);
			}
		}
		return updateFlag;
	}
	
	
	private boolean updatePeer(String ip, String id, String timestamp, boolean blacklist){
		sqlconnector sc = new sqlconnector("serverdb");
		boolean updateflag=false;
		String updatequery = "update serverpeers set latestip= + '"+ip+ "',"
				+ " timestamp='"+ timestamp + " blacklist='"+ blacklist + "' where id='"+ id + "'";
		updateflag=sc.Update(updatequery);
//		sc.closeconnect();
		return updateflag;
	}
	
	

	public void addPeerArray(String uniquefileid, String peers){  //This method writes to 'peersarray' table
		sqlconnector sc = new sqlconnector("serverdb");

		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO peersarray (uniquefileid, peers) " + 
					"VALUES ( '"+uniquefileid+"', '"+peers+"')");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
//		finally {  //close all connection to database
//			sc.closeconnect();
//		}
	}

	public boolean isPeerIDExisting(String id){
		sqlconnector sc = new sqlconnector("serverdb");
		String query = "select distinct id from serverpeers where id =" + "'"+ id + "'";	
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
//		finally  {
//			sc.closeconnect();
//		}
		return false;
	}

	public boolean updatePeer(String ip, String id, String timestamp){
		sqlconnector sc = new sqlconnector("serverdb");
		boolean updateflag=false;
		String updatequery = "update serverpeers set latestip= + '"+ip+ "',"
				+ " timestamp='"+ timestamp + "' where id='"+ id + "'";
		updateflag=sc.Update(updatequery);
//		sc.closeconnect();
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
		sqlconnector sc = new sqlconnector("serverdb");
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
//		finally  {
//			sc.closeconnect();
//		}

		return new getIPoStatus(latestIP, blackList);

	} 

	public Peers getpeers(){
		Peers peers = new Peers();
		String readquery="";
		sqlconnector SC = new sqlconnector("serverdb");
		ResultSet result;
		String data="";
		int counter=0;
		readquery="select distinct peers from peersarray";
		result = SC.runquery(readquery);
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
//		test.closeconnect();
		peers.setpeers(ip);

		return peers;
	}

	public Bootstraps getBootstraps(){
		Bootstraps bootstraps = new Bootstraps();
		String readquery="";
		sqlconnector sc = new sqlconnector("severdb");
		ResultSet result;
		String data="";
		readquery="select distinct ip from bootstrapserver";
		result = sc.runquery(readquery);

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
//		test.closeconnect();
		bootstraps.setbootstraps(ip);
		return bootstraps;
	}

	public Blacklist getBlacklist()	{
		sqlconnector sc = new sqlconnector("serverdb");
		Blacklist blacklist = new Blacklist();

		String readquery="";
		ResultSet result;
		String data="";
		readquery="select distinct latestip from serverpeers where blacklist='1';";
		result = sc.runquery(readquery);
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
		blacklist.setblacklist(ip);

		return blacklist;
	}

	public SwarmsHelper getSwarms(){
		SwarmsHelper swarmHelp = new SwarmsHelper();
		String readquery="";
		sqlconnector sc = new sqlconnector("serverdb");
		ResultSet result;
		readquery="select * from serverswarm";
		result = sc.runquery(readquery);
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
//		sc.closeconnect();
		swarmHelp.setSwarms(swarms);		
		return swarmHelp;
	}

	public Swarm getSwarm(String swarmID){
		Swarm swarm = new Swarm();
		String readquery="";
		sqlconnector sc = new sqlconnector("serverdb");
		ResultSet result;
		int blockcount=0;
		String filename="";

		String filechecksum="";
		String metadatachecksum="";
		String filepeers="";
		String swarmid="'"+swarmID+"'";
		readquery="select * from serverswarm where uniquefileid ='"+swarmid+"';";  //"+ swarmid;
		result = sc.runquery(readquery);

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
		readquery="select distinct peers from peersarray where uniquefileid ='"+swarmid+"';"; //" + swarmid;
		result = sc.runquery(readquery);
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
//		sc.closeconnect();	
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

	public boolean getSwarmByName(String filename){
		
		sqlconnector sc = new sqlconnector("serverdb");
		String query = "select distinct filename from serverswarm where filename =" + "'"+ filename + "'";	
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
//		finally  {
//			sc.closeconnect();
//		}
		return false;
	}
	
	/**
	 * Function to compare incoming IP whit the ones that
	 * are in database to see if it is blacklisted
	 * @param request is the incoming rest request from remote client
	 * @return true if ip was blacklisted in db
	 */
	public boolean isBlacklisted(String ip) {
		sqlconnector sc = new sqlconnector("serverdb");
		boolean status = false;
		String readquery="";
		ResultSet result;
		String data="";
		readquery="select distinct blacklist from serverpeers where latestip='"+ip+"';";
		result = sc.runquery(readquery);

		try {
			System.out.println();
			while(result.next()){
				//Retrieve by column name			
				data = result.getString("blacklist");	         
			}

			if (data.equals(1)) {
				status = true;
			} else {
				status = false;
			}	
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}
		return status;
	}
	
	
}
