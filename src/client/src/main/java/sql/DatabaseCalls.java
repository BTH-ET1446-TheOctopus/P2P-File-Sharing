package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCalls implements DatabaseAPI{
	private static final Logger LOG = Logger.getLogger(DatabaseCalls.class.getName());
	
	//create an object from sqlconnector, to be able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;

	
	public void getSwarm() {  //This method reads from 'clientfile' table
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
	}
	
	public void getPeers(){   //This method reads from 'clientpeers' table
		rs = sc.runquery("SELECT * FROM clientpeers");

		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String latestip = rs.getString("latestip");
				String blackList = rs.getString("blacklist");
				String timestamp = rs.getString("timestamp");
				String files = rs.getString("files");
				String filecount = rs.getString("filecount");

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
	public void addPeerArray(){  //This method writes to 'peersarray' table
		sqlconnector sc = new sqlconnector();

		Statement stmnt = sc.getStatement();
		
		try {
			stmnt.executeUpdate("INSERT INTO peersarray (uniquefileid, peers) " + 
					"VALUES ( '2sdfd5sdf6dsf5', '192.168.54.68')");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addSwarm(){   //This method writes to 'clientfile' table
		sqlconnector sc = new sqlconnector();

		Statement stmnt = sc.getStatement();
				
		try {
			stmnt.executeUpdate("INSERT INTO clientfile (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'filechecksum', 'metadatachecksum' )");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	public void addPeers(){  //This method writes to 'clientpeers' table
		sqlconnector sc = new sqlconnector();

		Statement stmnt = sc.getStatement();
		
		try {
			stmnt.executeUpdate("INSERT INTO clientpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
					"VALUES ('21dfg1df1g21g', '192.168.31.52', 0, default,'Captain Ameerica Civil War', 0)");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
}
