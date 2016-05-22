package backend;

public final class Settings {
	public static final boolean ENABLE_HTTPS = true;

	public static String DEFAULT_BOOTSTRAP_ADDRESS = "localhost";
	public static final String BOOTSTRAP_PORT = "9999";

	public static final String DEFAULT_CLIENT_ADDRESS = "localhost";
	public static final String CLIENT_PORT = "1337";

	public static final int BOOTSTRAP_HELLO_INTERVAL = 2 * 60; // seconds
	public static final int BOOTSTRAP_DATA_RETRIVAL_INTERVAL = 3 * 60; // seconds
	public static final int GUI_UPDATE_INTERVAL = 1000; // milliseconds

	public static final String MYSQL_DATABASE = "clientdb";
}
