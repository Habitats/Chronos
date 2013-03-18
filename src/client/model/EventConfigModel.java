package client.model;

import java.awt.Color;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import chronos.DateManagement;
import chronos.Person;
import chronos.Room;
import chronos.Singleton;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.eventConfig.EventWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.CalEvent.CalEventType;

public class EventConfigModel extends ChronosModel {
	public enum ViewType {
		OTHER, ADMIN, PARTICIPANT, NEW, INVITE, UPDATE;
	}

	/**
	 * Input variables from view
	 */
	private Room room;
	private String eventDescription;
	private String eventName;
	private Date startDate;
	private int duration;

	private String startTime;

	private CalEvent event;

	private HashMap<String, Person> participants;
	private HashMap<ViewType, EventWindow> eventViews;

	private String description;

	private String formattedStartDate;

	private boolean alert;

	public EventConfigModel(ClientController controller) {
		super(controller, ChronosType.EVENT_CONFIG);
		eventViews = new HashMap<ViewType, EventWindow>();
	}

	// State of window

	private boolean validateInput(EventWindow view) {
		eventName = view.getEventNameField().getText();
		eventDescription = view.getEventDescriptionArea().getText();
		startDate = DateManagement.getDateFromString(view.getStartDateField().getText());
		// startTime =
		// DateManagement.getDateFromString(view.getStartTimeArray().getText());
		room = null;
		try {
			room = new Room(null, Integer.parseInt(view.getRoomNumberField().getText()), null);
		} catch (Exception e) {
		}
		duration = -1;
		try {
			// duration = Integer.parseInt(view.getDurationField().getText());
			duration = 10;
		} catch (Exception e) {
		}

		view.getEventNameField().setBackground(eventName == null ? Color.red : Color.green);
		view.getEventDescriptionArea().setBackground(eventDescription == null ? Color.red : Color.green);
		view.getStartDateField().setBackground(startDate == null ? Color.red : Color.green);

		return eventName != null && eventDescription != null && startDate != null && duration != -1;
	}

	public void setDefaultModel() {
		setEventName("Event name");
		setDescription("Description");
		setStartDate(DateManagement.getFormattedDate(new Date()));
		setAlert(false);
		setDuration(1);
		setStartTime("12:00");
		setParticipants(new HashMap<String, Person>());
	}

	public void setCalEvent(CalEvent event) {
		this.event = event;
		setEventName(event.getTitle());
		setDescription(event.getDescription());
		setStartDate(DateManagement.getFormattedDate(event.getStart()));
		setAlert(event.getAlert());
		setDuration(event.getDuration());
		setStartTime(DateManagement.getTimeOfDay(event.getStart()));
		setParticipants(event.getParticipants());
	}

	public void updateViews() {
		for (EventWindow view : eventViews.values()) {
			view.getEventNameField().setText(getEventName());
			view.getEventDescriptionArea().setText(getEventDescription());
			view.setParticipants(getParticipants());
			view.getStartDateField().setText(getFormattedStartDate());
			view.getDuration().setSelectedIndex(getDuration());
			view.getStartTimeField().setText(getStartTime());
		}
	}

	public void newCalEvent(EventWindow view) {
		Person creator = Singleton.getInstance().getSelf();
		creator.setStatus(Person.Status.ACCEPTED);
		CalEvent event;
		if (validateInput(view)) {
			event = new CalEvent(getEventName(), getStartDate(), getDuration(), creator, getEventDescription()).setState(view.getViewType()).addParticipant(getParticipants());
			fireNetworkEvent(event);
		}
		view.setVisible(false);
	}

	private Date getStartDate() {
		return DateManagement.getDateFromString(getFormattedStartDate());
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
	}

	public void setView(ChronosWindow view, ViewType type) {
		eventViews.put(type, (EventWindow) view);
	}

	public String getFormattedStartDate() {
		return formattedStartDate;
	}

	public void removeEvent(EventWindow view) {
		event.setState(CalEventType.DELETE);
		view.setVisible(false);
		fireNetworkEvent(event);
	}

	public void setParticipants(HashMap<String, Person> participants) {
		this.participants = participants;
	}

	public void setView(ViewType type) {
		updateViews();
		eventViews.get(type).setVisible(true);
		hideAllBut(type);
	}

	private void hideAllBut(ViewType type) {
		for (EventWindow view : eventViews.values()) {
			if (view.getViewType() != type)
				view.setVisible(false);
		}
	}

	private void setAlert(boolean alert) {
		this.alert = alert;
	}

	private void setStartDate(String formattedStartDate) {
		this.formattedStartDate = formattedStartDate;
	}

	private void setDescription(String description) {
		this.description = description;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String string) {
		this.startTime = string;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public CalEvent getCalEvent() {
		return event;
	}

	public HashMap<ViewType, EventWindow> getEventViews() {
		return eventViews;
	}

	@Override
	public void setView(ChronosWindow view) {
	}

	public HashMap<String, Person> getParticipants() {
		return participants;
	}
}
