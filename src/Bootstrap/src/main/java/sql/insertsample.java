//
//package sql;
//
//public class insertsample {
//	
//	public void insertbdb(){
//		sqlconnector sample= new sqlconnector();
//		String writequery="";
//		sample.connector("root", "sql", "serverdb", "127.0.0.1", "3306");
//		
//		//Write Sample Data to servers table
//		writequery="insert into servers (ip,name,timestamp,clientcount,servercount) " 
//				+ " VALUES ('192.168.1.10','server-1','2015-05-11 20:00:00','10','3')";
//		sample.Update(writequery);
//		
//		//Write Sample Data to servers table
//		writequery="insert into servers (ip,name,timestamp,clientcount,servercount) " 
//				+ " VALUES ('192.168.1.20','server-2','2015-05-11 20:00:00','10','3')";
//		sample.Update(writequery);
//
//		//Write Sample Data to servers table
//		writequery="insert into servers (ip,name,timestamp,clientcount,servercount) " 
//				+ " VALUES ('192.168.1.30','server-3','2015-05-11 20:00:00','10','3')";
//		sample.Update(writequery);
//		sample.closeconnect();
//	}
//
//	public void insertcdb(){
//		sqlconnector sample= new sqlconnector();
//		String writequery="";
//		sample.connector("root", "sql", "clientdb", "127.0.0.1", "3306");
//
//		//Write Sample Data to peersarray table
//		writequery="insert into peersarray (uniquefileid,peers) " 
//				+ " VALUES ('abc123','192.168.1.200')";
//		//Writing Data to ServerDB
//		sample.Update(writequery);
//		
//		//Write Sample Data to peersarray table
//		writequery="insert into peersarray (uniquefileid,peers) " 
//				+ " VALUES ('abc123','192.168.1.201')";
//		//Writing Data to ServerDB
//		sample.Update(writequery);
//		
//		//Write Sample Data to peersarray table
//		writequery="insert into peersarray (uniquefileid,peers) " 
//				+ " VALUES ('abc123','192.168.1.202')";
//		//Writing Data to ServerDB
//		sample.Update(writequery);
//		
//		sample.closeconnect();
//	}
//}