package events;

import chronos.Person;

/**
 * Event used to authenitcate the user/client with the server/db
 * 
 * @author anon
 * 
 */
public class AuthEvent extends NetworkEvent {
	private final String username;
	private final String password;
	private boolean accessGranted = false;

	public AuthEvent(Person person, String password) {
		super(EventType.LOGIN);
		super.person = person;
		this.username = person.getUsername();
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
}
