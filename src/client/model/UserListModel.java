package client.model;

import client.ClientController;
import events.NetworkEvent;
import events.QueryEvent;

public class UserListModel extends ChronosModel {

	public UserListModel(ClientController controller, ChronosType chronosType) {
		super(controller, ChronosType.USER_LIST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub
		
	}


}
