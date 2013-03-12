package client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import chronos.DateManagement;
import chronos.Person;
import client.ClientController;
import client.gui.view.CalendarWindow;
import events.CalEvent;
import events.NetworkEvent;
import events.NetworkEvent.EventType;
import events.QueryEvent;
import events.QueryEvent.QueryType;

public class CalendarModel extends ChronosModel {
	
	public enum Weekday {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, NONE;
		
		public static Weekday getWeekday(int ordinal) {
			for (Weekday weekday : Weekday.values()) {
				if (weekday.ordinal() == ordinal)
					return weekday;
			}
			return null;
		}
	}
	
	private CalendarWindow calendarWindow;
	private HashMap<Person, ArrayList<CalEvent>> selectedPersonsEvents;


	public CalendarModel(ClientController controller) {
		super(controller);
		selectedPersonsEvents = new HashMap<Person,ArrayList<CalEvent>>();
		
		
	}

	@Override
	public void fireNetworkEvent(NetworkEvent event) {
		
	}
	
	/**
	 * method that adds events for a specified person, the person lies in the QueryEvent
	 * @param queryEvent
	 */

	public void addEvents(QueryEvent queryEvent) {
		ArrayList<CalEvent> calEvents  = (ArrayList<CalEvent>) queryEvent.getResults();
		selectedPersonsEvents.put(queryEvent.getPerson(), calEvents);
		addEventsArrayList(calEvents);
	}
	
	private void addEventsArrayList(ArrayList<CalEvent> calEvents) {
		int currentWeek = DateManagement.getCurrentWeek();
		for (CalEvent calEvent : calEvents) {
			Date startDate = calEvent.getStart();
			int eventWeek = DateManagement.getWeek(startDate);
			if(currentWeek == eventWeek){
				calendarWindow.addEvent(calEvent, DateManagement.getWeekday(startDate));
			} else {
				calendarWindow.addEvent(calEvent, Weekday.NONE);
			}
		}
	}
	
	public void addOtherPersons(QueryEvent queryEvent) {
		ArrayList<Person> persons  = (ArrayList<Person>) queryEvent.getResults();
		for (Person person : persons) {
			calendarWindow.addOtherPerson(person);
		}
	}
	public void setView(CalendarWindow calendarWindow) {
		this.calendarWindow = calendarWindow;
		
	}
	private void getPersonEvents(Person person) {
		QueryEvent event = new QueryEvent(EventType.BATCH_CALENDAR, person);
		controller.sendQueryEvent(event);
	}

	public void setSelectedPerson(Person person) {
		getPersonEvents(person);
		
	}
	public void removeSelectedPerson(Person person) {
		selectedPersonsEvents.remove(person);
	}
	public void update() {
		calendarWindow.removeEvents();
		for (Person personKeys : selectedPersonsEvents.keySet()) {
			addEventsArrayList(selectedPersonsEvents.get(personKeys));
		}
	}
}
