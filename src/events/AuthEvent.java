package events;

import chronos.Person;

/**
 * Event used to authenitcate the user/client with the server/db
 */
public class AuthEvent extends NetworkEvent {

	private final String username;
	private final String password;
	private boolean accessGranted = false;

	public AuthEvent(EventType type, Person sender, String password) {
		super(type);
		super.sender = sender;
		this.username = sender.getUsername();
		this.password = password;
	}

	@Override
	public String toString() {
		return "[AuthEvent] Username: " + username + " - Password: " + password + (accessGranted ? " - Acessgranted!" : "");
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}

	public boolean getAccessGranted() {
		return accessGranted;
	}

	public Person getPerson() {
		return sender;
	}
}
