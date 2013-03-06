package events;

import java.util.ArrayList;

abstract public class NetworkEvent {
	public enum EventType {
		LOGIN, //
		CALENDAR, //
		ROOM_BOOK, //
		USER_SEARCH, //
	}

	ArrayList<?> results;
	private EventType type;

	public NetworkEvent(EventType type) {
		this.type = type;
	}
}
