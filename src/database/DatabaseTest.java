package database;

import java.util.ArrayList;
import java.util.Properties;

import chronos.Person;
import chronos.Singleton;

public class DatabaseTest {

	Properties prop = new Properties();

	public static void main(String[] args) {

		new DatabaseTest().run();
	}

	private void run() {
		Singleton.getInstance().enableLog();
		DatabaseController db = new DatabaseController();
		DatabaseQueries quries = new DatabaseQueries(db);

		db.initialize();

		String user = "bob";
		quries.addUser(user, null, null);
		quries.updateUser(user, "passord", "nyttpw");
		quries.removeUser(user);

		ArrayList<String[]> users = new ArrayList<String[]>();

		// username, password, name
		String[] user1 = { "h@xxor", "asdklasd", "bob" };
		String[] user2 = { "yoyo", "asdklssdasdld", "carl" };
		String[] user3 = { "sis", "929jsd", "steve" };
		// quries.removeUser("sis");
		// quries.removeUser("yoyo");
		// quries.removeUser("h@xxor");

		// users.add(user1);
		// users.add(user2);
		// users.add(user3);
		//
		// quries.addMultipleUsers(users);

		ArrayList<Person> userInDb = quries.getUsers();
		Singleton.log("Users in db:");
		for (Person person : userInDb) {
			Singleton.log(person.toString());
		}
	}
}
