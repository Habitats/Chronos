package client.model;

import chronos.Person;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.LoginWindow;
import events.AuthEvent;
import events.NetworkEvent;

public class LoginModel extends ChronosModel {
	private String username;
	private char[] password;
	private LoginWindow view;

	public LoginModel(ClientController controller) {
		super(controller, ChronosType.LOGIN);
	}

	public void login(String username, char[] cs) {
		fireNetworkEvent(new AuthEvent(new Person(username), cs.toString()));
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
	public void setView(ChronosWindow view) {
		this.view = (LoginWindow) view;
	}

}
