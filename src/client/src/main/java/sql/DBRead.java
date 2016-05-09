package sql;

/* class to read data from each table */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBRead {

	private static final Logger LOG = Logger.getLogger(DBRead.class.getName());
	
	//create an object from sqlconnector, to be able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;

	public DBRead() {

	}
	
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

}