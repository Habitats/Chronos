package chronos;

public class Person {
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

	public String toString() {
		return String.format("Username: %s - Name: %s", username, name);
	}
}
