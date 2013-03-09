package chronos;

/**
 * Global variables are stored here
 * http://en.wikipedia.org/wiki/Singleton_pattern
 * 
 * @author anon
 * 
 */
public class Singleton {
	private static Singleton instance;
	private boolean logEnabled;
	private int port = 25000;
	private String hostname = "localhost";
	private String username;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	public static void log(String str) {
		if (Singleton.getInstance().logEnabled())
			System.out.println(str);
	}

	public void enableLog() {
		this.logEnabled = true;
	}

	public void disableLog() {
		this.logEnabled = false;
	}

	public boolean logEnabled() {
		return logEnabled;
	}

	public int getPort() {
		return port;
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsersname(String username) {
		this.username = username;
	}
}
