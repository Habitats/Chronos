package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import chronos.Person;
import chronos.Singleton;


/** 
 * Handles all specific queries
 * THIS IS THE ONLY CLASS WITH SQL IN IT!
 * @author anon
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

	private String processString(String str) {
		str = "'" + str + "'";
		return str;
	}
}
