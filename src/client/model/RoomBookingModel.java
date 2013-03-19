package client.model;

import java.util.ArrayList;
import java.util.Date;

import chronos.Person;
import chronos.Room;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.RoomBookingWindow;
import client.gui.view.calendarWindowHelper.RoomCheckBox;
import events.CalEvent;
import events.NetworkEvent;
import events.QueryEvent;
import events.QueryEvent.QueryType;

public class RoomBookingModel extends ChronosModel {
	private String roomNumber;
	private RoomBookingWindow view;
	private ArrayList<RoomCheckBox> rooms;

	public void recieveNetworkEvent(NetworkEvent event) {
		addRooms((QueryEvent) event);
	}
	
	private void addRooms(QueryEvent event) {
		for(Room room : (ArrayList<Room>)event.getResults()){
			rooms.add(view.addRoom(room));
		}
		view.getFrame().pack();
	}
	
	public RoomBookingModel(ClientController controller) {
		super(controller, ChronosType.ROOM_BOOK);
		// TODO Auto-generated constructor stub
	}

//	public String getRoomNumber() {
//		return roomNumber;
//	}
	
	public void getRooms() {
		fireNetworkEvent(new QueryEvent(QueryType.ROOMS).addCalEvent(new CalEvent("", new Date(), 1829, new Person("penis"), "")));
	}

//	public void setRoomNumber(String roomNumber) {
//		this.roomNumber = roomNumber;
//	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (RoomBookingWindow) view;
	}

}
