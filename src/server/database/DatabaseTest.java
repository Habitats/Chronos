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
		
		queries.addUser("sexyboy", "kyrre", "");
		queries.addUser("childlover", "hallvard", "");
		CalEvent evt = new CalEvent("testevent",new Date(2013, 4, 3), 99999, queries.getUsers().get(0), "Dette er en test event");
		evt.addParticipant(queries.getUsers().get(1));
		queries.addEvent(evt);
		
		ArrayList<Person> userInDb = queries.getUsers();
		Singleton.log("Users in db:");
		for (Person person : userInDb) {
			Singleton.log(person.toString());
		}
	}
}
