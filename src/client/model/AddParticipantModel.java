package client.model;

import client.ClientController;
import client.model.ChronosModel.ChronosType;
import events.NetworkEvent;


public class AddParticipantModel extends ChronosModel {

	public AddParticipantModel(ClientController controller) {
		super(controller, ChronosType.USER_LIST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NetworkEvent newNetworkEvent() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
