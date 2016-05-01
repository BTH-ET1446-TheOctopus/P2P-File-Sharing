package sql;

import java.sql.*;
import java.util.Properties;

/**
 * @author Farhan
 */

public class sqlconnector {

private static final String dbClassName = "com.mysql.jdbc.Driver";
private static final String CONNECTION ="jdbc:mysql://127.0.0.1/clientdb";	
private Connection  connection  = null;
private Statement   statement   = null;
private ResultSet   set         = null;

String host;
String port;
String login;
String password;
String url;

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

public ResultSet Query(String query){

    try {
        statement = connection.createStatement();
        set = statement.executeQuery(query);
    }
    catch (Exception e) {
        System.out.println("Exception in query method:\n" + e.getMessage());
    }
    return set;
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