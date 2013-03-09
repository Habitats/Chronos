package client;

import java.util.Scanner;

import chronos.Singleton;
import client.gui.MainFrame;
import events.AuthEvent;
import events.NetworkEvent;
import events.TestEvent;

public class ClientController implements Runnable {

	private Client client;

	public ClientController() {
		MainFrame mainFrame = new MainFrame(this);
		mainFrame.buildGui();

		client = new Client(Singleton.getInstance().getPort(), Singleton.getInstance().getHostname(), this);

	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 * 
	 * @param event
	 */
	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Client evaluating: " + event);
	}

	/**
	 * sends network event TO server
	 * 
	 * @param event
	 */
	public void sendNetworkEvent(NetworkEvent event) {
		Singleton.log("sending networkEvent to server");

	}

	@Override
	public void run() {
		Thread clientThread = new Thread(client);
		clientThread.start();

		sendNetworkEvent();
	}

	private void sendNetworkEvent() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String msg = sc.nextLine();
			client.sendNetworkEvent(new TestEvent(msg));
		}
	}
}
