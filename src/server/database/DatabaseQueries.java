package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import chronos.Person;
import chronos.Singleton;
import events.CalEvent;

/**
 * Handles all specific queries THIS IS THE ONLY CLASS WITH SQL IN IT!
 * 
 */
public class DatabaseQueries {

	private final DatabaseConnection db;

	public DatabaseQueries(DatabaseConnection db) {
		this.db = db;
	}

	public boolean addUser(String username, String password, String name, long LastLoggedIn) {
		username = processString(username);
		try {
			db.execute(String.format("insert into person values (%s,%s,%s,%s)", username, ("MD5(" + processString(password) + ")"), processString(name), LastLoggedIn));
			Singleton.log("successfully added: " + username);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			Singleton.log("error adding: " + username);
			return false;
		}
	}

	public boolean removeUser(String username) {
		username = processString(username);
		try {
			db.execute(String.format("delete from person where username = %s", username));
			Singleton.log("successfully deleted: " + username);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			Singleton.log("error deleting: " + username);
			return false;
		}
	}

	public boolean updateUser(String username, String fieldToUpdate, String newValue) {
		username = processString(username);
		newValue = processString(newValue);
		try {
			db.execute(String.format("update person set %s=%s where username = %s", fieldToUpdate, newValue, username));
			Singleton.log(String.format("successfully updated %s to %s in %s", fieldToUpdate, newValue, username));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			Singleton.log("error updating: " + username);
			return false;

		}
	}

	public void addMultipleUsers(ArrayList<String[]> users) {
		String insertQuery = "insert into person (username,password,name) values (?,?,?)";
		PreparedStatement ps;
		try {
			ps = db.makeBatchUpdate(insertQuery);

			for (String[] user : users) {
				try {
					ps.setString(1, user[0]);
					ps.setString(2, user[1]);
					ps.setString(3, user[2]);
					ps.addBatch();
					Singleton.log("successfully added: " + user[0] + " with fields " + user[1] + " and " + user[2]);
				} catch (SQLException e) {
					Singleton.log("error adding: " + user[0] + " with fields " + user[1] + " and " + user[2]);
				}
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Person> getUsers() {
		ArrayList<Person> users = new ArrayList<Person>();
		ResultSet rs;
		String query = "select username,name,lastLoggedIn from person";
		try {
			rs = db.makeSingleQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				String name = rs.getString(1);
				String username = rs.getString(2);
				String lastLoggedIn = rs.getString(3);
				users.add(new Person(username, name, Long.parseLong(lastLoggedIn)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void addEvent(CalEvent evt) {
		String insertQuery = "insert into avtale (event_ID,title,startTime,duration,description,owner) values (?,?,?,?,?,?);";
		PreparedStatement ps;
		boolean addedAvtale = false;
		try {
			ps = db.makeBatchUpdate(insertQuery);
			try {
				ps.setString(1, "" + evt.getTimestamp());
				ps.setString(2, evt.getTitle());
				ps.setString(3, "" + evt.getStart().getTime());
				ps.setString(4, "" + evt.getDuration());
				ps.setString(5, evt.getDescription());
				ps.setString(6, evt.getCreator().getUsername());
				ps.addBatch();
				Singleton.log("successfully added: " + evt.getTitle() + " with fields " + evt.getStart().getTime() + " and " + evt.getDuration() + " and " + evt.getDescription());
			} catch (SQLException e) {
				Singleton.log("error adding: " + evt.getTitle() + " with fields " + evt.getStart().getTime() + " and " + evt.getDuration() + " and " + evt.getDescription());
			}
			ps.executeBatch();
			ps.close();
			addedAvtale = true;
		} catch (SQLException e) {
			Singleton.log("error executing: " + evt.getTitle() + " with fields " + evt.getStart().getTime() + " and " + evt.getDuration() + " and " + evt.getDescription());
			addedAvtale = false;
		}

		/**
		 * If the apointment is added sucessfully to the database, the
		 * participants are added and connected.
		 * */
		if (addedAvtale) {
			addParticipants(evt);
		}
	}

	/**
	 * Adds participants from a CalEvent to the DB.
	 * 
	 * @param evt
	 */
	public void addParticipants(CalEvent evt) {
		String insertQuery = "insert into participants (username,event_ID,alarm,status) values (?,?,?,?);";
		PreparedStatement ps;
		try {
			ps = db.makeBatchUpdate(insertQuery);
			for (Person p : evt.getParticipants().values()) {
				try {
					p.setStatus(Person.Status.WAITING);
					ps.setString(1, p.getUsername());
					ps.setString(2, "" + evt.getTimestamp());
					ps.setString(3, null);
					ps.setString(4, "" + p.getStatus().ordinal());
					ps.addBatch();
					Singleton.log("successfully added participant: " + p.getUsername());
				} catch (SQLException e) {
					Singleton.log("error participant: " + p.getUsername());
					e.printStackTrace();
				}
			}
			ps.executeBatch();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Returns an arraylist of all events the person is a participant of, either
	 * before or after last login
	 * 
	 * @param per
	 * @param afterLastLogin
	 * @return ArrayList<CalEvent>
	 */
	public ArrayList<CalEvent> getEventByParticipant(Person per, boolean afterLastLogin) {
		ArrayList<CalEvent> al = new ArrayList<CalEvent>();
		ResultSet rs;
		String param;
		if (afterLastLogin) {
			param = "<";
		} else {
			param = ">";
		}
		String query = "SELECT event_ID,title,startTime,duration,description,username,name,lastLoggedIn" + "FROM events, Participants, Person" + "WHERE Events.event_ID = participants.event_ID AND participants.username =" + per.getUsername() + "AND person.username = events.owner AND"
				+ per.getLastLoggedIn() + param + "event_ID";
		try {
			rs = db.makeSingleQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				long event_id = rs.getLong(1);
				String title = rs.getString(2);
				long start = rs.getLong(3);
				int duration = rs.getInt(4);
				String description = rs.getString(5);
				String username = rs.getString(6);
				String name = rs.getString(7);
				long lastLoggedIn = rs.getLong(8);
				CalEvent evt = new CalEvent(title, new Date(start), duration, new Person(username, name, lastLoggedIn), description, event_id);
				evt.setParticipants(getParticipantsByEventId(event_id));
				al.add(evt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;

	}

	private HashMap<String, Person> getParticipantsByEventId(long id) {
		HashMap<String, Person> participants = new HashMap<String, Person>();
		ResultSet rs;
		String query = "SELECT username,name,lastLoggedIn" + "FROM person, participants" + "WHERE participants.event_ID =" + id + "AND participants.username = person.username";
		try {
			rs = db.makeSingleQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				String username = rs.getString(1);
				String name = rs.getString(2);
				long lastLoggedIn = rs.getLong(3);
				participants.put(username, new Person(username, name, lastLoggedIn));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participants;
	}

	private String processString(String str) {
		str = "'" + str + "'";
		return str;
	}
}
