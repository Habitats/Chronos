package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import chronos.DateManagement;
import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel;

public class EventConfigWindow extends ChronosWindow implements ActionListener {

	private JTextField eventNameField, dateField, startTimeField, durationField, roomNumberField;
	private JTextArea eventDescriptionArea;
	private JList participantList;
	private JCheckBox alert;
	private JButton addParticipantButton, deleteParticipantButton, bookRoomButton, editButton, deleteButton, applyButton, cancelButton;
	private Dimension button = new Dimension(50, 20);
	private EventConfigModel model;

	public EventConfigWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());
		eventNameField = new JTextField();
		dateField = new JTextField();
		startTimeField = new JTextField();
		durationField = new JTextField();
		eventDescriptionArea = new JTextArea();
		participantList = new JList<>();
		alert = new JCheckBox();
		roomNumberField = new JTextField("");
		addParticipantButton = new JButton("Add participant");
		deleteParticipantButton = new JButton("Delete participant");
		bookRoomButton = new JButton("Book room");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		applyButton = new JButton("Apply");
		cancelButton = new JButton("Cancel");

		eventNameField.setColumns(15);
		dateField.setColumns(8);
		startTimeField.setColumns(5);
		durationField.setColumns(2);
		roomNumberField.setColumns(5);
		eventNameField.setMaximumSize(new Dimension(80, 20));
		eventDescriptionArea.setPreferredSize(new Dimension(100, 100));
		participantList.setPreferredSize(new Dimension(100, 100));
		editButton.setPreferredSize(button);
		editButton.setMinimumSize(new Dimension(50, 20));
		deleteButton.setPreferredSize(button);

		bookRoomButton.addActionListener(new BookRoomAction());
		addParticipantButton.addActionListener(new AddParticipantAction());
		deleteParticipantButton.addActionListener(new DeleteParticipantAction());
		editButton.addActionListener(new EditAction());
		deleteButton.addActionListener(new DeleteAction());
		applyButton.addActionListener(new ApplyAction());
		cancelButton.addActionListener(new CancelAction());

		add(eventNameField, new GBC(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 1));
		add(eventDescriptionArea, new GBC(0, 1).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 6));
		add(editButton, new GBC(0, 7));

		add(deleteButton, new GBC(1, 7).setFill(GridBagConstraints.NONE));

		add(dateField, new GBC(2, 0).setAnchor(GridBagConstraints.FIRST_LINE_START));
		add(participantList, new GBC(2, 1).setSpan(1, 6));
		add(applyButton, new GBC(2, 7));

		add(startTimeField, new GBC(3, 0).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE));
		add(new Label("Enable alert 15 min before"), new GBC(3, 1).setSpan(2, 1).setAnchor(GridBagConstraints.NORTH));
		add(new Label("min before event."), new GBC(3, 1).setSpan(2, 1).setAnchor(GridBagConstraints.SOUTH));

		add(new Label("Alert:"), new GBC(3, 2));
		add(new Label("Room no."), new GBC(3, 3).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE));
		add(bookRoomButton, new GBC(3, 4).setSpan(2, 1));
		add(addParticipantButton, new GBC(3, 5).setSpan(2, 1));
		add(deleteParticipantButton, new GBC(3, 6).setSpan(2, 1));
		add(cancelButton, new GBC(3, 7).setSpan(2, 1));

		add(durationField, new GBC(4, 0));
		add(alert, new GBC(4, 2));
		add(roomNumberField, new GBC(4, 3));
		
		// sets the default model (IE. empty)
		this.model.setDefaultModel();
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (EventConfigModel) model;
		this.model.setView(this);
	}

	public EventConfigModel getModel() {
		return model;
	}

	private class BookRoomAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getRoomBookingWindow().setVisible(true);
		}
	}

	private class AddParticipantAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getAddParticipantWindow().setVisible(true);
		}
	}

	private class DeleteParticipantAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	private class EditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	private class DeleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// slett event i DB
			getFrame().getEventConfigWindow().setVisible(false);

		}
	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getEventConfigWindow().setVisible(false);
		}
	}

	private class ApplyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.newCalEvent();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * General input info from veiw made available for the model
	 */
	public JTextField getEventNameField() {
		return eventNameField;
	}

	public JTextField getStartDateField() {
		return dateField;
	}

	public JTextField getStartTimeField() {
		return startTimeField;
	}

	public JTextField getDurationField() {
		return durationField;
	}

	public JTextField getRoomNumberField() {
		return roomNumberField;
	}

	public JTextArea getEventDescriptionArea() {
		return eventDescriptionArea;
	}

	public JList getParticipantList() {
		return participantList;
	}

	public JCheckBox getAlert() {
		return alert;
	}

}
