package backend.thread;

import java.util.ArrayList;
import java.util.List;

import backend.rest.ClientCalls;
import sql.DatabaseCalls;

public class ClientSearchThread extends Thread {
	String filename;
	String ip;
	int hopLimit;
	
	public ClientSearchThread(String filename, String ip, int hopLimit) {
		this.filename = filename;
		this.ip = ip;
		this.hopLimit = hopLimit;
	}
	
	public void run() {
		DatabaseCalls databaseCalls = new DatabaseCalls();	
		ClientCalls clientCalls = new ClientCalls();
		boolean exist = false;
		//Check if the filename is in the database
			exist = databaseCalls.getSwarmByName(filename);
		//if the file exist, get fileinformation and send
		if(exist == true)	{
			//databaseCalls.getswarmInfoBYName(filename);
			//clientCalls.searchResult(String clientIP, String id, Integer blockCount, String filename, String fileChecksum, String metadataChecksum);
			}
		//If the file doesnt exist, send search request to nearby Peers
		else {
			List<String> peers = new ArrayList<String>();	
			peers = databaseCalls.getPeers();
			hopLimit = hopLimit-1;
			for(int i=0; i<peers.size() && (peers.get(i)!=ip);i++)	{
			clientCalls.search(peers.get(1),filename, ip, hopLimit);
			}							
		}
	}
}
