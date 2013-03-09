package server;

import java.io.IOException;

import chronos.Singleton;
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

	public ServerController() {
		DatabaseController dbController = new DatabaseController();
		server = new Server(Singleton.getInstance().getPort(), this);
	}

	public void evaluateNetworkEvent(NetworkEvent event) {
		Singleton.log("Server evaluating: " + event.toString());
		broadcastNetworkEvent(event);
	}

	private void broadcastNetworkEvent(NetworkEvent event) {
		for (ClientConnection clientConnection : server.getClientConnections()) {
			try {
				clientConnection.getOut().writeObject(event);
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
