package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import events.CalEvent;

public class EventConfigWindow extends ChronosWindow implements ActionListener {

	JTextField eventName, eventDescription;
	JList participantList;
	JCheckBox alert;
	JButton addParticipantButton, deleteParticipantButton, bookRoomButton, editButton, deleteButton, applyButton, cancelButton;
	Dimension button = new Dimension(50, 20);
	EventConfigModel model;

	public EventConfigWindow(ChronosModel model,MainFrame frame) {
		super(model,frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());
		eventName = new JTextField("Eventname");
		participantList = new JList<>();
		eventDescription = new JTextField("Description");
		alert = new JCheckBox();
		addParticipantButton = new JButton("Add participant");
		deleteParticipantButton = new JButton("Delete participant");
		bookRoomButton = new JButton("Book room");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		applyButton = new JButton("Apply");
		cancelButton = new JButton("Cancel");

		eventName.setColumns(20);
		eventName.setMaximumSize(new Dimension(80, 20));
		eventDescription.setPreferredSize(new Dimension(100, 100));
		participantList.setPreferredSize(new Dimension(100, 100));
		editButton.setPreferredSize(button);
		editButton.setMinimumSize(new Dimension(50, 20));
		deleteButton.setPreferredSize(button);

		add(eventName, new GBC(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 1));
		add(eventDescription, new GBC(0, 1).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 6));
		add(editButton, new GBC(0, 7));
		add(deleteButton, new GBC(1, 7));

		add(new Label("Dag og tid blablabla."), new GBC(2, 0).setSpan(2, 1));
		add(participantList, new GBC(2, 1).setSpan(1, 6));
		add(applyButton, new GBC(2, 7));

		add(new Label("Enable alert 15 min before event."), new GBC(3, 1));
		add(new Label("Alert:"), new GBC(3, 2));
		add(alert, new GBC(3, 2));
		add(new Label("Room no."), new GBC(3, 3));
		add(bookRoomButton, new GBC(3, 4));
		add(addParticipantButton, new GBC(3, 5));
		add(deleteParticipantButton, new GBC(3, 6));
		add(cancelButton, new GBC(3, 7));

		// setModel(model);

	}


	@Override
	public void setModel(ChronosModel model) {
		this.model = (EventConfigModel) model;
	}
	
	public EventConfigModel getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("EventConfig");
		frame.add(new EventConfigWindow(null,null));
		frame.pack();
		frame.setVisible(true);

	}

}
