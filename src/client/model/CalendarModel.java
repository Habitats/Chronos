package client.model;

import java.util.ArrayList;

import chronos.Person;
import client.ClientController;
import events.CalEvent;
import events.NetworkEvent;
import events.QueryEvent;

public class CalendarModel extends ChronosModel {
	
	public enum Weekday {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}

	public CalendarModel(ClientController controller) {
		super(controller);
	}

	@Override
	public void fireNetworkEvent(NetworkEvent event) {
	}

	public void getPersonEvents(Person person) {
		
	}
	public void addEvents(QueryEvent queryEvent) {
		ArrayList<CalEvent> calEvents  =queryEvent.getResults();
	}
}
