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
		DatabaseQueries queries = new DatabaseQueries(db);

		db.initialize();

		String user = "bob";
		queries.addUser(user, null, null);
		queries.updateUser(user, "passord", "nyttpw");
		queries.removeUser(user);

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

		ArrayList<Person> userInDb = queries.getUsers();
		Singleton.log("Users in db:");
		for (Person person : userInDb) {
			Singleton.log(person.toString());
		}
	}
}
