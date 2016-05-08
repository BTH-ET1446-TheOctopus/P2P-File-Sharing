package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Logger;

//import backend.rest.RESTStartUp;

public class DBWrite {

	private static final Logger LOG = Logger.getLogger(DBWrite.class.getName());
	
	sqlconnector sc = new sqlconnector();

	public DBWrite(){
		
	}
	
	public void addBootstrapServer(){  //This method writes to 'servers' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO servers (ip, name, timestamp, clientcount, servercount) " + 
					"VALUES ('192.168.54.68', 'Backup01', default,2, 1)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addSwarm(){   //This method writes to 'serverfile' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO serverfile (filename, filesize, filetype, peers, peercount, uniquefileid) " + 
					"VALUES ('Pirates Carrabian', 10000, 'MP4', '192.168.2.2', 1, 2255 )");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	public void addPeers(){  //This method writes to 'serverpeers' table
		sqlconnector sc = new sqlconnector();
		
		/* stablish the connection to database */
		Connection connect = sc.getConnection();
		Statement stmnt = sc.getStatement();
		ResultSet rs = sc.getSet();
		
		try {
			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
					"VALUES (1026, '192.168.31.52', 1, default,'Captain Ameerica Civil War', 0)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	}
