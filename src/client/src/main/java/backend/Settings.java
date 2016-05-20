package backend;

public final class Settings {

	public static final String BOOTSTRAP_URL = "http://localhost:9999/rest/";
	public static final String DEFAULT_CLIENT_ADDRESS = "0.0.0.0";
	public static final String CLIENT_PORT = "1337";

	public static final int BOOTSTRAP_HELLO_INTERVAL = 2 * 60; // seconds
	public static final int BOOTSTRAP_DATA_RETRIVAL_INTERVAL = 5 * 60; // seconds
	public static final int GUI_UPDATE_INTERVAL = 1000; // milliseconds

	public static final String MYSQL_DATABASE = "clientdb";
}
