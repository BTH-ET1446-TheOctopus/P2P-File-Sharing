package sql;

import java.sql.*;
import java.util.Properties;

/**
 * @author Farhan
 */

public class DBRtable {
	  // The JDBC Connector Class.
		public static final String dbClassName = "com.mysql.jdbc.Driver";
		public static final String CONNECTION =
		                          "jdbc:mysql://127.0.0.1/clientdb";
		Statement stmt = null;
		ResultSet rs = null;
	
		String filename="";
		int filesize=0;
		String filetype = "";
		String peers = "";
		int peercount = 0;
		int uniquefileid = 0;
	
	public DBRtable(){	
		
		try {
			System.out.println(dbClassName);
		    // Class.forName(xxx) loads the jdbc classes and
		    // creates a driver manager class factory
		    //Class.forName(dbClassName);

		    // Properties for user and password. Here the user and password are used
		    Properties sqlprop = new Properties();
		    sqlprop.put("user","farhan");
		    sqlprop.put("password","farhan");
		    String sql="SELECT * FROM serverfile where peercount='1'";

		    // Now try to connect
		    Connection sqlconn = DriverManager.getConnection(CONNECTION,sqlprop);
		    stmt = sqlconn.createStatement();
		    rs = stmt.executeQuery(sql);

		    // Now do something with the ResultSet ....
		    while(rs.next()){
		         //Retrieve by column name
		 		 filename=rs.getString("filename");
		         filesize=rs.getInt("filesize");
		         filetype = rs.getString("filetype");
		         peers = rs.getString("peers");
		         peercount = rs.getInt("peercount");
		         uniquefileid = rs.getInt("uniquefileid");
		         
		         //Display values
		         System.out.printf("filename: %s ", filename);
		         System.out.printf("filesize: %d ", filesize);
		         System.out.printf("filetype: %s ", filetype);
		         System.out.printf("peers: %s ", peers);
		         System.out.printf("peercount: %d ", peercount);
		         System.out.printf("uniquefileid: %d\n", uniquefileid);
		      }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	  }
}