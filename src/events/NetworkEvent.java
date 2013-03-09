package events;

import java.io.Serializable;
import java.util.ArrayList;

import chronos.Person;

abstract public class NetworkEvent implements Serializable {
	private static final long serialVersionUID = 4077361285383168257L;

	public enum EventType {
		LOGIN, //
		CALENDAR, //
		ROOM_BOOK, //
		USER_SEARCH, //
		TEST, //
	}

	ArrayList<?> results;
	private EventType type;
	private Person person;

	public NetworkEvent(EventType type) {
		this.type = type;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String toString() {
		return String.format("Network Event with type: %s, from person: %s", type.name(), person.toString());
	}
}
