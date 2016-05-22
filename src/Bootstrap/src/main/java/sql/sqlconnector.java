package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import backend.Settings;

public class sqlconnector {
	
	private Connection  connection  = null;

	String host;
	String port;
	String login;
	String password;
	String url;
	
	private static final Logger LOG = Logger.getLogger(sqlconnector.class.getName());

	public sqlconnector(){
		this.connector(Settings.MYSQL_USERNAME, Settings.MYSQL_PASSWORD, Settings.MYSQL_DATABASE, Settings.MYSQL_HOST, Settings.MYSQL_PORT);
	}
	
	//Overloaded Constructor to Open Connection with Specified DB
	public sqlconnector(String dbname){

		this.connector(Settings.MYSQL_USERNAME, Settings.MYSQL_PASSWORD, dbname, Settings.MYSQL_HOST, Settings.MYSQL_PORT);
	}
	
	public void connector(String login, String password, String db, String host, String port) {
		this.login = login;
		this.password = password;
		this.host = host;
		this.port = port;
		
		if (Settings.USE_SQLITE) {
			url = "jdbc:sqlite:" + Settings.MYSQL_DATABASE;
		} else {
			url = "jdbc:mysql://"+host+":"+port+"/"+db+"?autoReconnect=true&useSSL=false";
		}
		
		connect();
	}

	private void connect() {
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException ex) {
			// handle any errors
			LOG.log(Level.SEVERE, ex.toString(), ex);
			
			LOG.log(Level.SEVERE,"SQLException: " + ex.getMessage());
			LOG.log(Level.SEVERE,"SQLState: " + ex.getSQLState());
			LOG.log(Level.SEVERE,"VendorError: " + ex.getErrorCode());
		}
	}

	public ResultSet runquery(String query){
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		
		return resultSet;
	}

	public boolean Update (String update) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
			statement.executeUpdate(update);
		} catch (SQLException e) {
			LOG.log(Level.WARNING, e.toString(), e);
			return false;
		}

		return true;
	}

	//Function to Create Server DB
	//Check if it already exists, then do nothing
	public void createserverdb(){
		
		try {
			String createdb = "create database if not exists serverdb";
			this.Update(createdb);		
			String usedb = "USE serverdb";
			this.runquery(usedb);
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		
		//Close Connection and Connect to Client DB
		this.closeconnect();
		this.connector(Settings.MYSQL_USERNAME, Settings.MYSQL_PASSWORD, "serverdb", Settings.MYSQL_HOST, Settings.MYSQL_PORT);
		
		//Create Table01 bootstrapserver
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "bootstrapserver", null);
			if (tables.next()) {
				// Table exists, Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = " CREATE TABLE bootstrapserver ( " +
						" ip varchar(15) NOT NULL, " +
						" name char(20) NOT NULL, " +
						" timestamp timestamp NOT NULL, " +
						" clientcount int NOT NULL, " +
						" servercount int NOT NULL, " +
						" CONSTRAINT bootstrapserver_pk PRIMARY KEY (ip))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		//Create Table02 peersarray
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "peersarray", null);
			if (tables.next()) {
				// Table exists, Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE peersarray ( " +
						" uniquefileid varchar(100) NOT NULL, " +    
						" peers varchar(15) NOT NULL, " +
						" clientid varchar(100) NOT NULL) ";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		//Create Table03 serverswarm
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "serverswarm", null);
			if (tables.next()) {
				// Table exists, Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE serverswarm ( " +
						" filename char(40) NOT NULL, " +
						" totalblocks int NOT NULL, " +
						" peers varchar(100) NOT NULL, " +
						" peercount int NOT NULL, " +
						" uniquefileid varchar(100) NOT NULL, " +
						" filechecksum varchar(100) NOT NULL, " +
						" metadatachecksum varchar(100) NOT NULL, " +		
						" CONSTRAINT serverswarm_pk PRIMARY KEY (uniquefileid))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
		//Create Table04 serverpeers
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "serverpeers", null);
			if (tables.next()) {
				// Table exists, Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE serverpeers ( " +
						" id varchar(100) NOT NULL, " +
						" latestIP varchar(15) NOT NULL, " +
						" blacklist binary(1) NOT NULL, " +
						" timestamp timestamp NOT NULL, " +
						" CONSTRAINT serverpeers_pk PRIMARY KEY (id))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.WARNING, e.toString(), e);
		}
	}

	public void closeconnect(){
		//Close all connection to MySQL
		try { if (connection != null) connection.close(); connection = null; } catch (SQLException e) { LOG.log(Level.WARNING, e.toString(), e); }

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void Disconnect () {
		connection = null;
	}

}
