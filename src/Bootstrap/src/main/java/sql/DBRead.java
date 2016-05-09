package sql;

/* class to read data from each table */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import backend.rest.RESTStartUp;

public class DBRead {

	private static final Logger LOG = Logger.getLogger(DBRead.class.getName());
	
	//create an object from sqlconnector, to be able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;

	public DBRead() {

	}
	
	public void getBootstrapServer() {  //This method reads from 'servers' table
		rs = sc.runquery("SELECT * FROM servers");

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
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	

	}
	
	public void getSwarm() {  //This method reads from 'serverfile' table
		rs = sc.runquery("SELECT * FROM serverfile where peercount='1'");

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
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}
		
	} 

}