package client.model;

import client.ClientController;
import client.gui.view.AddParticipantWindow;
import client.gui.view.ChronosWindow;
import events.NetworkEvent;

public class AddParticipantModel extends ChronosModel {

	private AddParticipantWindow view;

	public AddParticipantModel(ClientController controller) {
		super(controller, ChronosType.USER_LIST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (AddParticipantWindow) view;
	}
}
