package client.model;

import java.awt.Color;
import java.util.Date;

import chronos.DateManagement;
import chronos.Person;
import chronos.Room;
import chronos.Singleton;
import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.EventConfigWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.CalEvent.CalEventType;

public class EventConfigModel extends ChronosModel {

	private EventConfigWindow eventConfigWindow;

	private EventConfigWindow view;

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
	private CalEventType state;

	public EventConfigModel(ClientController controller) {
		super(controller, ChronosType.EVENT_CONFIG);
	}

	public EventConfigModel setState(CalEventType state) {
		this.state = state;
		return this;
	}

	public void setCalEvent(CalEvent event) {
		this.event = event;
		view.getEventNameField().setText(event.getTitle());
		view.getEventDescriptionArea().setText(event.getDescription());
		view.getStartDateField().setText(DateManagement.getFormattedDate(event.getStart()));
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

	private boolean validateInput() {
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
		boolean alert = view.getAlert().isSelected();

		view.getEventNameField().setBackground(eventName == null ? Color.red : Color.green);
		view.getEventDescriptionArea().setBackground(eventDescription == null ? Color.red : Color.green);
		view.getStartDateField().setBackground(startDate == null ? Color.red : Color.green);

		return eventName != null && eventDescription != null && startDate != null && duration != -1;
	}

	public void newCalEvent() {
		Person creator = Singleton.getInstance().getSelf();
		creator.setStatus(Person.Status.ACCEPTED);
		CalEvent event;
		if (validateInput()) {
			event = new CalEvent(getEventName(), getStartTime(), getDuration(), creator, getEventDescription()).setState(state);
			fireNetworkEvent(event);
		}
		view.setVisible(false);
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (EventConfigWindow) view;
	}

	public void setDefaultModel() {
		view.getEventNameField().setText("Event name");
		view.getEventNameField().setBackground(Color.white);

		view.getEventDescriptionArea().setText("Description");
		view.getEventDescriptionArea().setBackground(Color.white);

		view.getStartDateField().setText(DateManagement.getFormattedDate(new Date()));
		view.getStartDateField().setBackground(Color.white);

		view.getAlert().setSelected(false);
		
	}

	public void removeEvent() {
		event.setState(CalEventType.DELETE);
		view.setVisible(false);
		fireNetworkEvent(event);
	}
}
