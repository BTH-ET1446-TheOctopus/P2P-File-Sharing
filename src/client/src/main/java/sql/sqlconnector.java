package sql;

import java.sql.*;

public class sqlconnector {

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

	public sqlconnector(){
		/* sql user,pass,database name, host, port */
		connector("root", "", "clientdb", "localhost", "3306");
	}
	
	public void connector(String login, String password, String db, String host, String port) {
		this.login = login;
		this.password = password;
		this.host = host;
		this.port = port;
		url = "jdbc:mysql://"+host+":"+port+"/"+db+"?autoReconnect=true&useSSL=false";
		connect();
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

	//Function to Create Server DB Once
	public void createserverdb(){
		//Create Table01 servers
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "servers", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = " CREATE TABLE servers ( " +
						" ip varchar(100) NOT NULL, " +
						" name char(20) NOT NULL, " +
						" timestamp timestamp NOT NULL, " +
						" clientcount int NOT NULL, " +
						" servercount int NOT NULL, " +
						" CONSTRAINT Servers_pk PRIMARY KEY (ip))";

				this.Update(createtable);
			}
		}
		catch (Exception e) {
			System.out.println("Exception in query method:\n" + e.getMessage());
		}
		//Create Table02 peersarray
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "peersarray", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE peersarray ( " +
						" uniquefileid varchar(100) NOT NULL, " +    
						" peers varchar(100) NOT NULL, " +
						" CONSTRAINT peersarray_pk PRIMARY KEY (uniquefileid))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			System.out.println("Exception in query method:\n" + e.getMessage());
		}
		//Create Table03 serverfile
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "serverfile", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE serverfile ( " +
						" filename char(40) NOT NULL, " +
						" totalblocks int NOT NULL, " +
						" peers varchar(100) NOT NULL, " +
						" peercount int NOT NULL, " +
						" uniquefileid varchar(100) NOT NULL, " +
						" filechecksum varchar(100) NOT NULL, " +
						" metadatachecksum varchar(100) NOT NULL, " +		
						" CONSTRAINT serverfile_pk PRIMARY KEY (uniquefileid))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			System.out.println("Exception in query method:\n" + e.getMessage());
		}
		//Create Table04 serverpeers
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "serverpeers", null);
			if (tables.next()) {
				// Table exists Don't Create Table
			}
			else {
				//Table Doesn't Exist, Create Table
				String createtable = "CREATE TABLE serverpeers ( " +
						" id int NOT NULL, " +
						" latestIP varchar(100) NOT NULL, " +
						" blacklist binary(1) NOT NULL, " +
						" timestamp timestamp NOT NULL, " +
						" CONSTRAINT serverpeers_pk PRIMARY KEY (id))";
				this.Update(createtable);
			}
		}
		catch (Exception e) {
			System.out.println("Exception in query method:\n" + e.getMessage());
		}
	}

	public void closeconnect(){
		//Close all connection to MySQL
		try { if (set != null) set.close(); set = null; } catch (SQLException e) { e.printStackTrace(); }
		try { if (statement != null) statement.close(); statement = null; } catch (SQLException e) { e.printStackTrace(); }
		try { if (connection != null) connection.close(); connection = null; } catch (SQLException e) { e.printStackTrace(); }

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