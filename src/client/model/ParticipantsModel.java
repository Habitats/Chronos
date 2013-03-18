package client.model;

import java.util.ArrayList;
import java.util.HashMap;

import chronos.Person;
import client.ClientController;
import client.gui.view.ParticipantsWindow;
import client.gui.view.ChronosWindow;
import client.gui.view.CalendarWindowHelper.PersonCheckBox;
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
		for (Person person : (ArrayList<Person>) event.getResults()) {
			users.put(person, view.addUser(person));
		}
		view.getFrame().pack();
	}

	@Override
	public void setView(ChronosWindow view) {
		this.view = (ParticipantsWindow) view;
	}

	public void getUsers() {
		fireNetworkEvent(new QueryEvent(QueryType.PERSONS));
	}

	public ArrayList<Person> getSelectedUsers() {
		ArrayList<Person> selectedUsers = new ArrayList<Person>();
		for (Person user : users.keySet()) {
			if (users.get(user).isSelected())
				selectedUsers.add(user);
		}
		return selectedUsers;
	}
}
