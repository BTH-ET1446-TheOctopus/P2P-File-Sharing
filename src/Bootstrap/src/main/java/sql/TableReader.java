package sql;

/* class to read data from each table */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import backend.rest.RESTStartUp;

public class TableReader {

	private static final Logger LOG = Logger.getLogger(TableReader.class.getName());
	
	//create an object from sqlconnector, to eb able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;

	public TableReader(String tableName){	

		try {
			
			/* sql user,pass,database name, host, port defined to connect to the database */
			sc.connector("root", "", "serverdb", "localhost", "3306");

			//read data from specific table, which we passed to function
			if(tableName.equals("serverpeers")){
				rs = sc.runquery("SELECT * FROM "+tableName);

				while (rs.next()) {
					String id = rs.getString("id");
					String latestIP = rs.getString("latestIP");
					String blackList = rs.getString("blacklist");
					String timestamp = rs.getString("timestamp");
					String files = rs.getString("files");
					String filecount = rs.getString("filecount");

					LOG.log(Level.INFO, "ID: " + id.toString() + 
							"\nLastestIP: " + latestIP.toString() + 
							"\nBlacklist: " + blackList.toString() +
							"\nTimestamp: " + timestamp.toString() +
							"\nFiles: " + files.toString() +
							"\nFilecount: " + filecount.toString());

				}
				
			} else if (tableName.equals("serverfile")) {
				rs = sc.runquery("SELECT * FROM "+tableName+" where peercount='1'");

				while(rs.next()){
					//Retrieve by column name
					String filename=rs.getString("filename");
					String filesize=rs.getString("filesize");
					String filetype=rs.getString("filetype");
					String peers = rs.getString("peers");
					String peercount = rs.getString("peercount");
					String uniquefileid = rs.getString("uniquefileid");

					//Display values
					LOG.log(Level.INFO, "filename: " + filename.toString() + 
							"\nfilesize: " + filesize.toString() + 
							"\nfiletype: " + filetype.toString() +
							"\npeers: " + peers.toString() +
							"\npeercount: " + peercount.toString() +
							"\nuniquefileid: " + uniquefileid.toString());
				}	

			} else if (tableName.equals("servers")) {
				rs = sc.runquery("SELECT * FROM "+tableName);

				while(rs.next()){
					//Retrieve by column name
					String ip=rs.getString("ip");
					String name=rs.getString("name");
					String timestamp=rs.getString("timestamp");
					String clientcount = rs.getString("clientcount");
					String servercount = rs.getString("servercount");

					//Display values
					LOG.log(Level.INFO, "ip: " + ip.toString() + 
							"\nname: " + name.toString() + 
							"\ntimestamp: " + timestamp.toString() +
							"\nclientcount: " + clientcount.toString() +
							"\nservercount: " + servercount.toString());
				}	

			}
		}
		catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}
	}
}