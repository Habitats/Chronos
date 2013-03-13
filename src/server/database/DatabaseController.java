package server.database;

import java.util.Date;
import chronos.Person;
import chronos.Singleton;

import events.AuthEvent;
import events.CalEvent;
import events.NetworkEvent.EventType;
import events.QueryEvent.QueryType;
import events.QueryEvent;

/**
 * Acts as a link between server and DB
 */
public class DatabaseController implements DatabaseControllerInterface {

	private DatabaseQueries dbQueries;

	public DatabaseController() {
		DatabaseConnection dbConnection = new DatabaseConnection();
		dbQueries = new DatabaseQueries(dbConnection);
	}

	@Override
	public AuthEvent authenticateUser(AuthEvent event) {
		Singleton.log("Authenticating " + event.getUsername());
		// event.setPerson(new Person(event.getUsername(), "bob", -1));
		event.setAccessGranted(true);
		return event;
	}

	@Override
	public QueryEvent getAvailableRooms(QueryEvent event) {
		return null;
	}

	@Override
	public QueryEvent getUsers(QueryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryEvent getCalEvents(Person person) {
		return null;
	}

	@Override
	public void addCalEvent(CalEvent event, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCalEvent(CalEvent event, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date lastLoggedIn(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCalEvent(CalEvent event, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public QueryEvent getCalEventsFromTimeSlot(Person person, int year, int week) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryEvent getNewCalEvents(Person person) {
		return new QueryEvent(EventType.QUERY, QueryType.CALEVENT).setResults(dbQueries.getEventsByParticipant(person, true));
	}

	@Override
	public QueryEvent getOldEvents(Person person) {
		return new QueryEvent(EventType.QUERY, QueryType.CALEVENT).setResults(dbQueries.getEventsByParticipant(person, true));
	}
}
