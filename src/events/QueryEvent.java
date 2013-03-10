package events;

import java.util.ArrayList;

public class QueryEvent extends NetworkEvent {

	public QueryEvent(EventType type) {
		super(type);
	}

	public void setResults(ArrayList<?> results) {
		this.results = results;
	}

}
