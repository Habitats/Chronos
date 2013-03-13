package client.model;

import java.util.Date;

import chronos.Person;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.EventConfigWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.CalEvent.CalEventType;

public class EventConfigModel extends ChronosModel {

	private String eventName, eventDescription;
	private Person participant, creator;
	private Boolean alert;
	private String roomNumber;
	private EventConfigWindow eventConfigWindow;

	private ConfigState state;
	private Date start;
	private int duration;
	private EventConfigWindow view;

	public enum ConfigState {
		EDIT, NEW, VIEW;
	}

	public EventConfigModel(ClientController controller) {
		super(controller, ChronosType.EVENT_CONFIG);
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
		return start;
	}

	public void setStartTime(Date start) {
		this.start = start;
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

	public void editEvent() {
		setState(ConfigState.EDIT);
	}

	public void newCalEvent() {
		Person user1 = new Person("username1", "user1");
		Person user2 = new Person("username2", "user2");
		Person user3 = new Person("username3", "user3");
		Person creator = new Person("owner", "bob");
		CalEvent event = new CalEvent(getEventName(), getStartTime(), getDuration(), creator, getEventDescription()).setState(CalEventType.NEW).addParticipant(user1, user2, user3);
		fireNetworkEvent(event);
	}

	public Date getStart() {

		return start;
	}

	public void setStart(Date start) {
		this.start = start;
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

	@Override
	public void setView(ChronosWindow view) {
		this.view = (EventConfigWindow) view;
	}
}
