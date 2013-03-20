package client.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import chronos.DateManagement;
import chronos.Person;
import chronos.Person.Status;
import chronos.Singleton;
import client.ClientController;
import client.gui.view.CalendarWindow;
import client.gui.view.ChronosWindow;
import events.CalEvent;
import events.CalEvent.CalEventType;
import events.NetworkEvent;
import events.QueryEvent.QueryType;
import events.QueryEvent;

public class CalendarModel extends ChronosModel {

	public enum Weekday {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, NONE;

		public static Weekday getWeekday(int ordinal) {
			for (Weekday weekday : Weekday.values()) {
				if ((weekday.ordinal() + 1) == ordinal)
					return weekday;
			}
			return null;
		}
	}

	private HashMap<String, Boolean> prePersonIsSelected;
	private CalendarWindow calendarWindow;
	private Map<String, ArrayList<CalEvent>> selectedPersonsEvents;

	private HashMap<String, Person> selectedPersons;
	private int currentDisplayedWeek;
	private Date currentDisplayedDate;
	private HashMap<String, Color> personColors;

	public CalendarModel(ClientController controller) {
		super(controller, ChronosType.CALENDAR);
		setSelectedPersonsEvents(Collections.synchronizedMap(new HashMap<String, ArrayList<CalEvent>>()));
		selectedPersons = new HashMap<String, Person>();
		currentDisplayedDate = DateManagement.getMondayOfWeek(new Date());
		currentDisplayedWeek = DateManagement.getWeek(currentDisplayedDate);
		personColors = new HashMap<String, Color>();

		// Hash for debugging, may not be needed
		prePersonIsSelected = new HashMap<String, Boolean>();

		new Thread(new AlarmThread()).start();
	}

	public String getCurrentDisplayedDateIntervall() {
		return DateManagement.getFormattedDateIntervall(currentDisplayedDate);
	}

	public Date getCurrentDisplayedDate() {
		return currentDisplayedDate;
	}

	public String getCurrentDisplayedWeek() {
		return Integer.toString(currentDisplayedWeek);
	}

	/**
	 * method that adds events for a specified person to the
	 * selectedPersonEvents-hash, the person lies in the QueryEvent, and adds
	 * notifications if person equals self
	 */

	private void addEvents(QueryEvent queryEvent) {
		ArrayList<CalEvent> calEvents = (ArrayList<CalEvent>) queryEvent.getResults();
		Person person = queryEvent.getPerson();
		String username = person.getUsername();
		getSelectedPersonsEvents().put(username, calEvents);
		selectedPersons.put(username, person);
		if (Singleton.getInstance().getSelf().getUsername().equals(username)) {
			calendarWindow.removeNotifications();
			addNotifications(calEvents, username);
		}
		update();
	}

	/**
	 * Adds notifications where username equals WAITING
	 */

	private void addNotifications(ArrayList<CalEvent> calEvents, String username) {
		calendarWindow.setNotifications(0);
		for (CalEvent calEvent : calEvents) {
			if (statusIsWaiting(calEvent, username))
				calendarWindow.addNotification(calEvent);
		}
		calendarWindow.getTabbedPane().setTitleAt(1, "Invites (" + calendarWindow.getNotifications() + ")");
		calendarWindow.revalidate();
		calendarWindow.getFrame().validate();
		calendarWindow.getFrame().repaint();
	}

	/**
	 * Method gets called for each person in selectedPersonEvents-hash and adds
	 * them in view. Checks if event is in currentWeek, if so sends the weekday
	 * it should be in. If not then weekday equals NONE and gets added to
	 * eventsList in view.
	 */

	private void addEventsArrayList(ArrayList<CalEvent> calEvents, String username, Color personColor) {
		for (CalEvent calEvent : calEvents) {

			if (personIsAttending(calEvent, username)) {
				Date startDate = calEvent.getStart();
				int eventWeek = DateManagement.getWeek(startDate);
				int eventYear = DateManagement.getYear(startDate);
				int currentDisplayedYear = DateManagement.getYear(currentDisplayedDate);
				if (currentDisplayedWeek == eventWeek && eventYear == currentDisplayedYear) {
					calendarWindow.addEvent(calEvent, DateManagement.getWeekday(startDate), personColor);
				} else {
					calendarWindow.addEvent(calEvent, Weekday.NONE, personColor);
				}
			}
		}
	}

	/**
	 * Checks if status for person(username) equals waiting in the specified
	 * event
	 */

	private boolean statusIsWaiting(CalEvent event, String username) {
		HashMap<String, Person> participants = event.getParticipants();
		Person participant = participants.get(username);
		return (participant.getStatus() == Status.WAITING);
	}

	/**
	 * checks if person has accepted the event
	 */

