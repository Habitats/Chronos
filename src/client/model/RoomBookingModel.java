package client.model;

import client.ClientController;
import events.NetworkEvent;
import events.QueryEvent;

public class RoomBookingModel extends ChronosModel {

	public RoomBookingModel(ClientController controller) {
		super(controller,ChronosType.ROOM_BOOK);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub
		
	}

}
