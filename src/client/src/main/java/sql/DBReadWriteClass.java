package sql;

//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import oldCodes.sqlconnector;

//import backend.rest.RESTStartUp;

public class DBReadWriteClass {
	
	private static final Logger LOG = Logger.getLogger(DBReadWriteClass.class.getName());

	sqlconnector sc = new sqlconnector();
	
	public DBReadWriteClass(){
		try {

			sqlconnector sc = new sqlconnector();
			sc.connector("root", "", "serverdb", "localhost", "3306");
			
			Connection connect = sc.getConnection();
			Statement stmnt = sc.getStatement();
			ResultSet rs = sc.getSet();

			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
					"VALUES (1026, '192.168.31.52', 1, default,'Captain Ameerica Civil War', 0)");

			rs = stmnt.executeQuery("SELECT * FROM serverpeers");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
//			try { if (stmnt != null) stmnt.close(); } catch (SQLException e) { e.printStackTrace(); }
//			try { if (connect != null) connect.close(); } catch (SQLException e) { e.printStackTrace(); }
//		}
	}
	public void addSwarm(){   //This method writes to 'serverfile' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO clientfile (filename, filesize, filetype, peers, peercount, uniquefileid) " + 
					"VALUES ('Pirates Carrabian', 10000, 'MP4', '192.168.2.2', 1, 2255 )");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
}