	private boolean personIsAttending(CalEvent event, String username) {
		HashMap<String, Person> participants = event.getParticipants();
		Person participant = participants.get(username);
		return (participant.getStatus() == Status.ACCEPTED);
	}

	/**
	 * Makes calendarWindow add personCheckBoxes
	 */

	private void addOtherPersons(QueryEvent queryEvent) {
		ArrayList<Person> persons = (ArrayList<Person>) queryEvent.getResults();
		calendarWindow.removePersonCheckBoxes();
		for (Person person : persons) {
			if (selectedPersons.containsKey(person.getUsername())) {
				calendarWindow.addOtherPerson(person, true);
			} else {
				calendarWindow.addOtherPerson(person, false);
			}
		}
		calendarWindow.revalidate();
		calendarWindow.getFrame().validate();
		calendarWindow.getFrame().repaint();
	}

	/**
	 * Sends a queryEvent to get the persons calEvents
	 */

	private void getPersonEvents(Person person) {
		QueryEvent event = new QueryEvent(QueryType.CALEVENTS, person);
		fireNetworkEvent(event);
	}

	/**
	 * Gets called from calendarWindow. Ignore the first line with
	 * prePersonIsSelected, just some extra work.
	 */

	public void addSelectedPerson(Person person) {
		prePersonIsSelected.put(person.getUsername(), true);
		getPersonEvents(person);

	}

	/**
	 * removes person
	 */

	public void removeSelectedPerson(Person person) {
		getSelectedPersonsEvents().remove(person.getUsername());
		selectedPersons.remove(person.getUsername());
		prePersonIsSelected.remove(person.getUsername());
	}

	/**
	 * Updates everything except notifications and personCheckBoxes. Adds color
	 * to the CalEventPanels.
	 */

	public void update() {
		calendarWindow.removeEvents();
		calendarWindow.updateLabels();
		synchronized (getSelectedPersonsEvents()) {
			for (String username : getSelectedPersonsEvents().keySet()) {
				Color color;
				if (username.equals(Singleton.getInstance().getSelf().getUsername())) {
					color = Singleton.SELF_COLOR;
				} else if (personColors.containsKey(username)) {
					color = personColors.get(username);
				} else {
					color = Singleton.COLOR_ARRAY[personColors.size() % Singleton.COLOR_ARRAY.length];
					personColors.put(username, color);
				}
				addEventsArrayList(getSelectedPersonsEvents().get(username), username, color);
			}
		}
	}

	public void nextWeek() {
		if (currentDisplayedWeek < 52) {
			currentDisplayedWeek++;
		} else
			currentDisplayedWeek = 1;
		currentDisplayedDate = DateManagement.getDateFromString(DateManagement.getNextWeek(currentDisplayedDate));
	}

	public void prevWeek() {
		if (currentDisplayedWeek > 1) {
			currentDisplayedWeek--;
		} else
			currentDisplayedWeek = 52;
		currentDisplayedDate = DateManagement.getDateFromString(DateManagement.getPrevWeek(currentDisplayedDate));
	}

	@Override
	public void receiveNetworkEvent(NetworkEvent event) {
		switch (event.getType()) {

		case QUERY:
			evaluateQueryEvent((QueryEvent) event);
			break;
		}
	}

	private void evaluateQueryEvent(QueryEvent queryEvent) {
		switch (queryEvent.getQueryType()) {
		case CALEVENTS:
			addEvents(queryEvent);
			break;
		case PERSONS:
			addOtherPersons(queryEvent);
			break;
		}
	}

	@Override
	public void setView(ChronosWindow calendarWindow) {
		this.calendarWindow = (CalendarWindow) calendarWindow;
	}

	public Map<String, ArrayList<CalEvent>> getSelectedPersonsEvents() {
		return selectedPersonsEvents;
	}

	public void setSelectedPersonsEvents(Map<String, ArrayList<CalEvent>> selectedPersonsEvents) {
		this.selectedPersonsEvents = selectedPersonsEvents;
	}

	/**
	 * Thread somethingsomething alarm?
	 */
	private class AlarmThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					ArrayList<CalEvent> calEvents = getSelectedPersonsEvents().get(Singleton.getInstance().getSelf().getUsername());
					for (CalEvent calEvent : calEvents) {
						if (calEvent.getParticipants().get(Singleton.getInstance().getSelf().getUsername()).getAlert() && DateManagement.isLessThanFifteenMinFromNow(calEvent.getStart())) {
							calendarWindow.alarm(calEvent);
							calEvent.getParticipants().get(Singleton.getInstance().getSelf().getUsername()).setAlert(false);
							fireNetworkEvent(calEvent.setState(CalEventType.UPDATE));
						}
					}
				} catch (NullPointerException e) {
				} finally {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
