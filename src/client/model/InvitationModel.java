package client.model;

import client.ClientController;
import client.gui.view.ChronosWindow;
import client.gui.view.eventConfig.EventWindowInvite;
import events.CalEvent;

public class InvitationModel extends ChronosModel {
	public InvitationModel(ClientController controller) {
		super(controller, ChronosType.INVITATION);
	}

	private String eventName;
	private String eventDescription;
	private EventWindowInvite view;

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	@Override
	public void receiveNetworkEvent(events.NetworkEvent event) {
	};

	@Override
	public void setView(ChronosWindow view) {
		this.view = (EventWindowInvite) view;
	}

	public void setCalEvent(CalEvent calEvent) {
		// view.setEventName(calEvent.getTitle());
		// view.setEventDesctiption(calEvent.getDescription());
		// view.setStart(calEvent.getStart());
		// view.setSender(calEvent.getSender());
	};
}
