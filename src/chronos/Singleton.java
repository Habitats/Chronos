package chronos;

/**
 * Global variables are stored here
 * http://en.wikipedia.org/wiki/Singleton_pattern
 */
public class Singleton {
	/**
	 * Other constants
	 */
	public final static String APP_NAME = "Chronos - FUCK YEAH";

	/**
	 * Singleton variables
	 */
	private static Singleton instance;

	private boolean logEnabled;
	private int port = 25000;
	private String hostname = "78.91.39.140";
	private String username;
	private boolean networkEnabled = false;
	private boolean loginEnabled = false;
	
	// Current logged in user
	private Person person;


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

	public void enableNetwork() {
		networkEnabled = true;
	}

	public boolean networkEnabled() {
		return networkEnabled;
	}

	public void enableLogin() {
		loginEnabled = true;
	}

	public boolean loginEnabled() {
		return loginEnabled;
	}

	public void setSelf(Person person) {
		this.person = person;
	}
	public Person getSelf(){
		return person;
	}
}
