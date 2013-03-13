package client.model;

import client.ClientController;

public class RoomBookingModel extends ChronosModel {
	String roomNumber;
	
	public RoomBookingModel(ClientController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
