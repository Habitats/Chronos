package server.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import chronos.Person;
import chronos.Singleton;
import events.CalEvent;



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
//		queries.addUser("sexyboy","derp", "kyrre", 1829000);
//		queries.addUser("childlover","derp", "hallvard", 1829000);
		Person p = queries.getUsers().get(0);
		System.out.println(p);
		CalEvent evt = new CalEvent("testevent",new Date(2013, 4, 3), 99999, p, "Dette er en test event");
		evt.addParticipant(queries.getUsers().get(1));
		queries.addEvent(evt);

//		ArrayList<Person> userInDb = queries.getUsers();
//		Singleton.log("Users in db:");
//		for (Person person : userInDb) {
//			Singleton.log(person.toString());
//		}
		
		/**
		 * Add multiple Users
		 * */
		
		ArrayList<Person> peeps = new ArrayList<Person>();
		peeps.add(new Person("yolo", "derp", "Yolo Swaggins", 133333337));
		peeps.add(new Person("Kyra", "derp", "Kyyra, kom no sa!", new Date().getTime()));
		
		queries.addEvent(evt);
		
		
	}
}
