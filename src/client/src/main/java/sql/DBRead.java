package sql;

/* class to read data from each table */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBRead {

	private static final Logger LOG = Logger.getLogger(DBRead.class.getName());
	
	//create an object from sqlconnector, to be able to connect to the database
	sqlconnector sc = new sqlconnector();

	ResultSet rs = null;

	public DBRead() {

	}
	
	public void getPeerArray() {  //This method reads from 'peersarray' table
		rs = sc.runquery("SELECT * FROM peersarray");

		try {
			while(rs.next()){
				//Retrieve by column name
				String uniquefileid=rs.getString("uniquefileid");
				String peers=rs.getString("peers");

				//Display values
				LOG.log(Level.INFO, "uniquefileid: " + uniquefileid.toString() + 
						"\npeers: " + peers.toString());
			}
		} catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally  {
			sc.closeconnect();
		}	

	} 

}