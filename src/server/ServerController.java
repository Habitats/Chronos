package server;

import java.io.IOException;
import java.util.HashMap;

import chronos.Person;
import chronos.Singleton;
import events.AuthEvent;
import events.CalEvent;
import events.NetworkEvent;
import events.QueryEvent;
import events.QueryEvent.QueryType;
import server.database.DatabaseController;
import server.networking.ClientConnection;
import server.networking.Server;

/**
 * Handles communication between the server and DB
 */
public class ServerController implements Runnable {
	private Server server;
	private DatabaseController dbController;

	public ServerController() {
		dbController = new DatabaseController();
		server = new Server(Singleton.getInstance().getPort(), this);
	}

	/**
	 * Evaluates incoming event from client OR on disconnect from
	 * serverConnection
	 */
	public synchronized void evaluateNetworkEvent(NetworkEvent event) {

		switch (event.getType()) {
		case LOGIN:
			event = dbController.authenticateUser((AuthEvent) event);
			echoNetworkEventToSender(event);
			break;
		case LOGOUT:
			dbController.logout(((AuthEvent) event).getPerson());
			break;
		case CALENDAR:
			evaluateCalEvent((CalEvent) event);
			break;
		case QUERY:
			evaluateQueryEvent((QueryEvent) event);
			break;

		}
	}

	/**
	 * Further evaluation of calendar events
	 */
	private void evaluateCalEvent(CalEvent event) {
		switch (event.getState()) {
		case DELETE:
			if (event.getSender().getUsername().toLowerCase().equals(event.getCreator().getUsername().toLowerCase()))
				deleteCalEventForAllParticipants(event);
			else {
				deleteCalEventForSingleParticipan(event, event.getSender());
			}
			break;
		case NEW:
			dbController.addCalEvent(event);
			break;
		case UPDATE:
			dbController.updateCalEvent(event);
			break;
		}
		sendUpdateToAllParticipants(event.getParticipants());
	}

	private void evaluateQueryEvent(QueryEvent event) {
		switch (event.getQueryType()) {
		case CALEVENTS:
			event = dbController.getCalEvents(event.getPerson(), event);
			echoNetworkEventToSender(event);
			break;
		case PERSONS:
			event = dbController.getUsers(event);
			echoNetworkEventToSender(event);
			break;
		case PARTICIPANTS:
			event = dbController.getUsers(event);
			echoNetworkEventToSender(event);
			break;
		case ROOMS:
			event = dbController.getAvailableRooms(event);
			echoNetworkEventToSender(event);
			break;
		}
	}

	/**
	 * Updates the calendar for all participants on change. IE, specific
	 * participants status (declined/accepted), new/deleted events etc
	 * 
	 * @param participants
	 *            all paritcipants for a specific calendar event
	 */
	private void sendUpdateToAllParticipants(HashMap<String, Person> participants) {
		for (String username : participants.keySet()) {
			Person person = participants.get(username);
			QueryEvent event = new QueryEvent(QueryType.CALEVENTS, person);
			event = dbController.getCalEvents(person, event);
			sendSingleNetworkEvent(event, person);
		}
	}

	private void deleteCalEventForSingleParticipan(CalEvent event, Person sender) {
		dbController.removeCalEvent(event, sender);
	}

	private void deleteCalEventForAllParticipants(CalEvent event) {
		for (String username : event.getParticipants().keySet()) {
			dbController.removeCalEvent(event, event.getParticipants().get(username));
		}
	}

	private void sendSingleNetworkEvent(NetworkEvent event, Person person) {
		try {
			for (ClientConnection clientConnection : server.getClientConnections()) {
				if (clientConnection.getPerson() != null && clientConnection.getPerson().getUsername().toLowerCase().equals(person.getUsername().toLowerCase())) {
					clientConnection.getOut().writeObject(event);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void echoNetworkEventToSender(NetworkEvent event) {
		sendSingleNetworkEvent(event, event.getSender());
	}

	/**
	 * Broadcasts the networkEvent to selected participants
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
