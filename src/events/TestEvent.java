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

	public TestEvent(String msg, Person person) {
		super(EventType.TEST);
		super.person = person;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "[TestEvent] Message: " + msg;
	}

}
