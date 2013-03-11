package client.model;

import client.ClientController;
import events.NetworkEvent;

public class CalendarModel extends ChronosModel {

	public CalendarModel(ClientController controller) {
		super(controller);
	}

	@Override
	public void fireNetworkEvent(NetworkEvent event) {
	}

	public void newEventBtnPushed() {
		//super.controller
		
	}
}
