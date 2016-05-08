//package sql;
//
///* class to test writing to/reading from database */
//
//public class ShowResult {
//
//	public static void main(String[] args) {
//
//
//		/* only these variables currently we can pass to each method:
//		drw.addBootstrapServer(ip, name, clientcount, servercount);
//		drw.addSwarm(filename, totalblocks, peers, peercount, uniquefileid);
//		drw.addPeers(id, latestIP, blacklist);
//		 */
//		//write to each table
//		DBWrite drw = new DBWrite();			
//		drw.addBootstrapServer("192.168.222.5", "backup01", 2, 1);
//
//		//read from each table
//		DBRead dbr = new DBRead();
//		dbr.getBootstrapServer();
//
//	}
//
//}
