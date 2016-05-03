package sql;

import java.sql.*;
import java.util.Properties;

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
int filesize=0;
String filetype = "";
String peers = "";
int peercount = 0;
int uniquefileid = 0;

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