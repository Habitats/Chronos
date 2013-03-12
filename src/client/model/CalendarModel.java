package client.model;

import chronos.Person;
import client.ClientController;
import events.NetworkEvent;

public class CalendarModel extends ChronosModel {

	public enum Weekday {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

		public static Weekday getWeekday(int ordinal) {
			for (Weekday weekday : Weekday.values()) {
				if (weekday.ordinal() == ordinal)
					return weekday;
			}
			return null;
		}
	}

	public CalendarModel(ClientController controller) {
		super(controller);
	}

	@Override
	public void fireNetworkEvent(NetworkEvent event) {
	}

	public void newEventBtnPushed() {
		// super.controller

	}

	public void getPersonEvents(Person person) {
		// TODO Auto-generated method stub

	}
}
