package client.gui.view.CalendarWindowHelper;

import java.awt.Color;

import javax.swing.JCheckBox;

import chronos.Person;

public class PersonCheckBox extends JCheckBox {

	private Person person;

	public PersonCheckBox(Person person) {
		this(person, false);
	}

	public PersonCheckBox(Person person, boolean isSelected) {
		super(person.getUsername());
		this.person = person;
		setBackground(Color.white);
		this.setSelected(isSelected);
	}

	public Person getPerson() {
		return person;
	}
}
