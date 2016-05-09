package sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBWrite {

	sqlconnector sc = new sqlconnector();
	private static final Logger LOG = Logger.getLogger(DBWrite.class.getName());
	
	public DBWrite(){
		
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
