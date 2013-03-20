package client.model;

import java.util.ArrayList;
import java.util.HashMap;

import chronos.Person;
import chronos.Singleton;
import chronos.Person.Status;
import client.ClientController;
import client.gui.view.ParticipantsWindow;
import client.gui.view.ChronosWindow;
import client.gui.view.calendarWindowHelper.PersonCheckBox;
import events.NetworkEvent;
import events.QueryEvent;
import events.QueryEvent.QueryType;

public class ParticipantsModel extends ChronosModel {

	private ParticipantsWindow view;
	private HashMap<Person, PersonCheckBox> users;

	public ParticipantsModel(ClientController controller) {
		super(controller, ChronosType.USER_LIST);
		users = new HashMap<Person, PersonCheckBox>();
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		addUsers((QueryEvent) event);
	}

	private void addUsers(QueryEvent event) {
		view.getUsersPanel().removeAll();
		HashMap<String, Person> participants = view.getFrame().getEventModel().getParticipants();
		users.clear();
		// adds all users to a list, and checks those who are already
		// participating
		for (Person person : (ArrayList<Person>) event.getResults()) {
			users.put(person, view.addUser(person).select(participants.get(person.getUsername()) != null));
		}
		view.getFrame().revalidate();
		view.getFrame().repaint();
	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (ParticipantsWindow) view;
	}

	public void getUsers() {
		fireNetworkEvent(new QueryEvent(QueryType.PARTICIPANTS));
	}

	public HashMap<String, Person> getSelectedUsers() {
		HashMap<String, Person> selectedUsers = new HashMap<String, Person>();
		for (Person user : users.keySet())
			if (users.get(user).isSelected())
				selectedUsers.put(user.getUsername(), user.setStatus(Status.WAITING));
		selectedUsers.put(Singleton.getInstance().getSelf().getUsername(), Singleton.getInstance().getSelf().setStatus(Status.ACCEPTED));
		return selectedUsers;
	}

	public void getUsers(String argument) {
		fireNetworkEvent(new QueryEvent(QueryType.PARTICIPANTS, argument));
	}
}
