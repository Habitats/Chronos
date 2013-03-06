package chronos;

public class Singleton {
	private static Singleton instance;
	private boolean logEnabled;

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
}
