package chronos;

import events.NetworkEvent;

import gui.MainFrame;

public class ClientController {
	
	private MainFrame mainFrame;

	public ClientController() {
		mainFrame = new MainFrame(this);
		mainFrame.buildGui();

	}

	/**
	 * takes networkEvent FROM server, and evalutes it
	 * 
	 * @param event
	 */
	public void evaluateNetworkEvent(NetworkEvent event) {

	}

	/**
	 * sends network event TO server
	 * 
	 * @param event
	 */
	public void sendNetworkEvent(NetworkEvent event) {
		Singleton.log("sending networkEvent to server");

	}

	public void newEventConfigWindow() {
		System.out.println("New event");
	}
}
