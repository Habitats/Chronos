package chronos;

import events.NetworkEvent;

import gui.MainFrame;

public class ClientController {

	public ClientController() {
		MainFrame mainFrame = new MainFrame(this);
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
}
