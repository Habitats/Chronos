package server;

import java.io.IOException;

import chronos.Singleton;
import events.AuthEvent;
import events.CalEvent;
import events.NetworkEvent;
import server.database.DatabaseController;
import server.networking.ClientConnection;
import server.networking.Server;

/**
 * Handles communication between the server and DB
 * 
 * @author anon
 * 
 */
public class ServerController implements Runnable {
	private Server server;
	private DatabaseController dbController;

	public ServerController() {
		dbController = new DatabaseController();
		server = new Server(Singleton.getInstance().getPort(), this);
	}

	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Server evaluating: " + event.toString());

		switch (event.getType()) {
		case LOGIN:
			event = dbController.authenticateUser((AuthEvent) event);
			echoNetworkEventToSender(event);
			break;
		case CALENDAR:
			broadcastNetworkEvent(event);
			break;
		case ROOM_BOOK:
			echoNetworkEventToSender(event);
			break;
		case TEST:
			echoNetworkEventToSender(event);
			break;
		case USER_SEARCH:
			echoNetworkEventToSender(event);
			break;
		}
	}

	private void echoNetworkEventToSender(NetworkEvent event) {
		try {
			for (ClientConnection clientConnection : server.getClientConnections()) {
				if (clientConnection.getPerson().getUsername().toLowerCase().equals(event.getSender().getUsername().toLowerCase())) {
					Singleton.log("Echoing event: " + event + " - Recepient: " + event.getSender().getUsername());
					clientConnection.getOut().writeObject(event);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Broadcasts the networkEvent to selected participants
	 * 
	 * @param event
	 */
	private void broadcastNetworkEvent(NetworkEvent event) {
		for (ClientConnection clientConnection : server.getClientConnections()) {
			try {
				if (((CalEvent) event).getParticipants().containsKey(clientConnection.getPerson().getUsername())) {
					Singleton.log("Broadcasting event: " + event);
					clientConnection.getOut().writeObject(event);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
}
