package events;

import java.util.Date;
import java.util.HashMap;

import chronos.Person;

/**
 * Calendar event used for all calendar related thing (update, delete, add etc).
 * Holds a list over all participants (if any)
 */
public class CalEvent extends NetworkEvent {
	public enum CalEventType {
		UPDATE, NEW, DELETE;
	}

	HashMap<String, Person> participants;

	private Date start;
	private int duration;
	private CalEventType type;
	private final Person creator;
	private String description;

	public CalEvent(Date start, int duration, Person creator) {
		super(EventType.CALENDAR);
		setState(CalEventType.NEW);
		this.creator = creator;
		this.start = start;
		this.duration = duration;
		participants = new HashMap<String, Person>();
	}

	public CalEvent addParticipant(Person... person) {
		for (int i = 0; i < person.length; i++)
			participants.put(person[i].getUsername(), person[i]);
		return this;
	}

	@Override
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

	public void setState(CalEventType type) {
		this.type = type;
	}

	public CalEventType getState() {
		return type;
	}

	public Date getStart() {
		return start;
	}

	public int getDuration() {
		return duration;
	}

	public Person getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
