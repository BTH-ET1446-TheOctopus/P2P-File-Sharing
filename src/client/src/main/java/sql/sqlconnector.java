package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import backend.Settings;


public class sqlconnector {
	
	private Connection  connection  = null;
	private Statement   statement   = null;
	private ResultSet   set         = null;

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
			statement = connection.createStatement();
		}

		catch (SQLException ex) {
			// handle any errors
			LOG.log(Level.INFO,"SQLException: " + ex.getMessage());
			LOG.log(Level.INFO,"SQLState: " + ex.getSQLState());
			LOG.log(Level.INFO,"VendorError: " + ex.getErrorCode());
		}
	}

	public ResultSet runquery(String query){

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(query);
		}
		catch (Exception e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
		}
		return set;
	}

	public boolean Update (String update) {

		try {
			statement = connection.createStatement();
			statement.executeUpdate(update);

		}
		catch (SQLException e) {
			LOG.log(Level.INFO, "Exception in query method:\n" + e.getMessage());
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
							+ " id int NOT NULL, "		
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
		//Close all connection to MySQL
		try { if (set != null) set.close(); set = null; } catch (SQLException e) { LOG.log(Level.INFO, e.getMessage(), e); }
		try { if (statement != null) statement.close(); statement = null; } catch (SQLException e) { LOG.log(Level.INFO, e.getMessage(), e); }
		try { if (connection != null) connection.close(); connection = null; } catch (SQLException e) { LOG.log(Level.INFO, e.getMessage(), e); }

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getSet() {
		return set;
	}

	public void setSet(ResultSet set) {
		this.set = set;
	}

	public void Disconnect () {
		connection = null;
	}

}