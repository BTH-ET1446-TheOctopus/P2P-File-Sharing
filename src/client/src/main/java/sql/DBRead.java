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
				LOG.log(Level.INFO, "filename: " + filename.toString() + 
						"\nfilesize: " + totalblocks.toString() + 
						"\npeers: " + peers.toString() +
						"\npeercount: " + peercount.toString() +
						"\nuniquefileid: " + uniquefileid.toString() +
						"\nfilechecksum: " + filechecksum.toString() +
						"\nmetadatachecksum: " + metadatachecksum.toString()
						);
			}
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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

				LOG.log(Level.INFO, "ID: " + id.toString() + 
						"\nLastestip: " + latestip.toString() + 
						"\nBlacklist: " + blackList.toString() +
						"\nTimestamp: " + timestamp.toString() +
						"\nFiles: " + files.toString() +
						"\nFilecount: " + filecount.toString());

			}
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
				LOG.log(Level.INFO, "uniquefileid: " + uniquefileid.toString() + 
						"\npeers: " + peers.toString());
			}
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	

	} 

}