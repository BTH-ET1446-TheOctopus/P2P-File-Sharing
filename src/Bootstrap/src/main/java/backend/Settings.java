package backend;


import javax.ws.rs.core.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Settings {
	public static String bootstrapURL = "http://localhost:9999/";
	public static String ntpServer = "ptbtime1.ptb.de";
	public static int daytimeport = 13;
	
	public static int BOOTSTRAP_SYNCHRONIZATION_INTERVAL = 60; // seconds
	
	public static final boolean USE_SQLITE = false;
	public static final String MYSQL_HOST = "127.0.0.1";
	public static final String MYSQL_PORT = "3306";
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "sql";
	public static final String MYSQL_DATABASE = "serverdb";
	
	private static final Logger LOG = Logger.getLogger(backend.Settings.class.getName());	
	/**
	 * Function to get the time from NTP-server and formatting it.
	 * @return timestamp
	 */
	public static String getNTP()
	{	
		Socket so = null;
		try {
			so = new Socket(ntpServer, daytimeport);
		} catch (UnknownHostException e) {
			LOG.log(Level.WARNING, e.getMessage(), e);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader (so.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		
		String timestamp = null;
		try {
			timestamp = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		SimpleDateFormat parser = new SimpleDateFormat("d MMMM yyyy HH:mm:ss z", Locale.US);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-LL-dd HH:mm:ss", Locale.US);
		try {
			Date dt = parser.parse(timestamp);
			timestamp = formatter.format(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		LOG.log(Level.INFO, timestamp);
		return timestamp;
	}
	
	
}
