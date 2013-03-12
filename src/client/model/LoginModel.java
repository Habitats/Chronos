package client.model;

import chronos.Person;
import client.ClientController;
import events.AuthEvent;

public class LoginModel extends ChronosModel {
	String username;
	char[] password;
	
	public LoginModel(ClientController controller) {
		super(controller);
	}

	public void login(String username, String password) {
		fireNetworkEvent(new AuthEvent(new Person(username, null), password));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
}
