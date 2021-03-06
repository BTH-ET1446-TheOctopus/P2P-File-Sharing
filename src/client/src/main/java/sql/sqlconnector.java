package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sqlconnector {
	
	private Connection  connection  = null;

	String host;
	String port;
	String login;
	String password;
	String url;
	
	private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public sqlconnector(){

	}

	//Overloaded Constructor to Open Connection with Specified DB
	public sqlconnector(String dbname){

		this.connector(dbname);
	}

	
	public void connector(String db) {
		url = "jdbc:sqlite:"+db;
		connect();
	}

	private void connect() {
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException ex) {
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}
	}

	public ResultSet runquery(String query){
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
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

	//Function to Create Client DB
	//Check if it already exists, then do nothing
	public void createclientdb(){
		//Create Client DB
		try {
			this.connector("clientdb");
		}
		catch (Exception e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
		}
		//Create Table01 peersarray
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "peersarray", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE peersarray ( "
							+ " id int , "		
							+ " uniquefileid varchar(100) NOT NULL, "    
							+ " peers varchar(15) NOT NULL, "
							+ " CONSTRAINT peersarray_pk PRIMARY KEY (id))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
		}
		//Create Table02 clientswarm
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "clientswarm", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE clientswarm ("
							+ " filename char(40) NOT NULL, "
							+ " totalblocks int NOT NULL, "
							+ " peers varchar(15) NOT NULL, "
							+ " peercount int NOT NULL, "
							+ " uniquefileid varchar(100) NOT NULL, "
							+ " filechecksum varchar(100) NOT NULL, "
							+ " metadatachecksum varchar(100) NOT NULL, "		
							+ " CONSTRAINT clientswarm_pk PRIMARY KEY (uniquefileid))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
		}
		//Create Table03 clientpeers
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "clientpeers", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE clientpeers ( "
							+ " id varchar(100) NOT NULL, "
							+ " latestip varchar(15) NOT NULL, "
							+ " blacklist binary(1) NOT NULL, "
							+ " timestamp timestamp NOT NULL, "
							+ " files varchar(100) NOT NULL, "
							+ " filecount int NOT NULL, "
							+ " CONSTRAINT clientpeers_pk PRIMARY KEY (id))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
		}
	}


	public void closeconnect(){
		try { if (connection != null) connection.close(); connection = null; } catch (SQLException e) { LOG.log(Level.INFO, e.getMessage(), e); }

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