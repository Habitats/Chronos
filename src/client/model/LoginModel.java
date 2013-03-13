package client.model;

import chronos.Person;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.LoginWindow;
import events.AuthEvent;

public class LoginModel extends ChronosModel {
	private String username;
	private char[] password;
	private LoginWindow view;

	public LoginModel(ClientController controller) {
		super(controller, ChronosType.LOGIN);
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

	@Override
	public void receiveNetworkEvent(events.NetworkEvent event) {
	};

	@Override
	public events.NetworkEvent newNetworkEvent() {
		return null;
	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (LoginWindow) view;
	};
}
