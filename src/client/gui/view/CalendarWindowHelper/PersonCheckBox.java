package client.gui.view.calendarWindowHelper;

import java.awt.Color;
import javax.swing.JCheckBox;

import chronos.Person;

public class PersonCheckBox extends JCheckBox {

	private Person person;

	public PersonCheckBox(Person person) {
		this(person, false);
	}

	public PersonCheckBox(Person person, boolean isSelected) {
		super(person.getName());
		this.person = person;
		setBackground(Color.white);
		this.setSelected(isSelected);
	}

	public PersonCheckBox select(boolean b) {
		super.setSelected(b);
		return this;

	}

	public Person getPerson() {
		return person;
	}
}
