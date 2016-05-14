package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import backend.api.datatypes.SwarmMetadata;
import sql.api.DatabaseAPI;

public class DatabaseCalls implements DatabaseAPI{
	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	//create an object from sqlconnector, to be able to connect to the database
	sqlconnector sc = new sqlconnector();
	ResultSet rs = null;
	
	public SwarmMetadata getSwarm() {  //This method reads from 'clientfile' table
		rs = sc.runquery("SELECT * FROM clientfile where peercount='1'");

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
				LOG.log(Level.INFO, "\nfilename: " + filename + 
						"\nfilesize: " + totalblocks + 
						"\npeers: " + peers +
						"\npeercount: " + peercount +
						"\nuniquefileid: " + uniquefileid +
						"\nfilechecksum: " + filechecksum +
						"\nmetadatachecksum: " + metadatachecksum
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
		//return new SwarmMetadata(uniquefileid, filename, fileChecksum, metadataChecksum, blockCount);
	}
	
	public List<String> getPeers(){   //This method reads from 'clientpeers' table
		
		List<String> result = new ArrayList<String>();
		rs = sc.runquery("SELECT * FROM clientpeers");
		
		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String latestip = rs.getString("latestip");
				String blackList = rs.getString("blacklist");
				String timestamp = rs.getString("timestamp");
				String files = rs.getString("files");
				String filecount = rs.getString("filecount");
				
				result.add(latestip);
				
				LOG.log(Level.INFO, "\nID: " + id + 
						"\nLastestip: " + latestip + 
						"\nBlacklist: " + blackList +
						"\nTimestamp: " + timestamp +
						"\nFiles: " + files +
						"\nFilecount: " + filecount);

			}
			
		} catch (SQLException ex){
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}
		return result;
	}
	
	public void getPeerArray() {  //This method reads from 'peersarray' table
		rs = sc.runquery("SELECT * FROM peersarray");

		try {
			while(rs.next()){
				//Retrieve by column name
				String uniquefileid=rs.getString("uniquefileid");
				String peers=rs.getString("peers");

				//Display values
				LOG.log(Level.INFO, "\nuniquefileid: " + uniquefileid + 
						"\npeers: " + peers);
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

	public void addSwarm(String id, String filename, String fileChecksum, String metadataChecksum, int blockCount){   //This method writes to 'clientfile' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();
				
		try {
			stmnt.executeUpdate("INSERT INTO clientfile (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('"+filename+"', "+blockCount+", '192.168.2.2', 1, '"+id+"', '"+fileChecksum+"', '"+metadataChecksum+"' )");
					//"VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'filechecksum', 'metadatachecksum' )");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	public void addPeers(String swarmId, String ip){  //This method writes to 'clientpeers' table
		sqlconnector sc = new sqlconnector();

		Statement stmnt = sc.getStatement();
		
		try {
			stmnt.executeUpdate("INSERT INTO clientpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
					"VALUES ('"+swarmId+"', '"+ip+"', 0, default,'Captain Ameerica Civil War', 0)");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	@Override
	public void addPeerArray() {
		// TODO Auto-generated method stub
		
	}

}
