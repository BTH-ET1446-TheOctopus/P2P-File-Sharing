package backend.thread;

import java.util.ArrayList;
import java.util.List;

import backend.api.datatypes.SwarmMetadata;
import backend.rest.ClientCalls;
import backend.rest.RESTStartUp;
import sql.DatabaseCalls;


public class ClientSearchThread extends Thread {
	String filename;
	
	public ClientSearchThread(String filename) {
		this.filename = filename;
	}
	
	public void run() {
		DatabaseCalls databaseCalls = new DatabaseCalls();	
		ClientCalls clientCalls = new ClientCalls();
		List<String> peers = new ArrayList<String>();	
		peers = databaseCalls.getconnPeers();
		System.out.print(peers.size());
		int hopLimit = 2;		
		String ip =RESTStartUp.getLocalAddress(); //should be automatic
		for(int i=0; i<peers.size() && (peers.get(i)!=ip);i++)	{
		clientCalls.search(peers.get(i),filename, ip, hopLimit);
		}	
	}
}
