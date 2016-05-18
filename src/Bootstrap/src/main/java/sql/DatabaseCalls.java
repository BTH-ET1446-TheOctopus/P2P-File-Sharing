package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCalls implements DatabaseAPI {
	
private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	//create an object from sqlconnector, to eb able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;
	
	public void addBootstrapServer(String ip, String name, int clientcount, int servercount){  //This method writes to 'servers' table
		sqlconnector sc = new sqlconnector();
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
	
	public void getPeers(){   //This method reads from 'serverpeers' table
		rs = sc.runquery("SELECT * FROM serverpeers");

		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String latestIP = rs.getString("latestIP");
				String blackList = rs.getString("blacklist");
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


}
