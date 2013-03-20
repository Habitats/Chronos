package client.model;

import chronos.Person;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.LoginWindow;
import events.AuthEvent;
import events.NetworkEvent.EventType;

public class LoginModel extends ChronosModel {
	private LoginWindow view;

	public LoginModel(ClientController controller) {
		super(controller, ChronosType.LOGIN);
	}

	public void login(String username, String password) {
		AuthEvent authEvent = new AuthEvent(EventType.LOGIN, new Person(username), password);
		if (getController().getAuthEvent() != null)
			authEvent = getController().getAuthEvent();
		fireNetworkEvent(authEvent);
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
