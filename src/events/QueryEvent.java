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
		ROOMS, PERSONS, CALEVENTS, PARTICIPANTS;
	}

	private QueryType queryType;
	private Person otherPerson;
	private CalEvent calEvent;
	private String argument;

	public QueryEvent(QueryType queryType) {
		this(queryType, Singleton.getInstance().getSelf());
	}

	public QueryEvent(QueryType queryType, Person person) {
		super(EventType.QUERY);
		setQueryType(queryType);
		this.otherPerson = person;
	}

	public QueryEvent(QueryType queryType, String argument) {
		super(EventType.QUERY);
		setQueryType(queryType);
		this.argument = argument;
	}

	public Person getPerson() {
		return otherPerson;
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

	public QueryEvent addCalEvent(CalEvent calEvent) {
		this.calEvent = calEvent;
		return this;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public CalEvent getCalEvent() {
		return calEvent;
	}

	public String getArgument() {
		return argument;
	}

	@Override
	public String toString() {
		return "[QueryEvern] ~";
	}
}
