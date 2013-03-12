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

	public boolean addUser(String username, String name, String surname) {
		username = processString(username);
		try {
			db.execute(String.format("insert into person values (%s,%s,%s)", username, name, surname));
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
			db.execute(String.format("delete from person where brukernavn = %s", username));
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
			db.execute(String.format("update person set %s=%s where brukernavn = %s", fieldToUpdate, newValue, username));
			Singleton.log(String.format("successfully updated %s to %s in %s", fieldToUpdate, newValue, username));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			Singleton.log("error updating: " + username);
			return false;

		}
	}

	public void addMultipleUsers(ArrayList<String[]> users) {
		String insertQuery = "insert into person (brukernavn,passord,navn) values (?,?,?)";
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
		String query = "select brukernavn,navn from person";
		try {
			rs = db.makeSingleQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				String name = rs.getString(1);
				String username = rs.getString(2);
				// String password = rs.getString(3);
				users.add(new Person(username, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void addEvent(CalEvent evt){
		String insertQuery = "insert into avtale (avtaleID,tittel,starttid,varighet,beskrivelse,eier) values (?,?,?,?,?,?);";
		PreparedStatement ps;
		boolean addedAvtale = false;
		try {
			ps = db.makeBatchUpdate(insertQuery);
			try {
				ps.setString(1,""+evt.getTimestamp());
				ps.setString(2,evt.getTitle());
				ps.setString(3,""+evt.getStart().getTime());
				ps.setString(4,""+evt.getDuration());
				ps.setString(5,evt.getDescription());
				ps.setString(6,evt.getCreator().getUsername());
				ps.addBatch();
				Singleton.log("successfully added: " + evt.getTitle() + " with fields " + evt.getStart().getTime()+
						" and " + evt.getDuration() + " and " + evt.getDescription());
			} catch (SQLException e) {
				Singleton.log("error adding: " + evt.getTitle() + " with fields " + evt.getStart().getTime()+
						" and " + evt.getDuration() + " and " + evt.getDescription());
			}
			ps.executeBatch();
			ps.close();
			addedAvtale = true;
		}catch (SQLException e) {
			Singleton.log("error executing: " + evt.getTitle() + " with fields " + evt.getStart().getTime()+
						" and " + evt.getDuration() + " and " + evt.getDescription());
			addedAvtale = false;
		}
		
		/**
		 * If the apointment is added sucessfully to the database, the participants are added and connected.
		 * */
		if (addedAvtale) {
			addParticipants(evt);
		}
	}
	/**
	 * Adds participants from a CalEvent to the DB.
	 * @param evt
	 */
	public void addParticipants(CalEvent evt){
		String insertQuery = "insert into innkallelse (brukernavn,avtaleID,alarm,status) values (?,?,?,?);";
		PreparedStatement ps;
		try {
			ps = db.makeBatchUpdate(insertQuery);
			for (Person p :  evt.getParticipants().values()) {
				try {
					p.setStatus(Person.Status.WAITING);
					ps.setString(1, p.getUsername());
					ps.setString(2, ""+evt.getTimestamp());
					ps.setString(3, null);
					ps.setString(4,""+p.getStatus().ordinal());
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
	
	@SuppressWarnings("deprecation")
	public ArrayList<CalEvent> getEventByParticipant(Person per){
		ArrayList<CalEvent> al = new ArrayList<CalEvent>();
		ResultSet rs;
		String query = "SELECT avtaleID,tittel,starttid,varighet,beskrivelse,brukernavn,navn" +
				"FROM avtale, inkallelse, person" +
				"WHERE avtale.avtaleID = innkallelse.avtaleID AND innkallelse.brukernavn ="+ per.getUsername()+
				"AND person.brukernavn = avlate.eier";
		try {
			rs = db.makeSingleQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				String avtaleID = rs.getString(1);
				String title = rs.getString(2);
				String start = rs.getString(3);
				String duration = rs.getString(4);
				String description = rs.getString(5);
				String username = rs.getString(6);
				String name = rs.getString(7);
				al.add(new CalEvent(title, new Date(start), Integer.parseInt(duration),
						new Person(username, name), description, Integer.parseInt(avtaleID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private String processString(String str) {
		str = "'" + str + "'";
		return str;
	}
}
