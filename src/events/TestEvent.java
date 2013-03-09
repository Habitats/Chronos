package events;

/**
 * Test event used for debugging
 * @author anon
 *
 */
public class TestEvent extends NetworkEvent {

	private String msg;

	public TestEvent(String msg) {
		super(EventType.TEST);
		this.msg = msg;
	}

	public String toString() {
		return "[TestEvent] Message: " + msg;
	}

}
