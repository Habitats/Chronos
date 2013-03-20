package client;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import chronos.Person;
import chronos.Singleton;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.ChronosModel.ChronosType;
import client.model.LoginModel;
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
	private boolean loggedIn;
	private MainFrame mainFrame;
	private HashMap<ChronosType, ChronosModel> models;
	private AuthEvent authEvent;

	public ClientController() {
		models = new HashMap<ChronosType, ChronosModel>();
		mainFrame = new MainFrame(this);
		mainFrame.loginPrompt();

		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);
	}

	public ClientController(AuthEvent event) {
		models = new HashMap<ChronosType, ChronosModel>();
		mainFrame = new MainFrame(this);
		setAuthEvent(event);
		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);
	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 */
	public synchronized void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Client evaluating: " + event);
		switch (event.getType()) {
		case LOGIN:
			if (((AuthEvent) event).getAccessGranted()) {
				Singleton.getInstance().setSelf(((AuthEvent) event).getPerson());
				loggedIn = true;
				mainFrame.buildGui();
			} else
				((LoginModel) models.get(ChronosType.LOGIN)).setDenied();
			break;
		case QUERY:
			evaluateQueryEvent((QueryEvent) event);
			break;
		}
	}

	/**
	 * Acts as a router for the events -- making sure they end up in the correct
	 * model
	 */
	private void evaluateQueryEvent(QueryEvent event) {
		switch (event.getQueryType()) {
		case CALEVENTS:
			models.get(ChronosType.CALENDAR).receiveNetworkEvent(event);
			break;
		case PERSONS:
			models.get(ChronosType.CALENDAR).receiveNetworkEvent(event);
			break;
		case PARTICIPANTS:
			models.get(ChronosType.USER_LIST).receiveNetworkEvent(event);
			break;
		case ROOMS:
			models.get(ChronosType.ROOM_BOOK).receiveNetworkEvent(event);
			break;
		}
	}

	/**
	 * sends network event TO server
	 */
	@Override
	public synchronized void sendNetworkEvent(NetworkEvent event) {

		// Send to server
		if (Singleton.getInstance().networkEnabled())
			client.sendNetworkEvent(event);
	}

	@Override
	public void run() {
		Thread clientThread = new Thread(client);
		if (Singleton.getInstance().networkEnabled())
			clientThread.start();
	}

	private void sendTestEvent() {
		Scanner sc = new Scanner(System.in);
		Singleton.log("Entering test event loop. Write messages to test the server connection:");
		String msg = sc.nextLine();
		client.sendNetworkEvent(new TestEvent(msg, Singleton.getInstance().getSelf()));
	}

	private void sendCalEvent() {
		Date date = new Date();
		Person bob = new Person("bob");
		Person carl = new Person("carl");
		Person lisa = new Person("lisa");
		CalEvent calEvent = new CalEvent("test", date, 5, Singleton.getInstance().getSelf(), null).addParticipant(bob, carl, lisa);
	}

	/**
	 * Used by models to add themselves to the controller
	 */
	public void addModel(ChronosModel chronosModel) {
		models.put(chronosModel.getType(), chronosModel);
	}

	/**
	 * used primarily for debugging
	 */
	private void setAuthEvent(AuthEvent authEvent) {
		this.authEvent = authEvent;
	}

	public AuthEvent getAuthEvent() {
		return authEvent;
	}

	public void sendAuthEvent() {
		sendNetworkEvent(authEvent);
	}
}