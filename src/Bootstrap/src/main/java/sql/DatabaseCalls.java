package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import backend.Settings;
import backend.json.Blacklist;
import backend.json.PeersInfo;
import backend.json.SwarmsInfo;
import backend.json.Sync;

public class DatabaseCalls implements DatabaseAPI {

	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());

	//create an object from sqlconnector, to eb able to connect to the database
	sqlconnector sc = new sqlconnector("serverdb");

	ResultSet rs = null;

	public void addBootstrapServer(String ip, String name, int clientcount, int servercount){  //This method writes to 'servers' table
		sqlconnector sc = new sqlconnector("serverdb");
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO bootstrapserver (ip, name, timestamp, clientcount, servercount) " + 
					"VALUES ('"+ip+"', '"+name+"', default,"+clientcount+", "+servercount+")");
			//"VALUES ('192.168.54.68', 'Backup01', default,2, 1)");

		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid){   //This method writes to 'serverfile' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('"+filename+"', "+totalblocks+", '"+peers+"', "+peercount+", "+uniquefileid+",'filechecksum', 'metadatachecksum')");
			//"VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 2255, 'filechecksum', 'metadatachecksum' )");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addPeers(String id, String latestIP, int blacklist, String timestamp ){  //This method writes to 'serverpeers' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
					"VALUES ("+id+", '"+latestIP+"', "+blacklist+", "+timestamp+")");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		} finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addPeerArray(String uniquefileid, String peers){  //This method writes to 'peersarray' table
		sqlconnector sc = new sqlconnector();

		Statement stmnt = sc.getStatement();

		try {
			stmnt.executeUpdate("INSERT INTO peersarray (uniquefileid, peers) " + 
					"VALUES ( '"+uniquefileid+"', '"+peers+"')");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}


	public void getBootstrapServer() {  //This method reads from 'bootstrapserver' table
		rs = sc.runquery("SELECT * FROM bootstrapserver");

		try {
			while(rs.next()){
				//Retrieve by column name
				String ip=rs.getString("ip");
				String name=rs.getString("name");
				String timestamp=rs.getString("timestamp");
				String clientcount = rs.getString("clientcount");
				String servercount = rs.getString("servercount");

				//Display values
				LOG.log(Level.INFO, "\nip: " + ip.toString() + 
						"\nname: " + name.toString() + 
						"\ntimestamp: " + timestamp.toString() +
						"\nclientcount: " + clientcount.toString() +
						"\nservercount: " + servercount.toString());
			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	

	}

	public void getSwarm() {  //This method reads from 'serverswarm' table
		rs = sc.runquery("SELECT * FROM serverswarm where peercount='1'");

		try {
			while(rs.next()){
				//Retrieve by column name
				String filename=rs.getString("filename");
				String totalblocks=rs.getString("totalblocks");
				String peers = rs.getString("peers");
				String peercount = rs.getString("peercount");
				String uniquefileid = rs.getString("uniquefileid");
				String filechecksum = rs.getString("filechecksum");
				String metadatachecksum = rs.getString("metadatachecksum");

				//Display values
				LOG.log(Level.INFO, "\nfilename: " + filename.toString() + 
						"\ntotalblocks: " + totalblocks.toString() + 
						"\npeers: " + peers.toString() +
						"\npeercount: " + peercount.toString() +
						"\nuniquefileid: " + uniquefileid.toString() +
						"\nfilechecksum: " + filechecksum.toString() +
						"\nmetadatachecksum: " + metadatachecksum.toString()
						);
			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	
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
				String id = rs.getString("id");
				latestIP = rs.getString("latestIP");
				blackList = rs.getString("blacklist");
				String timestamp = rs.getString("timestamp");

				LOG.log(Level.INFO, "\nID: " + id.toString() + 
						"\nLastestIP: " + latestIP.toString() + 
						"\nBlacklist: " + blackList.toString() +
						"\nTimestamp: " + timestamp.toString()
						);

			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}

		return new getIPoStatus(latestIP, blackList);

	} 


	public void getPeerArray() {  //This method reads from 'peersarray' table
		rs = sc.runquery("SELECT * FROM peersarray");

		try {
			while(rs.next()){
				//Retrieve by column name
				String uniquefileid=rs.getString("uniquefileid");
				String peers=rs.getString("peers");

				//Display values
				LOG.log(Level.INFO, "\nuniquefileid: " + uniquefileid.toString() + 
						"\npeers: " + peers.toString());
			}
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	

	}

	public Blacklist getBlacklist()	{

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
	
	/**
	 * Function to compare incoming IP whit the ones that
	 * are in database to see if it is blacklisted
	 * @param request is the incoming rest request from remote client
	 * @return true if ip was blacklisted in db
	 */
	public boolean isBlacklisted(String ip) {
		
		boolean status = false;
		String readquery="";
		ResultSet result;
		String data="";
		readquery="select distinct blacklist from serverpeers where ip='"+ip+"';";
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
