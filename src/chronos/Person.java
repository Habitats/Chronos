package chronos;

import java.io.Serializable;

/**
 * Holds info about the client. Used for authentication etc.
 * 
 * @author anon
 * 
 */
public class Person implements Serializable, Comparable<Person> {
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

	public Person(String username) {
		this(username, null);
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

	@Override
	public int compareTo(Person o) {
		return getName().compareTo(o.getName());
	}
}
