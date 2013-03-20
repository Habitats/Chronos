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
		// queries.addUser("sexyboy","derp", "kyrre", 1829000);
		// queries.addUser("childlover","derp", "hallvard", 1829000);
		// queries.addUser("shitForBrains","derp", "patrick", 1829000);
		// queries.addUser("bieberfever","derp", "emilie", 1829000);
		// queries.addUser("hornyGirl96","derp", "Jens", 1829000);
		// queries.addUser("boytoy","derp", "Jostein", 1829000);

		/**
		 * Authenticate user
		 */
		// System.out.println(queries.isUsernameAndPassword(new
		// AuthEvent(EventType.LOGIN ,(Person)queries.getUsers().get(0),
		// "derp")));
		// System.out.println(queries.isUsernameAndPassword(new
		// AuthEvent(queries.getUsers().get(0), "derp")));
		// queries.setTimestampOfUser(-1,
		// queries.getUserByUsername("sexyboy").getUsername());

		/**
		 * AddEvent
		 */
		// CalEvent evt = new CalEvent("testeventet",new Date(2013, 4, 3),
		// 99999,
		// (Person) queries.getUsers().get(0), "Dette er en test event, dawg!");
		// evt.addParticipant((Person)queries.getUsers().get(1));
		// evt.addParticipant(queries.getUsers().get(2));
		// queries.addEvent(evt);
		//
		// CalEvent evt2 = new CalEvent("testevent4",new Date(2013, 4, 3),
		// 2829000,
		// (Person)queries.getUsers().get(1), "Dette er en test event",
		// 1363611650384L);
		// evt2.getCreator().setStatus(2);
		// queries.addEvent(evt2);
		// System.out.println(queries.makePrimaryUnique(1363611650384L, 0));

		/**
		 * GetUsers
		 */

		// ArrayList<Person> userInDb = queries.getUsers();
		// Singleton.log("Users in db:");
		// for (Person person : userInDb) {
		// Singleton.log(person.toString());

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

		// System.out.println(queries.getUsers().get(0));
		// System.out.println(queries.getParticipantsByEventId(1363184812475L));
		// System.out.println(((CalEvent)
		// queries.getEventsByParticipant((Person)
		// queries.getUsers().get(0)).get(0)).getTitle());

		/**
		 * Update Event
		 */
		// Person p = (Person) queries.getUsers().get(0);
		// p.setStatus(Status.ACCEPTED);
		// CalEvent evt = new CalEvent("fuck", new Date(2013, 4, 3), 99999, p,
		// "kom igjen");
		// CalEvent evt = new CalEvent("fuck",new Date() , 99999, p,
		// "kom igjen");
		// queries.addEvent(evt);
		//
		// evt.setDescription("Hallvard er best");
		// evt.setTitle("WATAAAAAAPPPP");
		// queries.updateCalEvent(evt);
		queries.deleteAllParticipansByEventId(1363618018149L);

		/**
		 * Get available rooms
		 */

		// Add event that conflics
		// CalEvent douchEvent = new CalEvent("DouchEvent",new Date(2013, 4, 3,
		// 2, 2), 99999,
		// (Person) queries.getUsers().get(0), "Jeg booket først. SUCK IT");
		// douchEvent.setRoom(new Room("R10", 70, ""));
		// queries.addEvent(douchEvent);

		// run query
		// CalEvent evt = new CalEvent("testeventet",new Date(2013, 4, 3, 2, 5),
		// 99999,
		// (Person) queries.getUsers().get(1), "Dette er en test event, dawg!");
		// ArrayList<Comparable> rooms = queries.getAvailableRooms(evt);
		// int i=1;
		// for( Comparable<Room> room : rooms) {
		// System.out.println(i+": "+room.toString());
		// i++;
		// }

		/**
		 * Search for users
		 */
		// ArrayList<Comparable> res = queries.searchUsers("boy");
		// for( Comparable<Person> per : res) {
		// System.out.println(" "+p.toString());
		// }
	}
}
