
package sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;


public class insertsample {
	
	public void insertbdb(){
		sqlconnector sample= new sqlconnector();
		String writequery="";
		sample.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
	
		//Write Sample Data to serverswarm table

		writequery= "INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
					+ "VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'hgjsk1345', 'hsjsj8282')";
		sample.Update(writequery);
		
		writequery= "INSERT INTO serverswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
				+ "VALUES ('Pirates', 10000, '192.168.2.2', 1, 'sdfsdggh22256', 'hsjsjs123', 'ghdjjd18834')";
		sample.Update(writequery);
		
		//Write Sample Data to bootstapserver table
		
		writequery="insert into bootstrapserver (ip,name,timestamp,clientcount,servercount)"
 				+ "VALUES ('172.12.58.5','server-1',2016-05-15 05:14:07,12,1)";	
		sample.Update(writequery);

		writequery="insert into bootstrapserver (ip,name,timestamp,clientcount,servercount)"
 				+ "VALUES ('172.12.58.8','server-2',2016-05-15 08:29:07,9,2)";	
		sample.Update(writequery);
		
		//Write Sample Data to peersarray table
		
		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('MSN-458','192.168.1.200')";
		sample.Update(writequery);

		writequery="insert into peersarray (uniquefileid,peers) " 
				+ " VALUES ('QWR-588','192.168.15.69')";
		sample.Update(writequery);
				
		sample.closeconnect();
	}

	public void insertcdb(){
		sqlconnector sample= new sqlconnector();
		String writequery="";
		sample.connector("root", "sql", "clientdb", "127.0.0.1", "3306");

		//Write Sample Data to clientswarm table

		writequery= "INSERT INTO clientswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
				+ "VALUES ('Pirates Carrabian', 10000, '192.168.2.2', 1, 'sdfsdggh22255', 'hgjsk1345', 'hsjsj8282')";
	
		sample.Update(writequery);
		writequery= "INSERT INTO clientswarm (filename, totalblocks, peers, peercount, uniquefileid, filechecksum, metadatachecksum) " 
				+ "VALUES ('Pirates', 10000, '192.168.2.2', 1, 'sdfsdggh22256', 'hsjsjs123', 'ghdjjd18834')";
	
		sample.Update(writequery);

		
		sample.closeconnect();
	}
}