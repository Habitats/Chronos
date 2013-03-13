package client.model;

import java.util.Date;

import chronos.Person;
import client.ClientController;
import client.gui.view.EventConfigWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.CalEvent.CalEventType;

public class EventConfigModel extends ChronosModel {
	String eventName, eventDescription;
	Date startTime;
	int duration;
	Person participant, creator;
	Boolean alert;
	String roomNumber;
	private ConfigState state;
	
	public enum ConfigState {
		EDIT, NEW, VIEW;
	}

	public EventConfigModel(ClientController controller) {
		super(controller);
	}

	public void clearModel() {
		
	}

	public void setCalEvent(CalEvent event) {
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
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

	// State of window
	public void setState(ConfigState state) {
		this.state = state;
	}

	public ConfigState getState() {
		return state;
	}

	public void editEvent(){
		setState(ConfigState.EDIT);
	}

	public void applyEvent() {
		CalEvent event = new CalEvent(getEventName(), getStartTime(), getDuration(), getCreator(), getEventDescription()); //setState(CalEventType.UPDATE
		fireNetworkEvent(event);
	}
}
