package gui.view;

import gui.GBC;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import chronos.Person;

import model.ChronosModel;

public class EventConfigWindow extends ChronosWindow implements ActionListener {

	protected JTextField EventName, EventDescription;
	JList ParticipantList;
	protected JCheckBox Alert;
	protected JToggleButton AddParticipantButton, DeleteParticipantButton, BookRoomButton, EditButton, DeleteButton, ApplyButton, CancelButton;
	
	//public EventConfigWindow(ChronosModel model) {
	public EventConfigWindow() {
		setLayout(new GridBagLayout());
		EventName = new JTextField("Eventname");
		EventDescription = new JTextField("Description");
		ParticipantList = new JList<Person>();
		Alert = new JCheckBox();
		AddParticipantButton = new JToggleButton("Add participant");
		DeleteParticipantButton = new JToggleButton("Delete participant");
		BookRoomButton = new JToggleButton("Book room");
		EditButton = new JToggleButton("Edit");
		DeleteButton = new JToggleButton("Delete");
		ApplyButton = new JToggleButton("Apply");
		CancelButton = new JToggleButton("Cancel");
		
		EventName.setColumns(20);
		EventDescription.setPreferredSize(new Dimension(20, 100));
		ParticipantList.setPreferredSize(new Dimension(10,100));
		
		
		
		add(EventName, new GBC(0,0));
		add(ParticipantList, new GBC(1,0));
		add(AddParticipantButton, new GBC(2,0));
		add(EventDescription, new GBC(0,1));
		add(DeleteParticipantButton, new GBC(1,1));
		add(new Label("Alert:"), new GBC(1,2));
		add(Alert, new GBC(2,2));
		add(EditButton, new GBC(0,2));
		add(DeleteButton, new GBC(1,2));
		add(ApplyButton, new GBC(2,2));
		add(CancelButton, new GBC(3,2));

		//setModel(model);
		
		
		
	}
	@Override
	public void setModel(ChronosModel model) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("EventConfig");
		frame.add(new EventConfigWindow());
		frame.pack();
		frame.setVisible(true);
		
	}
	

}
