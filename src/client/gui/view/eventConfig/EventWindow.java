package client.gui.view.eventConfig;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import chronos.Person;
import chronos.Room;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.gui.view.ChronosWindow;
import client.gui.view.EditConfigButton;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import client.model.EventConfigModel.ViewType;

abstract public class EventWindow extends ChronosWindow {

	private String[] startTimeArray = { "00:00", "01:00", "02:00", "03:00", "04:00" };
	private Integer[] durationArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	protected JTextField eventNameField;
	protected JButton addParticipantButton;
	protected JButton deleteParticipantButton;
	protected JButton bookRoomButton;
	protected JCheckBox alert;
	protected JButton deleteButton;
	protected JButton applyButton;
	protected JComboBox startTime, duration;
	protected JTextArea eventDescriptionArea;
	protected JTextField dateField;

	private JButton editButton;
	private JButton cancelButton;
	private JTextField startTimeField;
	private JTextField roomNumberField;
	private JList<Person> participantList;

	private Dimension bottomButtonDim = new Dimension(80, 20);
	private Dimension bigButtonDim = new Dimension(170, 20);
	protected EventConfigModel model;
	private EventWindow view;
	private ViewType type;
	private JLabel participantsLbl;
	private JLabel creator;
	private JLabel descriptionLbl;
	private JLabel nameLbl;
	private JLabel durationLbl;
	private JLabel startDateLbl;
	private JLabel startTimeLbl;
	private JLabel creatorField;

	public EventWindow(ChronosModel model, MainFrame frame, ViewType type) {
		super(model, frame);
		setModel(model);
		setVisible(false);
		this.type = type;

		int eventConfigWidth = 500;
		int eventConfigHeight = 300;

		setBounds((frame.getFrameWidth() - eventConfigWidth) / 2, (frame.getFrameHeight() - eventConfigHeight) / 2, eventConfigWidth, eventConfigHeight);
		((EventConfigModel) model).getEventViews().put(type, this);
		// this is kind of sloppy but hey, it's used for the actionslisteners to
		// work properly
		view = this;

		setLayout(new GridBagLayout());
		eventNameField = new JTextField();
		dateField = new JTextField();
		startTimeField = new JTextField();
		startTime = new JComboBox(startTimeArray);
		duration = new JComboBox(durationArray);
		creatorField = new JLabel();
		eventDescriptionArea = new JTextArea();

		participantList = new JList<Person>();
		participantList.setEnabled(true);

		alert = new JCheckBox();

		roomNumberField = new JTextField("");
		roomNumberField.setEditable(false);

		// editButton = new EditConfigButton("Edit", bottomButtonDim);
		applyButton = new EditConfigButton("Apply");
		cancelButton = new EditConfigButton("Cancel");

		eventNameField.setColumns(15);
		Border border = BorderFactory.createEmptyBorder(0, 3, 0, 3);
		eventNameField.setBorder(border);

		dateField.setColumns(8);
		dateField.setBorder(border);
		roomNumberField.setColumns(5);

		eventDescriptionArea.setPreferredSize(new Dimension(100, 100));
		eventDescriptionArea.setBorder(border);
		participantList.setPreferredSize(new Dimension(100, 100));

		startTime.addActionListener(new StartTimeListener());
		duration.addActionListener(new DurationListener());

		// editButton.addActionListener(new EditAction());
		applyButton.addActionListener(new ApplyAction());
		cancelButton.addActionListener(new CancelAction());

		nameLbl = new JLabel("Name");
		descriptionLbl = new JLabel("Description");
		creator = new JLabel("Creator");
		participantsLbl = new JLabel("Participants");
		durationLbl = new JLabel("Duration (hours)");
		startDateLbl = new JLabel("Date");
		startTimeLbl = new JLabel("Time");

		int i = 0;
		add(nameLbl, new GBC(0, 0, Align.NOT_BOTTOM));
		add(startDateLbl, new GBC(2, 0, Align.NOT_BOTTOM));
		add(startTimeLbl, new GBC(4, 0, Align.NOT_BOTTOM));
		add(durationLbl, new GBC(5, 0, Align.NOT_BOTTOM));

		add(eventNameField, new GBC(0, 1, Align.NOT_BOTTOM).setSpan(2, 1));
		add(dateField, new GBC(2, 1, Align.NOT_BOTTOM).setSpan(2, 1));
		add(startTime, new GBC(4, 1, Align.NOT_BOTTOM));
		add(duration, new GBC(5, 1, Align.NOT_BOTTOM));

		add(descriptionLbl, new GBC(0, 2, Align.NOT_BOTTOM));
		add(participantsLbl, new GBC(2, 2, Align.NOT_BOTTOM));
		add(new Label("Enable alert 15 min before"), new GBC(4, 2, Align.NOT_BOTTOM).setSpan(2, 1));

		add(eventDescriptionArea, new GBC(0, 3).setSpan(2, 6).setWeight(1, 1));
		add(participantList, new GBC(2, 3).setSpan(2, 6).setWeight(1, 1));

		add(new Label("Alert:"), new GBC(4, 3));
		add(alert, new GBC(5, 3));
		add(new Label("Room no."), new GBC(4, 4));
		add(roomNumberField, new GBC(5, 4));

		add(creator, new GBC(4, 5));
		add(creatorField, new GBC(5, 5));

		// add(editButton, new GBC(0, 7));
		add(applyButton, new GBC(4, 9).setWeight(0.5, 0));
		add(cancelButton, new GBC(5, 9).setWeight(0.5, 0));

		// sets the default model (IE. empty)
		this.model.setDefaultModel();
	}

	public void setParticipants(HashMap<String, Person> participants) {
		DefaultListModel<Person> defaultModel = new DefaultListModel<Person>();
		participantList.setModel(defaultModel);
		for (Person person : participants.values()) {
			defaultModel.addElement(person);
		}

		getModel().setParticipants(participants);
	}
/*	public void setRoom(Room room) {
		roomNumberField.setText(room.getName());
	}
*/
	@Override
	public void setModel(ChronosModel model) {
		this.model = (EventConfigModel) model;
		this.model.setView(this);
	}

	public EventConfigModel getModel() {
		return model;
	}

	private class StartTimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	private class DurationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	private class ApplyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.newCalEvent(view);
		}
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

	public JComboBox getDuration() {
		return duration;
	}

	public JComboBox getStartTime() {
		return startTime;
	}

	public JTextComponent getStartTimeField() {
		return startTimeField;
	}

	public JLabel getCreatorField() {
		return creatorField;
	}

	public ViewType getViewType() {
		return type;
	}
}
