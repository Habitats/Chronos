package events;

import chronos.Person;

public class AuthEvent extends NetworkEvent {
	private Person person;
	private final String username;
	private final String password;

	public AuthEvent(String username, String password) {
		super(EventType.LOGIN);
		this.username = username;
		this.password = password;
	}

	public String toString() {
		return "[AuthEvent] Username: " + username + " - Password: " + password;
	}
}
