package server.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import chronos.Singleton;

import events.NetworkEvent;

/**
 * Handles the connection with a single client. Event client gets it's own
 * connection on it's own thread.
 * 
 */
public class ServerConnection implements Runnable {

	private final Socket clientSocket;
	private final Server server;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ServerConnection(Socket clientSocket, Server server) {
		this.clientSocket = clientSocket;
		this.server = server;
	}

	private void initConnection() throws IOException {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		server.getClientConnections().add(new ClientConnection(out, clientSocket));

		NetworkEvent event;

		try {
			while ((event = (NetworkEvent) in.readObject()) != null) {
				Singleton.log("Server received: " + event);
				List<ClientConnection> clientConnections = server.getClientConnections();
				synchronized (clientConnections) {
					for (ClientConnection clientConnection : clientConnections) {
						if (clientConnection.getClientSocket() == clientSocket && clientConnection.getPerson() == null) {
							clientConnection.setPerson(event.getSender());
							break;
						}
					}
				}
				// forward event to serverController that handles it
				server.getServerController().evaluateNetworkEvent(event);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		out.close();
		in.close();
		clientSocket.close();
	}

	@Override
	public void run() {
		try {
			initConnection();
		} catch (Exception e) {
			try {
				out.close();
				in.close();
				clientSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
