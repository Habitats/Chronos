package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.mysql.jdbc.DatabaseMetaData;

import chronos.DateManagement;
import chronos.Person;
import chronos.Room;

/**
 * Event that holds a single array of elements (list of users, list of rooms,
 * list of calendar event etc)
 */
public class QueryEvent extends NetworkEvent {

	public enum QueryType {
		ROOM, PERSON, CALEVENT;
	}

	private QueryType queryType;
	private Person person;

	public QueryEvent(EventType type, QueryType queryType) {
		super(type);
		setQueryType(queryType);
	}

	public QueryEvent(EventType type, Person person) {
		super(type);
		setQueryType(QueryType.CALEVENT);
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
}
