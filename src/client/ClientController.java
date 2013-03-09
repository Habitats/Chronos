package client;

import java.util.Scanner;

import chronos.Singleton;
import client.gui.MainFrame;
import events.AuthEvent;
import events.NetworkEvent;
import events.TestEvent;

/**
 * Handles the communication between GUI, models and server
 * @author anon
 * 
 */
public class ClientController implements Runnable {

	private Client client;
	private MainFrame mainFrame;
	
	public ClientController() {
		mainFrame = new MainFrame(this);
		mainFrame.buildGui();

		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);

	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 * @param event
	 */
	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Client evaluating: " + event);
	}

	/**
	 * sends network event TO server
	 * @param event
	 */
	public void sendNetworkEvent(NetworkEvent event) {
		Singleton.log("sending networkEvent to server");

	}

	@Override
	public void run() {
		Thread clientThread = new Thread(client);
		clientThread.start();

		sendTestEvent();
	}

	private void sendTestEvent() {
		Scanner sc = new Scanner(System.in);
		Singleton.log("Entering test event loop. Write messages to test the server connection:");
		while (true) {
			String msg = sc.nextLine();
			client.sendNetworkEvent(new TestEvent(msg));
		}
	}

	public void addNewEventConfigWindow() {
		//mainFrame.add????
		
	}
}
