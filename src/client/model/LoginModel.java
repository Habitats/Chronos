package client.model;

import chronos.Person;
import client.ClientController;
import events.AuthEvent;

public class LoginModel extends ChronosModel {

	public LoginModel(ClientController controller) {
		super(controller);
	}

	public void login(String username, String password) {
		fireNetworkEvent(new AuthEvent(new Person(username, null), password));
	}
}
