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

	public Person(String username, String name) {
		this.username = username;
		this.name = name;
	}

	public Person(String string) {
		this(string, null);
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
}
