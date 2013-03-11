package server.database;

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

		CalEvent evt = new CalEvent("Foo",new Date(1991, 12, 15, 5, 30, 00), 3600, new Person("gunnar", "some user"));

		System.out.println(""+evt.getStart().getTime());
		evt.setDescription("No title");
		evt.setTitle("Some event");
		evt.addParticipant(new Person("loverBoy", "some other user"));
		evt.setPerson(queries.getUsers().get(0));
		queries.addEvent(evt);
//		String user = "gunnar";
//		queries.addUser(user, null, null);
//		queries.updateUser(user, "passord", "nyttpw");
//		//queries.removeUser(user);
//
//		ArrayList<String[]> users = new ArrayList<String[]>();
//
//		// username, password, name
//		String[] user1 = { "h@xxor", "asdklasd", "bob" };
//		String[] user2 = { "yoyo", "asdklssdasdld", "carl" };
//		String[] user3 = { "sis", "929jsd", "steve" };
//		// quries.removeUser("sis");
//		// quries.removeUser("yoyo");
//		// quries.removeUser("h@xxor");
//
//		// users.add(user1);
//		// users.add(user2);
//		// users.add(user3);
//		//
//		// quries.addMultipleUsers(users);
//
//		ArrayList<Person> userInDb = queries.getUsers();
//		Singleton.log("Users in db:");
//		for (Person person : userInDb) {
//			Singleton.log(person.toString());
//		}
	}
}
