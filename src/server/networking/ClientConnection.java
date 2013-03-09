package server.networking;

import java.io.ObjectOutputStream;
import java.net.Socket;

import chronos.Person;



public class ClientConnection {

	private final Socket clientSocket;
	private final ObjectOutputStream out;
	private Person person;

	public ClientConnection(ObjectOutputStream out, Socket clientSocket) {
		this.out = out;
		this.clientSocket = clientSocket;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
