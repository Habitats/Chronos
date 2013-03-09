package events;

import java.util.ArrayList;
import java.util.HashMap;

import chronos.Person;

/**
 * Calendar event used for all calendar related thing (update, delete, add etc).
 * Holds a list over all participants (if any)
 * 
 * @author anon
 * 
 */
public class CalEvent extends NetworkEvent{
	HashMap<String, Person> participants;

	public CalEvent() {
		super(EventType.CALENDAR);
		participants = new HashMap<String, Person>();
	}

	public CalEvent addParticipant(Person person) {
		participants.put(person.getUsername(), person);
		return this;
	}

	public String toString() {
		String people = null;
		for (String person : participants.keySet()) {
			people += person + ", ";
		}
		if (people != null)
			people = people.subSequence(0, people.length() - 3).toString();
		return "[CalEvent] Participants: " + people;
	}
	
	public HashMap<String, Person> getParticipants() {
		return participants;
}
}
