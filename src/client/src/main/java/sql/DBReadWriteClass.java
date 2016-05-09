package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBReadWriteClass {
	
	private static final Logger LOG = Logger.getLogger(DBReadWriteClass.class.getName());

	sqlconnector sc = new sqlconnector();
	
	public DBReadWriteClass(){

	}
	
	public void addSwarm(){   //This method writes to 'clientfile' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO clientfile (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 2255, 'filechecksum', 'metadatachecksum' )");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	public void addPeers(){  //This method writes to 'clientpeers' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO clientpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
					"VALUES (1026, '192.168.31.52', 0, default,'Captain Ameerica Civil War', 0)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
}
