package client.model;

import java.util.ArrayList;

import chronos.Room;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.RoomBookingWindow;
import events.NetworkEvent;
import events.QueryEvent;
import events.QueryEvent.QueryType;

public class RoomBookingModel extends ChronosModel {
	String roomNumber;
	private RoomBookingWindow view;
	private ArrayList<Room> rooms;

	public void recieveNetworkEvent(NetworkEvent event) {
		addRooms((QueryEvent) event);
	}
	
	private void addRooms(QueryEvent event) {
		rooms = (ArrayList<Room>)event.getResults();
	}
	
	public RoomBookingModel(ClientController controller) {
		super(controller, ChronosType.ROOM_BOOK);
		// TODO Auto-generated constructor stub
	}

	public String getRoomNumber() {
		return roomNumber;
	}
	
	public void getRooms() {
		fireNetworkEvent(new QueryEvent(QueryType.ROOMS));
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
