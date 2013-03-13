package client.model;

import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.RoomBookingWindow;
import events.NetworkEvent;

public class RoomBookingModel extends ChronosModel {
	String roomNumber;
	private RoomBookingWindow view;
	
	public RoomBookingModel(ClientController controller) {
		super(controller, ChronosType.ROOM_BOOK);
		// TODO Auto-generated constructor stub
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub
	}



	@Override
	public void setView(ChronosWindow view) {
		this.view = (RoomBookingWindow) view;
	}

}
