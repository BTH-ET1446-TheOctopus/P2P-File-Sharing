package sql;

import java.sql.*;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author Farhan
 */

public class sqlconnector {

private static final String dbClassName = "com.mysql.jdbc.Driver";
private static final String CONNECTION = "jdbc:mysql://localhost:3306/serverdb";
private Connection  connection  = null;
private Statement   statement   = null;
private ResultSet   set         = null;

String host;
String port;
String login;
String password;
String url;

String filename="";
int totalblocks=0;
String peers = "";
int peercount = 0;
String uniquefileid = "";
String filechecksum = "";
String metadatachecksum = "";

public void connector(String login, String password, String db, String host, String port) {
    this.login = login;
    this.password = password;
    this.host = host;
    this.port = port;
    url = "jdbc:mysql://"+host+":"+port+"/"+db;
    connect();
}

public void Disconnect () {
    connection = null;
}

private void connect() {
    try {
        connection = DriverManager.getConnection(url, login, password);
        statement = connection.createStatement();
    }

    catch (SQLException ex) {
    	// handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
    }
}

public ResultSet runquery(String query){

    try {
        statement = connection.createStatement();
        set = statement.executeQuery(query);
    }
    catch (Exception e) {
        System.out.println("Exception in query method:\n" + e.getMessage());
    }
    return set;
}

public void printresult(ResultSet rs){
	try {
		System.out.println();
		while(rs.next()){
	         //Retrieve by column name
	 		 filename=rs.getString("filename");
	         totalblocks=rs.getInt("totalblocks");
	         peers = rs.getString("peers");
	         peercount = rs.getInt("peercount");
	         uniquefileid = rs.getString("uniquefileid");
	         filechecksum = rs.getString("filechecksum");
	         metadatachecksum = rs.getString("metadatachecksum");
	         
	         //Display values
	         System.out.printf("filename: %s ", filename);
	         System.out.printf("totalblocks: %d ", totalblocks);
	         System.out.printf("peers: %s ", peers);
	         System.out.printf("peercount: %s ", peercount);
	         System.out.printf("uniquefileid: %s ", uniquefileid);
	         System.out.printf("filechecksum: %s\n", filechecksum);
	         System.out.printf("filechecksum: %s\n", metadatachecksum);
	      }
    }
    catch (Exception e) {
        System.out.println("Exception in query method:\n" + e.getMessage());
    }

}
public void printpeersarray(ResultSet rs){
	try {
		System.out.println();
		while(rs.next()){
	         //Retrieve by column name			
	         //uniquefileid = rs.getString("uniquefileid");
	         peers = rs.getString("peers");	         
	         //Display values 
	         //System.out.printf("uniquefileid: %s ", uniquefileid);
	         System.out.printf("peers: %s ", peers);
	         System.out.println();
	      }
    }
    catch (Exception e) {
        System.out.println("Exception in query method:\n" + e.getMessage());
    }

}

public boolean Update (String update) {

    try {
        statement = connection.createStatement();
        statement.executeUpdate(update);

    }
    catch (SQLException e) {
        System.out.println("Exception in update method:\n" + e.getMessage());
        return false;
    }

    return true;
}

/*public boolean write (String write) {

    try {
    	String writequery = "INSERT INTO serverfile (filename,filesize,filetype,peers,peercount,uniquefileid) "
    			+ " values (?, ?, ?, ?, ?, ?)";
	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = connection.prepareStatement(writequery);
	      preparedStmt.setString (1, "Barney");
	      preparedStmt.setString (2, "Rubble");
	      preparedStmt.setDate   (3, startDate);
	      preparedStmt.setBoolean(4, false);
	      preparedStmt.setInt    (5, 5000);
	 
	      // execute the preparedstatement
	      preparedStmt.execute();
        statement = connection.createStatement();
        statement.executeUpdate(update);

    }
    catch (SQLException e) {
        System.out.println("Exception in update method:\n" + e.getMessage());
        return false;
    }

    return true;
} */

public void closeconnect(){

    try {
        connection.close();
        connection = null;
    }
    catch(Exception e)         {
        System.out.println("Problem in closing connection ");
    }
}

}