package client.gui.view.eventConfig;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import chronos.Person;
import client.gui.GBC;
import client.gui.MainFrame;
import client.gui.GBC.Align;
import client.gui.view.ChronosWindow;
import client.gui.view.EditConfigButton;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import client.model.EventConfigModel.ViewType;
import events.CalEvent.CalEventType;

abstract public class EventWindow extends ChronosWindow {

	private String[] startTimeArray = { "00:00", "01:00", "02:00", "03:00", "04:00" };
	private Integer[] durationArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	protected JTextField eventNameField;
	protected JButton addParticipantButton, deleteParticipantButton, bookRoomButton, editButton, deleteButton, applyButton, cancelButton;
	protected JComboBox startTime, duration;
	protected JTextArea eventDescriptionArea;
	protected JTextField dateField;
	protected JTextField startTimeField;
	protected JCheckBox alert;
	protected JTextField roomNumberField;
	protected JList<Person> participantList;

	private Dimension bottomButtonDim = new Dimension(80, 20);
	private Dimension bigButtonDim = new Dimension(170, 20);
	protected EventConfigModel model;
	private EventWindow view;
	private ViewType type;

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
		eventDescriptionArea = new JTextArea();

		participantList = new JList<Person>();
		participantList.setEnabled(true);

		alert = new JCheckBox();

		roomNumberField = new JTextField("");
		roomNumberField.setEditable(false);

		// editButton = new EditConfigButton("Edit", bottomButtonDim);
		applyButton = new EditConfigButton("Apply", getBottomButtonDim());
		cancelButton = new EditConfigButton("Cancel", getBottomButtonDim());

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

		add(eventNameField, new GBC(0, 0).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 1));
		add(eventDescriptionArea, new GBC(0, 1).setAnchor(GridBagConstraints.FIRST_LINE_START).setSpan(2, 6).setWeight(1, 1));

		add(dateField, new GBC(2, 0).setAnchor(GridBagConstraints.FIRST_LINE_START));
		add(participantList, new GBC(2, 1).setSpan(1, 6).setWeight(1, 1));

		add(startTime, new GBC(3, 0).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE));
		add(new Label("Enable alert 15 min before"), new GBC(3, 1).setSpan(3, 1).setAnchor(GridBagConstraints.NORTH));

		add(new Label("Alert:"), new GBC(3, 2));
		add(new Label("Room no."), new GBC(3, 3).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE));

		// add(editButton, new GBC(0, 7));
		add(applyButton, new GBC(3, 7).setWeight(0.5, 0));
		add(cancelButton, new GBC(4, 7).setWeight(0.5, 0));

		add(duration, new GBC(4, 0));
		add(alert, new GBC(4, 2));
		add(roomNumberField, new GBC(4, 3));

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

	public Dimension getBigButtonDim() {
		return bigButtonDim;
	}

	public void setBigButtonDim(Dimension bigButtonDim) {
		this.bigButtonDim = bigButtonDim;
	}

	public Dimension getBottomButtonDim() {
		return bottomButtonDim;
	}

	public void setBottomButtonDim(Dimension bottomButtonDim) {
		this.bottomButtonDim = bottomButtonDim;
	}

	public JTextComponent getStartTimeField() {
		return startTimeField;
	}

	public ViewType getViewType() {
		return type;
	}
}
