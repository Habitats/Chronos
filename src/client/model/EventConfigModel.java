package client.model;

import chronos.Person;
import client.ClientController;

public class EventConfigModel extends ChronosModel{
	String eventName, eventDescription;
	Person participant;
	Boolean alert;
	String roomNumber;

	public EventConfigModel(ClientController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	public void clearModel() {
		// TODO Auto-generated method stub
		
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Person getParticipant() {
		return participant;
	}

	public void setParticipant(Person participant) {
		this.participant = participant;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
