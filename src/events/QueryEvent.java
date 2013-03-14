package events;

import java.util.ArrayList;
import java.util.Collections;

import chronos.Person;
import chronos.Singleton;

/**
 * Event that holds a single array of elements (list of users, list of rooms,
 * list of calendar event etc)
 */
public class QueryEvent extends NetworkEvent {

	public enum QueryType {
		ROOM, PERSON, CALEVENT_CONFIRMED, CALEVENT_NEW;
	}

	private QueryType queryType;
	private Person person;

	public QueryEvent(QueryType queryType) {
		this(queryType, Singleton.getInstance().getSelf());
	}

	public QueryEvent(QueryType queryType, Person person) {
		super(EventType.QUERY);
		setQueryType(queryType);
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public QueryEvent setResults(ArrayList<Comparable> results) {
		this.results = results;
		return this;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public ArrayList<?> getResults() {
		return results;
	}

	public ArrayList<Comparable> getSortedResults() {
		Collections.sort(results);
		return results;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	@Override
	public String toString() {
		return "[QueryEvern] ~";
	}
}
