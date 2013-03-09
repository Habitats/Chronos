package client;

import java.util.Scanner;

import chronos.Person;
import chronos.Singleton;
import client.gui.MainFrame;
import events.AuthEvent;
import events.CalEvent;
import events.NetworkEvent;
import events.NetworkEvent.EventType;
import events.TestEvent;

/**
 * Handles the communication between GUI, models and server
 * 
 * @author anon
 * 
 */
public class ClientController implements Runnable {

	private Client client;
	private Person person;
	private boolean loggedIn;

	public ClientController() {
		MainFrame mainFrame = new MainFrame(this);
		mainFrame.buildGui();

		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);

		Singleton.getInstance().setUsersname(Integer.toString((int) (Math.random() * 1000000)));

	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 * 
	 * @param event
	 */
	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Client evaluating: " + event);
		switch (event.getType()) {
		case TEST:
			sendTestEvent();
			break;
		case LOGIN:
			person = ((AuthEvent) event).getSender();
			loggedIn = true;
			sendTestEvent();
			break;

		}
	}

	/**
	 * sends network event TO server
	 * 
	 * @param event
	 */
	public void sendNetworkEvent(NetworkEvent event) {
		Singleton.log("sending networkEvent to server");

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
		client.sendNetworkEvent(new TestEvent(msg, person));
	}

	private void sendCalEvent() {
		NetworkEvent calEvent = new CalEvent();
	}
}