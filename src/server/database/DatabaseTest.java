package server.database;

import java.util.Date;
import java.util.Properties;

import chronos.Singleton;

public class DatabaseTest {

	Properties prop = new Properties();

	public static void main(String[] args) {

		Date test = new Date();

		System.out.println(test);

		new DatabaseTest().run();
	}

	private void run() {
		Singleton.getInstance().enableLog();
		DatabaseConnection db = new DatabaseConnection();
		DatabaseQueries queries = new DatabaseQueries(db);

		db.initialize();

		/**
		 * AddUser
		 */
		// queries.addUser("shitForBrains","derp", "kyrre", 1829000);
		// queries.addUser("childlover","derp", "hallvard", 1829000);
		// queries.addUser("shitForBrains","derp", "patrick", 1829000);

		/**
		 * AddEvent
		 */
		// CalEvent evt = new CalEvent("testevent",new Date(2013, 4, 3), 99999,
		// queries.getUsers().get(0), "Dette er en test event");
		// evt.addParticipant(queries.getUsers().get(1));
		// queries.addEvent(evt);

		/**
		 * GetUsers
		 */
		// ArrayList<Person> userInDb = queries.getUsers();
		// Singleton.log("Users in db:");
		// for (Person person : userInDb) {
		// Singleton.log(person.toString());
		// }

		/**
		 * Add multiple Users
		 */
		// ArrayList<Person> peeps = new ArrayList<Person>();
		// peeps.add(new Person("yolo", "Yolo Swaggins", 133333337));
		// peeps.add(new Person("Kyra", "Kyyra, kom no sa!", new
		// Date().getTime()));
		// queries.addMultipleUsers(peeps);

		/**
		 * Update users
		 */
		// queries.updateUser("yolo", "name", "Patrick Swaggins");

		/**
		 * Get Events
		 */

		// new
		// System.out.println(queries.getUsers().get(0));
		// System.out.println(queries.getParticipantsByEventId(1363110427115L));
		// System.out.println(queries.getEventsByParticipant(queries.getUsers().get(0),
		// true).get(0).getParticipants());

	}
}
