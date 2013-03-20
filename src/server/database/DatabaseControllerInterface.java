package server.database;

import chronos.Person;
import events.AuthEvent;
import events.CalEvent;
import events.QueryEvent;

public interface DatabaseControllerInterface {

	/**
	 * Returns a query event with an array of all users in the DB
	 */
	public QueryEvent getUsers(QueryEvent event);

	/**
	 * Get events the user already accepted and viewed
	 */
	public QueryEvent getCalEvents(Person person, QueryEvent event);

	/**
	 * adds a new calendar event for the person with a "neutral" (IE. waiting,
	 * person hasn't yet accepted/declined) state
	 */
	public void addCalEvent(CalEvent event);

	/**
	 * Updates a calendar event, IE. the state from "accepted" to "declined"
	 */
	public void updateCalEvent(CalEvent event);

	/**
	 * Removes a calendar event for the specified person
	 */
	public void removeCalEvent(CalEvent event, Person person);

	/**
	 * Used for login.
	 * 
	 * @return sets accessGranted true/false and returns event
	 */
	public AuthEvent authenticateUser(AuthEvent event);

	/**
	 * Logout the specified user and set the timestamp for lastLoggedIn
	 */
	public void logout(Person person);

	/**
	 * Get all available rooms in the time interval of the event
	 */
	public QueryEvent getAvailableRooms(QueryEvent qe);

}
