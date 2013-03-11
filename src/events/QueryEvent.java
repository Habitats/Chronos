package events;

import java.util.ArrayList;

/**
 * Event that holds a single array of elements (list of users, list of rooms,
 * list of calendar event etc)
 */
public class QueryEvent extends NetworkEvent {

	public QueryEvent(EventType type) {
		super(type);
	}

	public void setResults(ArrayList<?> results) {
		this.results = results;
	}
}
