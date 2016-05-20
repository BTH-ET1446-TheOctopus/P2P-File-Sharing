
package sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class insertsample {
	public void insertcdb(){
		sqlconnector sample= new sqlconnector();
		String writequery="";
		sample.connector("clientdb");

//		writequery= "INSERT INTO clientswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
//				+ "VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'hgjsk1345', 'hsjsj8282')";
//	
//		sample.Update(writequery);
//		writequery= "INSERT INTO clientswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
//				+ "VALUES ('Pirates', 10000, '192.168.2.2', 1, 'sdfsdggh22256', 'hsjsjs123', 'ghdjjd18834')";
//		writequery= "INSERT INTO clientpeers (id, latestip, blacklist, timestamp, files, filecount) " 
//		+ "VALUES ('abc123', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'hgjsk1345', 'hsjsj8282')";
//
//		sample.Update(writequery);
//		writequery= "INSERT INTO clientpeers (id, latestip, blacklist, timestamp, files, filecount)" 
//				+ "VALUES ('Pirates', 10000, '192.168.2.2', 1, 'sdfsdggh22256', 'hsjsjs123', 'ghdjjd18834')";

		
		//Write Sample Data in clientswarm
		writequery= "INSERT INTO clientswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
				+ "VALUES ('pom.xml', 6, 'localhost', 1, 'abc123', 'hgjsk1345', 'hsjsj8282')";
		sample.Update(writequery);
	
		//Write Sample Data to peersarray table
		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('abc123','192.168.1.200')";
		//Writing Data to ServerDB
		sample.Update(writequery);
		
		//Write Sample Data to peersarray table
		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('abc123','192.168.1.201')";
		//Writing Data to ServerDB
		sample.Update(writequery);
		
		//Write Sample Data to peersarray table
		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('abc123','192.168.1.202')";
		//Writing Data to ServerDB
		sample.Update(writequery);

		//Write Sample Data to peersarray table
		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('pom.xml','192.168.1.202')";
		//Writing Data to ServerDB
		sample.Update(writequery);
		
		sample.closeconnect();
	}
}