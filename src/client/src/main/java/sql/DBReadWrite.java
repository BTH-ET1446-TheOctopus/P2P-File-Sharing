package sql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Kambiz
 */

public class DBReadWrite {
	
		Connection connect = null;
		Statement stmnt = null;
		ResultSet rs = null;
		
	public DBReadWrite(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/serverdb?autoReconnect=true&useSSL=false";
			String connectionUser = "root";
			String connectionPassword = "";
			connect = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			System.out.println("Connected to server successfully...\n");
			stmnt = connect.createStatement();
			rs = stmnt.executeQuery("SELECT * FROM serverpeers");
			while (rs.next()) {
				String id = rs.getString("id");
				String latestIP = rs.getString("latestIP");
				String blackList = rs.getString("blacklist");
				System.out.println("File ID: " + id + "\nLastest IP is: " + latestIP + "\nBlacklist IPs are: " + blackList );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmnt != null) stmnt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (connect != null) connect.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
