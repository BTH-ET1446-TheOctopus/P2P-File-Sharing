package sql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Kambiz
 */

public class DBReadWriteClass {
	
		Connection connect = null;
		Statement stmnt = null;
		ResultSet rs = null;
		
	public DBReadWriteClass(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/serverdb?autoReconnect=true&useSSL=false";
			String connectionUser = "root";
			String connectionPassword = "";
			connect = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			System.out.println("Connected to server successfully...\n");
			stmnt = connect.createStatement();
			
			stmnt.executeUpdate("INSERT INTO serverpeers (id, latestIP, blacklist, timestamp, files, filecount) " + 
	                "VALUES (1026, '192.168.31.52', 1, default,'Captain Ameerica Civil War', 0)");
			rs = stmnt.executeQuery("SELECT * FROM serverpeers");
			while (rs.next()) {
				String id = rs.getString("id");
				String latestIP = rs.getString("latestIP");
				String blackList = rs.getString("blacklist");
				String timestamp = rs.getString("timestamp");
				String files = rs.getString("files");
				String filecount = rs.getString("filecount");
				System.out.println("ID: " + id + 
						"\nLastestIP: " + latestIP + 
						"\nBlacklist: " + blackList +
						"\nTimestamp: " + timestamp +
						"\nFiles: " + files +
						"\nFilecount: " + filecount
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmnt != null) stmnt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (connect != null) connect.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
