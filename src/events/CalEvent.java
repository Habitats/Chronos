package events;

import java.util.Date;
import java.util.HashMap;

import chronos.Person;
import chronos.Room;

/**
 * Calendar event used for all calendar related thing (update, delete, add etc).
 * Holds a list over all participants (if any)
 */
public class CalEvent extends NetworkEvent implements Comparable<CalEvent> {
	public enum CalEventType {
		UPDATE, NEW, DELETE;
	}

	private HashMap<String, Person> participants;

	private String Title;
	private Date start;
	private int duration;
	private CalEventType type;
	private final Person creator;
	private final String title;
	private String description;
	private final long timestampPrimaryKey;
	private Room room;

	public CalEvent(String title, Date start, int duration, Person creator, String description) {
		this(title, start, duration, creator, description, 0);
	}

	public CalEvent(String title, Date start, int duration, Person creator, String description, long timestampPrimaryKey) {
		super(EventType.CALENDAR);
		setState(CalEventType.NEW);
		this.title = title;
		this.creator = creator;
		this.start = start;
		this.duration = duration;
		participants = new HashMap<String, Person>();
		this.description = description;

		this.timestampPrimaryKey = (timestampPrimaryKey == 0) ? System.currentTimeMillis() : timestampPrimaryKey;
		addParticipant(creator);
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

	public CalEvent setState(CalEventType type) {
		this.type = type;
		return this;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Room getRoom() {
		return room;
	}

	public CalEvent setRoom(Room room) {
		this.room = room;
		return this;
	}

	/**
	 * used as primary key in the db, HATERS GONNA' HATE
	 */
	public long getTimestamp() {
		return timestampPrimaryKey;
	}

	public void setParticipants(HashMap<String, Person> participants) {
		this.participants = participants;
	}

	@Override
	public int compareTo(CalEvent otherEvent) {
		return (int) ((start.getTime() - otherEvent.getStart().getTime()) / 1000);
	}
}
