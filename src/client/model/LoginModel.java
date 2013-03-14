package client.model;

import chronos.Person;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.LoginWindow;
import events.AuthEvent;
import events.NetworkEvent.EventType;

public class LoginModel extends ChronosModel {
	private String username;
	private String password;
	private LoginWindow view;

	public LoginModel(ClientController controller) {
		super(controller, ChronosType.LOGIN);
	}

	public void login(String username, String password) {
		fireNetworkEvent(new AuthEvent(EventType.LOGIN, new Person(username), password));
	}

	public void setDenied() {
		view.getStatusLabel().setText("Access denied!");
	}

	@Override
	public void receiveNetworkEvent(events.NetworkEvent event) {
	};

	@Override
	public void setView(ChronosWindow view) {
		this.view = (LoginWindow) view;
	}

}
