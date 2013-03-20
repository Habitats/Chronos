package client.gui.view.eventConfig;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import chronos.Person;
import chronos.Singleton;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.gui.view.ChronosWindow;
import client.gui.view.EditConfigButton;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import client.model.EventConfigModel.ViewType;

abstract public class EventWindow extends ChronosWindow {

	protected JTextField eventNameField;
	protected JButton addParticipantButton;
	protected JButton bookRoomButton;
	protected JCheckBox alert;
	protected JButton deleteButton;
	protected JButton applyButton;
	protected JComboBox<Integer> duration;
	protected JTextPane eventDescriptionArea;
	protected JSpinner startTime;
	protected JSpinner startDate;

	private Integer[] durationArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	private JButton cancelButton;
	private JTextField roomNumberField;
	private JList<Person> participantList;

	protected EventConfigModel model;
	private EventWindow view;
	private ViewType type;
	private JLabel participantsLbl;
	private JLabel descriptionLbl;
	private JLabel nameLbl;
	private JLabel durationLbl;
	private JLabel startDateLbl;
	private JLabel startTimeLbl;
	private JLabel creatorField;
	private int eventConfigWidth;
	private int eventConfigHeight;

	public EventWindow(ChronosModel model, MainFrame frame, ViewType type) {
		super(model, frame);
		setModel(model);
		setVisible(false);
		this.type = type;

		eventConfigWidth = 500;
		eventConfigHeight = 300;

		setBounds((frame.getFrameWidth() - eventConfigWidth) / 2, (frame.getFrameHeight() - eventConfigHeight) / 2, eventConfigWidth, eventConfigHeight);
		((EventConfigModel) model).getEventViews().put(type, this);
		// this is kind of sloppy but hey, it's used for the actionslisteners to
		// work properly
		view = this;

		startTime = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(startTime, "HH:mm");
		startTime.setEditor(timeEditor);

		startDate = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(startDate, "dd:MM:yyyy");
		startDate.setEditor(dateEditor);

		setLayout(new GridBagLayout());
		eventNameField = new JTextField();
		creatorField = new JLabel();
		eventDescriptionArea = new JTextPane();

		participantList = new JList<Person>();
		participantList.setEnabled(true);

		alert = new JCheckBox();

		roomNumberField = new JTextField("");
		roomNumberField.setEditable(false);

		applyButton = new EditConfigButton("Apply");
		cancelButton = new EditConfigButton("Cancel");

		eventNameField.setColumns(15);
		Border border = BorderFactory.createEmptyBorder(0, 3, 0, 3);
		eventNameField.setBorder(border);

		roomNumberField.setColumns(5);

		eventDescriptionArea.setPreferredSize(new Dimension(100, 100));
		eventDescriptionArea.setBorder(border);
		participantList.setPreferredSize(new Dimension(100, 100));

		duration = new JComboBox<Integer>(durationArray);
		duration.addActionListener(new DurationListener());

		applyButton.addActionListener(new ApplyAction());
		cancelButton.addActionListener(new CancelAction());

		nameLbl = new JLabel("Name");
		descriptionLbl = new JLabel("Description");
		participantsLbl = new JLabel("Participants");
		durationLbl = new JLabel("Duration (hours)");
		startDateLbl = new JLabel("Date");
		startTimeLbl = new JLabel("Time");

		add(nameLbl, new GBC(0, 0, Align.NOT_BOTTOM));
		add(startDateLbl, new GBC(2, 0, Align.NOT_BOTTOM));
		add(startTimeLbl, new GBC(4, 0, Align.NOT_BOTTOM));
		add(durationLbl, new GBC(5, 0, Align.NOT_BOTTOM));

		add(eventNameField, new GBC(0, 1, Align.NOT_BOTTOM).setSpan(2, 1));
		add(startDate, new GBC(2, 1, Align.NOT_BOTTOM).setSpan(2, 1));
		add(startTime, new GBC(4, 1, Align.NOT_BOTTOM));
		add(duration, new GBC(5, 1, Align.NOT_BOTTOM));

		add(descriptionLbl, new GBC(0, 2, Align.NOT_BOTTOM));
		add(participantsLbl, new GBC(2, 2, Align.NOT_BOTTOM));
		add(new JLabel("Enable alert 15 min before"), new GBC(4, 2, Align.NOT_BOTTOM).setSpan(2, 1));

		add(eventDescriptionArea, new GBC(0, 3).setSpan(2, 6).setWeight(1, 1));
		add(participantList, new GBC(2, 3).setSpan(2, 6).setWeight(1, 1));

		add(new JLabel("Alert:"), new GBC(4, 3));
		add(alert, new GBC(5, 3));
		add(new JLabel("Location:"), new GBC(4, 4));
		add(roomNumberField, new GBC(5, 4));

		add(new JLabel("Creator:"), new GBC(4, 5));
		add(creatorField, new GBC(5, 5));

		add(applyButton, new GBC(4, 9).setWeight(0.5, 0));
		add(cancelButton, new GBC(5, 9).setWeight(0.5, 0));

		// sets the default model (IE. empty)
		this.model.setDefaultModel();
	}

	public void setParticipants(HashMap<String, Person> participants) {
		DefaultListModel<Person> defaultModel = new DefaultListModel<Person>();
		participantList.setModel(defaultModel);
		participantList.setCellRenderer(new PersonRenderer());

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

	private class PersonRenderer extends JLabel implements ListCellRenderer<Person> {

		@Override
		public Component getListCellRendererComponent(JList<? extends Person> list, Person person, int index, boolean isSelected, boolean cellHasFocus) {
			setFont(Singleton.FONT_BOLD);
			setText(person.toString());
			switch (person.getStatus()) {
			case ACCEPTED:
				setForeground(Singleton.GREEN);
				break;
			case DECLINED:
				setForeground(Singleton.RED);
				break;
			case WAITING:
				setForeground(Singleton.BLUE);
			}
			return this;
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (!aFlag && (getFrame().getAddParticipantWindow() != null && getFrame().getRoomBookingWindow() != null)) {
			getFrame().getAddParticipantWindow().setVisible(false);
			getFrame().getRoomBookingWindow().setVisible(false);
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

	public JSpinner getStartTime() {
		return startTime;
	}

	public JSpinner getStartDate() {
		return startDate;
	}

	public JTextField getRoomNumberField() {
		return roomNumberField;
	}

	public JTextPane getEventDescriptionArea() {
		return eventDescriptionArea;
	}

	public JList<Person> getParticipantList() {
		return participantList;
	}

	public JCheckBox getAlert() {
		return alert;
	}

	public JComboBox<Integer> getDuration() {
		return duration;
	}

	public JLabel getCreatorField() {
		return creatorField;
	}

	public ViewType getViewType() {
		return type;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBounds((getFrame().getFrameWidth() - eventConfigWidth) / 2, (getFrame().getFrameHeight() - eventConfigHeight) / 2, eventConfigWidth, eventConfigHeight);
		revalidate();
	}
}
