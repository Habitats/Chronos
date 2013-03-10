package server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import chronos.Person;
import chronos.Singleton;

import events.AuthEvent;
import events.CalEvent;
import events.QueryEvent;

/**
 * Acts as a link between server and DB
 * @author anon
 *
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
		event.setPerson(new Person(event.getUsername(), "bob"));
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
	public void getNewCalEvents(Person person) {
		// TODO Auto-generated method stub

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
}
