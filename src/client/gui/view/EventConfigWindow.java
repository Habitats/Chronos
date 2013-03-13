package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import chronos.DateManagement;
import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import events.CalEvent;

public class EventConfigWindow extends ChronosWindow implements ActionListener {

	JTextField eventNameField, dateField, startTimeField, durationField, roomNumberField;
	JTextArea eventDescriptionArea;
	JList participantList;
	JCheckBox alert;
	JButton addParticipantButton, deleteParticipantButton, bookRoomButton,
			editButton, deleteButton, applyButton, cancelButton;
	Dimension button = new Dimension(50, 20);
	EventConfigModel model;

	public EventConfigWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());
		eventNameField = new JTextField("Eventname");
		dateField = new JTextField("DD.MM.YY");
		startTimeField = new JTextField("00:00");
		durationField = new JTextField("1h");
		eventDescriptionArea = new JTextArea("Description");
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
		
		eventNameField.addActionListener(new EventNameAction());
		dateField.addActionListener(new DateAction());
		bookRoomButton.addActionListener(new BookRoomAction());
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
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (EventConfigModel) model;
		//model.addPropertyChangeListener(this)
	}

	public EventConfigModel getModel() {
		return model;
	}

	public class DateAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String dateString = e.getActionCommand();
			Date date = DateManagement.getDateFromString(dateString);
			System.out.println((date == null) ? "Date was invalid: " + date
					: "Date was valid: "
							+ DateManagement.getFormattedSimple(date));
		}
	}
	
	public class EventNameAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventNameString = e.getActionCommand();
			
			
		}
	}
	
	public class BookRoomAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getRoomBookingWindow().setVisible(true);
		}
	}
	
	public class EditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class DeleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//slett event i DB
			getFrame().getEventConfigWindow().setVisible(false);
			
		}
	}
	public class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getEventConfigWindow().setVisible(false);
		}	
	}
	
	public class ApplyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// send til DB (navn, tid, beskrivelse ...)
			//model.setEventName(eventNameField.getText());
			//model.setEventDescription(eventDescriptionArea.getText());
			//model.setAlert(alert.???);
			//model.setParticipant(participantList.??);
			//model.getRoomNumber();
			getFrame().getEventConfigWindow().setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
