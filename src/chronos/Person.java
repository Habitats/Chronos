package chronos;

import java.io.Serializable;

/**
 * Holds info about the client. Used for authentication etc.
 * @author anon
 *
 */
public class Person implements Serializable{
	private static final long serialVersionUID = -1682580791493320360L;
	
	String name;
	String username;

	public Person(String username, String name) {
		this.username = username;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return String.format("Username: %s - Name: %s", username, name);
	}
}
