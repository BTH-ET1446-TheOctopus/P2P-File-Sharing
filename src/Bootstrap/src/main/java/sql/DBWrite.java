package sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import backend.rest.RESTStartUp;

public class DBWrite {
	
	sqlconnector sc = new sqlconnector();
	
	private static final Logger LOG = Logger.getLogger(DBWrite.class.getName());
	public DBWrite(){
		
	}
	
	public void addBootstrapServer(String ip, String name, int clientcount, int servercount){  //This method writes to 'servers' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();
		
		try {
			stmnt.executeUpdate("INSERT INTO servers (ip, name, timestamp, clientcount, servercount) " + 
					"VALUES ('"+ip+"', '"+name+"', default,"+clientcount+", "+servercount+")");
			//"VALUES ('192.168.54.68', 'Backup01', default,2, 1)");
			
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}

	public void addSwarm(String filename, int totalblocks, String peers, int peercount, int uniquefileid){   //This method writes to 'serverfile' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();
				
		try {
			stmnt.executeUpdate("INSERT INTO serverfile (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " + 
					"VALUES ('"+filename+"', "+totalblocks+", '"+peers+"', "+peercount+", "+uniquefileid+",'filechecksum', 'metadatachecksum')");
			//"VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 2255, 'filechecksum', 'metadatachecksum' )");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		}
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
	
	public void addPeers(String id, String latestIP, int blacklist){  //This method writes to 'serverpeers' table
		sqlconnector sc = new sqlconnector();
		Statement stmnt = sc.getStatement();
		
		try {
			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp) " + 
					"VALUES ("+id+", '"+latestIP+"', "+blacklist+", default)");
		} catch (SQLException e) {
			LOG.log(Level.INFO, e.getMessage(), e);
		} finally {  //close all connection to database
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
	
	}
