package client.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import chronos.DateManagement;
import chronos.Person;
import chronos.Room;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.EventConfigWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.CalEvent.CalEventType;

public class EventConfigModel extends ChronosModel {

	private EventConfigWindow eventConfigWindow;

	private ConfigState state;
	private EventConfigWindow view;

	/**
	 * Input variables from view
	 */
	private Room room;
	private String eventDescription;
	private String eventName;
	private Date startDate;
	private int duration;

	private Date startTime;

	public enum ConfigState {
		EDIT, NEW, VIEW;
	}

	public EventConfigModel(ClientController controller) {
		super(controller, ChronosType.EVENT_CONFIG);
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
		return startDate;
	}

	public void setStartTime(Date start) {
		this.startDate = start;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	private boolean validateInput() {
		eventName = view.getEventNameField().getText();
		eventDescription = view.getEventDescriptionArea().getText();
		startDate = DateManagement.getDateFromString(view.getStartDateField().getText());
		startTime = DateManagement.getDateFromString(view.getStartTimeField().getText());
		room = null;
		try {
			room = new Room(null, Integer.parseInt(view.getRoomNumberField().getText()));
		} catch (Exception e) {
		}
		duration = -1;
		try {
			duration = Integer.parseInt(view.getDurationField().getText());
		} catch (Exception e) {
		}
		boolean alert = view.getAlert().isSelected();

		view.getEventNameField().setBackground(eventName == null ? Color.red : Color.green);
		view.getEventDescriptionArea().setBackground(eventDescription == null ? Color.red : Color.green);
		view.getStartDateField().setBackground(startDate == null ? Color.red : Color.green);
		view.getStartTimeField().setBackground(startTime == null ? Color.red : Color.green);
		view.getDurationField().setBackground(duration == -1 ? Color.red : Color.green);
		// ArrayList<Person> participants = view.getParticipantList();

		return eventName != null && eventDescription != null && startDate != null && duration != -1;
	}

	public void newCalEvent() {
		Person user1 = new Person("username1", "user1");
		Person user2 = new Person("username2", "user2");
		Person user3 = new Person("username3", "user3");
		Person creator = new Person("owner", "bob");
		CalEvent event;
		if (validateInput()) {
			event = new CalEvent(getEventName(), getStartTime(), getDuration(), creator, getEventDescription()).setState(CalEventType.NEW).addParticipant(user1, user2, user3);
			fireNetworkEvent(event);
			view.setVisible(false);
		}
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

	public void setDefaultModel() {
		view.getEventNameField().setText("Event name");
		view.getEventNameField().setBackground(Color.white);

		view.getDurationField().setText("");
		view.getDurationField().setBackground(Color.white);

		view.getEventDescriptionArea().setText("Description");
		view.getEventDescriptionArea().setBackground(Color.white);

		view.getStartDateField().setText("DD.MM.YYYY");
		view.getStartDateField().setBackground(Color.white);
		
		view.getStartTimeField().setText("");
		view.getStartTimeField().setBackground(Color.white);

		view.getAlert().setSelected(false);
	}
}
