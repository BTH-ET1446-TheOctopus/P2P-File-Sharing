package sql;

/* class to test writing to/reading from database */

public class ShowResult {

	public static void main(String[] args) {
		
			DBWrite drw = new DBWrite();
			
			drw.addBootstrapServer();
			
//			System.out.println("\n");
			
			drw.addPeers();
			
//			System.out.println("\n");
			
			drw.addSwarm();
	}

}
