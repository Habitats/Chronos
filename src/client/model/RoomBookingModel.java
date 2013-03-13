package client.model;

import client.ClientController;
import events.NetworkEvent;

public class RoomBookingModel extends ChronosModel {

	public RoomBookingModel(ClientController controller) {
		super(controller, ChronosType.ROOM_BOOK);
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
