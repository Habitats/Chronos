package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import chronos.Singleton;

import events.NetworkEvent;

/** 
 * Handles the clients connection with the server
 * @author anon
 *
 */
public class Client implements Runnable {
	private final String hostname;
	private final int port;
	private final ClientController clientController;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;

	public Client(int port, String hostname, ClientController clientController) {
		this.port = port;
		this.hostname = hostname;
		this.clientController = clientController;
	}

	private Socket setUpConnection(int port, String hostname) {
		Singleton.log("Connecting to " + hostname + " on port " + port + "...");
		try {
			clientSocket = new Socket(hostname, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientSocket;
	}

	private void initConnection(Socket socket) {
		Singleton.log("Initiating streams...");
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			NetworkEvent event;
			while ((event = (NetworkEvent) in.readObject()) != null) {
//				Singleton.log("Client received: " + event.toString());
				getClientController().evaluateNetworkEvent(event);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		Socket socket = setUpConnection(port, hostname);
		initConnection(socket);

		kill();
	}

	private void kill() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
		} catch (IOException e) {
			Singleton.log("Couldn't close socket...");
		}
	}

	public ClientController getClientController() {
		return clientController;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void sendNetworkEvent(NetworkEvent event) {
		try {
			Singleton.log("Client sending: " + event);
			out.writeObject(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
