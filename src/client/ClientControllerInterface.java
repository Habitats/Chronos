package client;

import events.NetworkEvent;

public interface ClientControllerInterface {

	/**
	 * Used by models to communicate with the controller
	 */
	public void sendNetworkEvent(NetworkEvent event);

}
