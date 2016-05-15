package backend;

import org.glassfish.grizzly.http.server.Request;

public final class Settings {
	public static String bootstrapURL = "http://localhost:9999/";
	public static String ntpServer = "ptbtime1.ptb.de";
	public static int daytimeport = 13;
	
	public static final String MYSQL_HOST = "127.0.0.1";
	public static final String MYSQL_PORT = "3306";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "sql";
	public static final String MYSQL_DATABASE = "serverdb";
	
	/**
	 * Function to compare incoming IP whit the ones that
	 * are in database to see if it is blacklisted
	 * @param request is the incoming rest request from remote client
	 * @return true if ip was blacklisted in db
	 */
	public static boolean blackListedIp(Request request)
	{
		
		
		//se if ip is in db and if it is return true, if the ip 
		//is not in db return false
		//remove comment after fixing this
		
		System.out.println(request.getRemoteAddr());
		return true;
	}
	
	
}
