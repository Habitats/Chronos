package server.database;

import java.util.Date;
import chronos.Person;
import chronos.Singleton;

import events.AuthEvent;
import events.CalEvent;
import events.QueryEvent;

/**
 * Acts as a link between server and DB
 */
public class DatabaseController implements DatabaseControllerInterface {

	private DatabaseQueries dbQueries;

	public DatabaseController() {
		DatabaseConnection dbConnection = new DatabaseConnection();
		dbConnection.initialize();
		dbQueries = new DatabaseQueries(dbConnection);
	}

	@Override
	public AuthEvent authenticateUser(AuthEvent event) {
		Singleton.log("Authenticating " + event.getUsername());
		if (dbQueries.isUsernameAndPassword(event)) {
			event.setAccessGranted(true);
			event.setSender(dbQueries.getUserByUsername(event.getUsername()));

		}
		return event;
	}

	@Override
	public QueryEvent getUsers(QueryEvent event) {
		if (event.getArgument() == null)
			return event.setResults(dbQueries.getUsers());
		return event.setResults(dbQueries.searchUsers(event.getArgument()));
	}

	@Override
	public void addCalEvent(CalEvent event) {
		dbQueries.addEvent(event);
	}

	@Override
	public void updateCalEvent(CalEvent event) {
		dbQueries.updateCalEvent(event);
	}

	@Override
	public void removeCalEvent(CalEvent event, Person person) {
		dbQueries.removeCalEvent(event);

	}

	@Override
	public QueryEvent getCalEvents(Person person, QueryEvent event) {
		return event.setResults(dbQueries.getEventsByParticipant(person));
	}

	@Override
	public void logout(Person person) {
		dbQueries.setlastLoggedIn(new Date().getTime(), person.getUsername());

	}

	@Override
	public QueryEvent getAvailableRooms(QueryEvent qe) {
		return qe.setResults(dbQueries.getAvailableRooms(qe.getCalEvent()));
	}

}
