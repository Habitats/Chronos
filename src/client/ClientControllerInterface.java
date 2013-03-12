package client;

import chronos.Person;
import events.QueryEvent;

public interface ClientControllerInterface {
	
	/**
	 * method to get events for a specified person, the person lies in the event. Updated constructor for QueryEvent
	 * @param event
	 */
	
	public void sendQueryEvent(QueryEvent event);

}
