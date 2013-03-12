package chronos;

import java.io.Serializable;

/**
 * Holds info about the client. Used for authentication etc.
 * 
 * @author anon
 * 
 */
public class Person implements Serializable {
	public enum Status {
		ACCEPTED, REJECTED, WAITING,
	}

	private static final long serialVersionUID = -1682580791493320360L;

	private String name;
	private String username;
	private Status status;
	private long lastLoggedIn;
	private String password;

	public Person(String username, String name, long lastLoggedIn) {
		this.username = username;
		this.name = name;
		this.lastLoggedIn = lastLoggedIn;
	}

	public Person(String username, String name) {
		this(username, name, 0);
	}

	public Person(String string) {
		this(string, null, -1);
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return String.format("Username: %s - Name: %s", username, name);
	}

	public long getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(long lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
