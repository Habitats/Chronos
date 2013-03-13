package client;

import chronos.Person;
import events.NetworkEvent;
import events.QueryEvent;

public interface ClientControllerInterface {


	/**
	 * Used by models to communicate with the controller
	 */	
	public void sendNetworkEvent(NetworkEvent event);

}
