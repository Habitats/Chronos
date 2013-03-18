package events;

import chronos.Person;

/**
 * Test event used for debugging
 * 
 * @author anon
 * 
 */
public class TestEvent extends NetworkEvent {

	private String msg;

	public TestEvent(String msg, Person sender) {
		super(EventType.TEST);
		super.sender = sender;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "[TestEvent] Message: " + msg;
	}

}
