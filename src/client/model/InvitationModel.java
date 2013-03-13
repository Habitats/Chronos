package client.model;

import client.ClientController;

public class InvitationModel extends ChronosModel {
	public InvitationModel(ClientController controller) {
		super(controller, ChronosType.INVITATION);
	}

	private String eventName;
	private String eventDescription;

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

}
