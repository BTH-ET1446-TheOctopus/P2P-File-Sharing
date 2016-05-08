package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.logging.Logger;

//import backend.rest.RESTStartUp;

public class TableWriter {

	private static final Logger LOG = Logger.getLogger(TableWriter.class.getName());
	
	sqlconnector sc = new sqlconnector();

	public TableWriter(){
		try {
			
			/* sql user,pass,database name, host, port */
			sc.connector("root", "", "serverdb", "localhost", "3306");
			/* stablish the connection to database */
			Connection connect = sc.getConnection();
			Statement stmnt = sc.getStatement();
			ResultSet rs = sc.getSet();
			
			/* tablename to write data to, choose one below */
			String tableName = "servers";
			//String tableName = "serverpeers";
			//String tableName = "serverfile";
			
			/* writing samples to each table  */
			/* Writing to each table is "one at a time", not simultaneously!
			 * If we want it, simply change each "esle if" to "if"
			 * and you can provide an "else" for each one
			 */
			
			if (tableName.equals("serverfile")){
				stmnt.executeUpdate("INSERT INTO "+tableName+" (filename, filesize, filetype, peers, peercount, uniquefileid) " + 
						"VALUES ('Pirates Carrabian', 10000, 'MP4', '192.168.2.2', 1, 2255 )");

				TableReader rt = new TableReader(tableName);
				
			} else if (tableName.equals("serverpeers")){
				stmnt.executeUpdate("INSERT INTO "+tableName+" (id, latestIP, blacklist, timestamp, files, filecount) " + 
						"VALUES (1026, '192.168.31.52', 1, default,'Captain Ameerica Civil War', 0)");

				TableReader rt = new TableReader(tableName);
				
			} else if (tableName.equals("servers")){
				stmnt.executeUpdate("INSERT INTO "+tableName+" (ip, name, timestamp, clientcount, servercount) " + 
						"VALUES ('192.168.54.68', 'Backup01', default,2, 1)");

				TableReader rt = new TableReader(tableName);
				
			} else {
				System.out.println("There is no such table name in server database!");
			}


		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {  //close all connection to database
			sc.closeconnect();
		}
	}
}
