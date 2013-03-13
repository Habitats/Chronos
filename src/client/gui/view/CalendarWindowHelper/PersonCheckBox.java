package client.gui.view.CalendarWindowHelper;

import javax.swing.JCheckBox;

import chronos.Person;

public class PersonCheckBox extends JCheckBox {

	private Person person;

	public PersonCheckBox(Person person) {
		super(person.getUsername());
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}
