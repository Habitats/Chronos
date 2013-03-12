package events;

import java.util.ArrayList;

/**
 * Event that holds a single array of elements (list of users, list of rooms,
 * list of calendar event etc)
 */
public class QueryEvent extends NetworkEvent {

	public enum QueryType {
		ROOM, PERSON, CALEVENT;
	}

	private QueryType queryType;

	public QueryEvent(EventType type, QueryType queryType) {
		super(type);
		setQueryType(queryType);
	}

	public QueryEvent setResults(ArrayList<?> results) {
		this.results = results;
		return this;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}
}
