package client.model;

import client.ClientController;
import events.NetworkEvent;

abstract public class ChronosModel {
	public final ClientController controller;

	/**
	 * fires a networkEvent from the model, which in turn forwards the event to
	 * the clientController, that sends it to the server
	 * 
	 * @param event
	 *            the event sent to server
	 */
	public void fireNetworkEvent(NetworkEvent event) {
		controller.sendNetworkEvent(event);
	}

	public ChronosModel(ClientController controller) {
		this.controller = controller;
	}

	public ClientController getController() {
		return controller;
	}
}
