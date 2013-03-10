package server.database;

import java.util.Date;

import chronos.Person;
import events.AuthEvent;
import events.CalEvent;
import events.QueryEvent;

public interface DatabaseControllerInterface {
	/**
	 * Returns a query event with an array of all available rooms in the DB
	 * 
	 * @param event
	 * @return
	 */
	public QueryEvent getAvailableRooms(QueryEvent event);

	/**
	 * Retruns a query event with an array of all users in the DB
	 * 
	 * @param event
	 * @return
	 */
	public QueryEvent getUsers(QueryEvent event);

	/**
	 * Get all calendar events in the DB for a given person
	 * 
	 * @param person
	 */
	public void getCalEvents(Person person);

	/**
	 * Get calendar events added since last login. Acts as a cache.
	 * 
	 * @param person
	 */
	public void getNewCalEvents(Person person);

	/**
	 * adds a new calendar event for the person with a "neutral" (IE. waiting,
	 * person hasn't yet accepted/declined) state
	 * 
	 * @param event
	 * @param person
	 */
	public void addCalEvent(CalEvent event, Person person);

	/**
	 * Updates a calendar event, IE. the state from "accepted" to "declined"
	 * 
	 * @param event
	 * @param person
	 */
	public void updateCalEvent(CalEvent event, Person person);

	/**
	 * Used for login.
	 * 
	 * @return sets accessGranted true/false and returns event
	 */
	public AuthEvent authenticateUser(AuthEvent event);

	/**
	 * @return Date object from when the specified user was last logged in
	 */
	public Date lastLoggedIn(Person person);

}
