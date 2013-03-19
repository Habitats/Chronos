package events;

import java.io.Serializable;
import java.util.ArrayList;

import chronos.Person;
import chronos.Singleton;

/**
 * Wrapper class for all events sent to server.
 */
abstract public class NetworkEvent implements Serializable {
	protected static final long serialVersionUID = 4077361285383168257L;

	public enum EventType {
		LOGIN, //
		CALENDAR, //
		TEST, //
		QUERY, //
		LOGOUT, //
		LOG_OUT, //
	}

	protected ArrayList<Comparable> results;
	protected Person sender;
	private EventType type;

	public NetworkEvent(EventType type) {
		setSender(Singleton.getInstance().getSelf());
		this.type = type;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person person) {
		this.sender = person;
	}

	@Override
	public String toString() {
		return String.format("Network Event with type: %s, from person: %s", type.name(), sender.toString());
	}

	public EventType getType() {
		return type;
	}
}
