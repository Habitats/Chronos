package client;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import chronos.Person;
import chronos.Singleton;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.ChronosModel.ChronosType;
import events.AuthEvent;
import events.CalEvent;
import events.NetworkEvent;
import events.QueryEvent;
import events.TestEvent;

/**
 * Handles the communication between GUI, models and server
 */
public class ClientController implements Runnable, ClientControllerInterface {

	private Client client;
	private Person person;
	private boolean loggedIn;
	private MainFrame mainFrame;
	private HashMap<ChronosType, ChronosModel> models;

	public ClientController() {
		models = new HashMap<ChronosType, ChronosModel>();
		mainFrame = new MainFrame(this);
		mainFrame.buildGui();

		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);

		Singleton.getInstance().setUsersname(Integer.toString((int) (Math.random() * 1000000)));

	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 */
	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Client evaluating: " + event);
		switch (event.getType()) {
		// case TEST:
		// sendTestEvent();
		// break;
		case LOGIN:
			setPerson(((AuthEvent) event).getSender());
			loggedIn = true;
//			sendTestEvent();
			break;
		// case CALENDAR:
		// evaluateCalEvent((CalEvent) event);
		// break;
		case QUERY:
			evaluateQueryEvent((QueryEvent) event);
			break;
		}
	}

	/**
	 * this is never supposed to happend!
	 */
	// private void evaluateCalEvent(CalEvent event) {
	// Singleton.log("Evaluating calEvent...");
	// switch (event.getState()) {
	// case DELETE:
	// break;
	// case NEW:
	// break;
	// case UPDATE:
	// break;
	// }
	// }

	/**
	 * Acts as a router for the events -- making sure they end up in the correct model
	 */
	private void evaluateQueryEvent(QueryEvent event) {
		Singleton.log("Evaluating queryEvent...");
		switch (event.getQueryType()) {
		case CALEVENT:
			models.get(ChronosType.CALENDAR).receiveNetworkEvent(event);
			break;
		case PERSON:
			models.get(ChronosType.USER_LIST).receiveNetworkEvent(event);
			break;
		case ROOM:
			models.get(ChronosType.ROOM_BOOK).receiveNetworkEvent(event);
			break;
		}
	}

	/**
	 * sends network event TO server
	 */
	@Override
	public void sendNetworkEvent(NetworkEvent event) {
		Singleton.log("sending networkEvent to server");
		client.sendNetworkEvent(event);

	}

	public void sendAuthEvent() {
		NetworkEvent authEvent = new AuthEvent(new Person(Singleton.getInstance().getUsername(), null), "asd");
		client.sendNetworkEvent(authEvent);
	}

	@Override
	public void run() {
		Thread clientThread = new Thread(client);
		clientThread.start();
	}

	private void sendTestEvent() {
		Scanner sc = new Scanner(System.in);
		Singleton.log("Entering test event loop. Write messages to test the server connection:");
		String msg = sc.nextLine();
		client.sendNetworkEvent(new TestEvent(msg, getSelf()));
	}

	private void sendCalEvent() {
		Date date = new Date();
		Person bob = new Person("bob");
		Person carl = new Person("carl");
		Person lisa = new Person("lisa");
		CalEvent calEvent = new CalEvent("test", date, 5, getSelf(), null).addParticipant(bob, carl, lisa);
	}

	public Person getSelf() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Used by models to add themselves to the controller
	 */
	public void addModel(ChronosModel chronosModel) {
		models.put(chronosModel.getType(), chronosModel);
	}
}