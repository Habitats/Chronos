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
		selectedPersonsEvents = Collections.synchronizedMap(new HashMap<String, ArrayList<CalEvent>>());
		selectedPersons = new HashMap<String, Person>();
		currentDisplayedDate = DateManagement.getMondayOfWeek(new Date());
		currentDisplayedWeek = DateManagement.getWeek(currentDisplayedDate);
		personColors = new HashMap<String, Color>();

		// Hash for debugging, may not be needed
		prePersonIsSelected = new HashMap<String, Boolean>();
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
	 * method that adds events for a specified person to the selectedPersonEvents-hash,
	 * the person lies in the QueryEvent,
	 * and adds notifications if person equals self
	 * 
	 * @param queryEvent
	 */

	private void addEvents(QueryEvent queryEvent) {
		ArrayList<CalEvent> calEvents = (ArrayList<CalEvent>) queryEvent.getResults();
		Person person = queryEvent.getPerson();
		String username = person.getUsername();
		selectedPersonsEvents.put(username, calEvents);
		selectedPersons.put(username, person);
		if (Singleton.getInstance().getSelf().getUsername().equals(username)) {
			calendarWindow.removeNotifications();
			addNotifications(calEvents, username);
		}
		update();
	}

	/**
	 * Adds notifications where username equals WAITING
	 * @param calEvents
	 * @param username
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
	 * Method gets called for each person in selectedPersonEvents-hash and adds them in view.
	 * Checks if event is in currentWeek, if so sends the weekday it should be in.
	 * If not then weekday equals NONE and gets added to eventsList in view.
	 * @param calEvents
	 * @param username
	 * @param personColor
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
	 * Checks if status for person(username) equals waiting in the specified event
	 * @param event
	 * @param username
	 * @return
	 */

	private boolean statusIsWaiting(CalEvent event, String username) {
		HashMap<String, Person> participants = event.getParticipants();
		Person participant = participants.get(username);
		try {
			if (participant.getStatus() == Status.WAITING)
				return true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * checks if person has accepted the event
	 * @param event
	 * @param username
	 * @return
	 */
	
	private boolean personIsAttending(CalEvent event, String username) {
		HashMap<String, Person> participants = event.getParticipants();
		Person participant = participants.get(username);
		try {
			if (participant.getStatus() == Status.ACCEPTED)
				return true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * Makes calendarWindow add personCheckBoxes
	 * @param queryEvent
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
	 * @param person
	 */

	private void getPersonEvents(Person person) {
		QueryEvent event = new QueryEvent(QueryType.CALEVENTS, person);
		fireNetworkEvent(event);
	}
	/**
	 * Gets called from calendarWindow. Ignore the first line with prePersonIsSelected,
	 * just some extra work.
	 * @param person
	 */

	public void addSelectedPerson(Person person) {
		prePersonIsSelected.put(person.getUsername(), true);
		getPersonEvents(person);

	}
	/**
	 * removes person
	 * @param person
	 */

	public void removeSelectedPerson(Person person) {
		selectedPersonsEvents.remove(person.getUsername());
		selectedPersons.remove(person.getUsername());
		// personColors.remove(person.getUsername());
		prePersonIsSelected.remove(person.getUsername());
	}
	/**
	 * Updates everything except notifications and personCheckBoxes. 
	 * Adds color to the CalEventPanels.
	 */

	public void update() {
		calendarWindow.removeEvents();
		calendarWindow.updateLabels();
		synchronized (selectedPersonsEvents) {
			for (String username : selectedPersonsEvents.keySet()) {
				Color color;
				if (username.equals(Singleton.getInstance().getSelf().getUsername())) {
					color = Singleton.SELF_COLOR;
				} else if (personColors.containsKey(username)) {
					color = personColors.get(username);
				} else {
					color = Singleton.COLOR_ARRAY[personColors.size() % Singleton.COLOR_ARRAY.length];
					personColors.put(username, color);
				}
				addEventsArrayList(selectedPersonsEvents.get(username), username, color);
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

	/**
	 * Thread somethingsomething alarm?
	 * @author Jostein
	 *
	 */
	class AlarmThread implements Runnable {

		@Override
		public void run() {
			Person self = Singleton.getInstance().getSelf();
			ArrayList<CalEvent> calEvents = selectedPersonsEvents.get(self.getUsername());
			for (CalEvent calEvent : calEvents) {
				if (calEvent.getAlert() && DateManagement.isLessThanFifteenMinFromNow(calEvent.getStart())) {
					calendarWindow.alarm(calEvent);
					calEvent.getAlert();
				}
			}
		}
	}
}
