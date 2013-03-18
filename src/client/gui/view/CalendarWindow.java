package client.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import chronos.DateManagement;
import chronos.Person;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.gui.view.CalendarWindowHelper.BoxPanel;
import client.gui.view.CalendarWindowHelper.CalEventListPanel;
import client.gui.view.CalendarWindowHelper.CalEventPanel;
import client.gui.view.CalendarWindowHelper.CalLabel;
import client.gui.view.CalendarWindowHelper.ChangeWeekButton;
import client.gui.view.CalendarWindowHelper.DayPanel;
import client.gui.view.CalendarWindowHelper.NotificationPanel;
import client.gui.view.CalendarWindowHelper.PersonCheckBox;
import client.model.CalendarModel;
import client.model.CalendarModel.Weekday;
import client.model.ChronosModel;
import events.CalEvent;
import events.CalEvent.CalEventType;

public class CalendarWindow extends ChronosWindow {

	private CalendarModel model;
	private JButton newEventButton;
	private JPanel weekPanel;
	private ChangeWeekButton prevButton, nextButton;
	private JScrollPane eventsPane, othersCalPane, notificationsPane, weekScrollPane;
	private BoxPanel eventsPanel, othersCalPanel, notificationsPanel;
	private DayPanel mondayPanel, tuesdayPanel, wednesdayPanel, thursdayPanel, fridayPanel, saturdayPanel, sundayPanel;
	private CalLabel dateLbl, weekNumberLbl, mondayLbl, tuesdayLbl, wednesdayLbl, thursdayLbl, fridayLbl, saturdayLbl, sundayLbl;
	private int eventsPanelHeight = 250;
	private int eventsPanelWidth = 140;

	public CalendarWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);

		int i = 0;

		newEventButton = new JButton("New event");
		newEventButton.setPreferredSize(new Dimension(140, 25));
		newEventButton.setMinimumSize(new Dimension(140, 25));
		add(newEventButton, new GBC(i, 0));
		newEventButton.addActionListener(new NewEventListener());

		JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane, new GBC(i, 3));

		eventsPanel = new BoxPanel();
		eventsPane = new JScrollPane(eventsPanel);

		notificationsPanel = new BoxPanel();
		notificationsPane = new JScrollPane(notificationsPanel);

		// CalLabel eventsLbl = new CalLabel("Events");
		// eventsPane.setColumnHeaderView(eventsLbl);
		// eventsPane.setPreferredSize(new Dimension(eventsPanelWidth,
		// eventsPanelHeight));
		// eventsPane.setMinimumSize(new Dimension(eventsPanelWidth,
		// eventsPanelHeight));
		// eventsPane.setBorder(border);
		// add(eventsPane, new GBC(i, 3));
		tabbedPane.add(eventsPane);
		tabbedPane.add(notificationsPane);
		tabbedPane.setTitleAt(0, "Events");
		tabbedPane.setTitleAt(1, "Not");
		tabbedPane.setMaximumSize(new Dimension(eventsPanelWidth, eventsPanelHeight));
		tabbedPane.setMinimumSize(new Dimension(eventsPanelWidth, eventsPanelHeight));
		tabbedPane.setPreferredSize(new Dimension(eventsPanelWidth, eventsPanelHeight));

		notificationsPanel.add(new NotificationPanel(new CalEvent("Jostein", new Date(), 10, new Person("Jossi"), "hehehhehe"), this, eventsPanelWidth));
		Border border = BorderFactory.createLineBorder(Color.white, 3);
		othersCalPanel = new BoxPanel();

		CalLabel othersLbl = new CalLabel("Others");

		othersCalPane = new JScrollPane(othersCalPanel);
		othersCalPane.setColumnHeaderView(othersLbl);
		othersCalPane.setPreferredSize(new Dimension(eventsPanelWidth, 250));
		othersCalPane.setMinimumSize(new Dimension(eventsPanelWidth, 250));
		othersCalPane.setBorder(border);
		add(othersCalPane, new GBC(i, 4));

		i++;
		i++;
		i++;

		weekPanel = new JPanel();
		weekPanel.setLayout(new GridBagLayout());
		weekPanel.setPreferredSize(new Dimension(140 * 7 - 19, 500));
		weekPanel.setMaximumSize(new Dimension(140 * 7 - 19, 500));
		weekPanel.setMinimumSize(new Dimension(140 * 7 - 19, 500));
		weekScrollPane = new JScrollPane(weekPanel);
		weekScrollPane.setMinimumSize(new Dimension(140 * 7, 500));
		weekScrollPane.setPreferredSize(new Dimension(140 * 7, 500));
		weekScrollPane.setMaximumSize(new Dimension(140 * 7, 500));
		add(weekScrollPane, new GBC(i, 3).setSpan(7, 2));

		mondayPanel = new DayPanel();
		weekPanel.add(mondayPanel, new GBC(i, 3, Align.NOT_RIGHT).setSpan(1, 2));

		mondayLbl = new CalLabel("Monday");
		add(mondayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		i++;

		tuesdayPanel = new DayPanel();
		weekPanel.add(tuesdayPanel, new GBC(i, 3, Align.TOP_AND_BOTTOM).setSpan(1, 2));

		tuesdayLbl = new CalLabel("Tuesday");
		add(tuesdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		i++;

		wednesdayPanel = new DayPanel();
		weekPanel.add(wednesdayPanel, new GBC(i, 3, Align.TOP_AND_BOTTOM).setSpan(1, 2));

		wednesdayLbl = new CalLabel("Wednesday");
		add(wednesdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		prevButton = new ChangeWeekButton("<");
		prevButton.addActionListener(new PrevButtonListener());
		add(prevButton, new GBC(i, 0).setAnchor(GridBagConstraints.EAST).setFill(GridBagConstraints.NONE));

		i++;

		thursdayPanel = new DayPanel();
		weekPanel.add(thursdayPanel, new GBC(i, 3, Align.TOP_AND_BOTTOM).setSpan(1, 2));

		thursdayLbl = new CalLabel("Thursday");
		add(thursdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		weekNumberLbl = new CalLabel("Week " + this.model.getCurrentDisplayedWeek());
		add(weekNumberLbl, new GBC(i, 0).setAnchor(GridBagConstraints.CENTER));

		i++;

		fridayPanel = new DayPanel();
		weekPanel.add(fridayPanel, new GBC(i, 3, Align.TOP_AND_BOTTOM).setSpan(1, 2));

		fridayLbl = new CalLabel("Friday");
		add(fridayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		nextButton = new ChangeWeekButton(">");
		nextButton.addActionListener(new NextButtonListener());
		add(nextButton, new GBC(i, 0).setAnchor(GridBagConstraints.WEST).setFill(GridBagConstraints.NONE));

		i++;

		saturdayPanel = new DayPanel();
		weekPanel.add(saturdayPanel, new GBC(i, 3, Align.TOP_AND_BOTTOM).setSpan(1, 2));

		saturdayLbl = new CalLabel("Saturday");
		add(saturdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		dateLbl = new CalLabel(this.model.getCurrentDisplayedDateIntervall());
		dateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		add(dateLbl, new GBC(i, 0).setSpan(2, 1).setAnchor(GridBagConstraints.WEST));

		i++;

		sundayPanel = new DayPanel();
		weekPanel.add(sundayPanel, new GBC(i, 3, Align.NOT_LEFT).setSpan(1, 2));

		sundayLbl = new CalLabel("Sunday");
		add(sundayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		updateLabels();
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (CalendarModel) model;
		this.model.setView(this);
	}

	public void addOtherPerson(Person person, boolean isSelected) {
		PersonCheckBox box = new PersonCheckBox(person, isSelected);
		box.addActionListener(new CheckBoxListener());
		othersCalPanel.add(box);
		repaint();
	}

	public void addEvent(CalEvent event, Weekday weekday) {
		CalEventPanel panel = new CalEventPanel(event, this);
		eventsPanel.add(new CalEventListPanel(event, this, eventsPane.getWidth() - 19));
		switch (weekday) {
		case MONDAY:
			mondayPanel.add(panel);
			break;
		case TUESDAY:
			tuesdayPanel.add(panel);
			break;
		case WEDNESDAY:
			wednesdayPanel.add(panel);
			break;
		case THURSDAY:
			thursdayPanel.add(panel);
			break;
		case FRIDAY:
			fridayPanel.add(panel);
			break;
		case SATURDAY:
			saturdayPanel.add(panel);
			break;
		case SUNDAY:
			sundayPanel.add(panel);
			break;
		default:
			break;
		}

		getFrame().pack();
		repaint();
	}

	public void removeEvents() {
		mondayPanel.removeAll();
		tuesdayPanel.removeAll();
		wednesdayPanel.removeAll();
		thursdayPanel.removeAll();
		fridayPanel.removeAll();
		saturdayPanel.removeAll();
		sundayPanel.removeAll();
		eventsPanel.removeAll();
		notificationsPanel.removeAll();
		// updateUI();
		repaint();
	}

	public void updateLabels() {
		Date currentDate = model.getCurrentDisplayedDate();
		weekNumberLbl.setText("Week " + model.getCurrentDisplayedWeek());
		dateLbl.setText(model.getCurrentDisplayedDateIntervall());
		mondayLbl.setText("Monday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		tuesdayLbl.setText("Tuesday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		wednesdayLbl.setText("Wednesday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		thursdayLbl.setText("Thursday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		fridayLbl.setText("Friday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		saturdayLbl.setText("Saturday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		sundayLbl.setText("Sunday " + DateManagement.getFormattedSimple(currentDate));
		currentDate = DateManagement.getNextDay(currentDate);
		repaint();

	}

	private class NewEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((EventConfigWindow) getFrame().getEventConfigWindow()).getModel().setDefaultModel();
			((EventConfigWindow) getFrame().getEventConfigWindow()).setVisible(true, CalEventType.NEW);
		}
	}

	private class CheckBoxListener implements ActionListener {

		public void itemStateChanged(ItemEvent e) {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			PersonCheckBox checkBox = (PersonCheckBox) e.getSource();
			Person person = checkBox.getPerson();
			System.out.println("Clicked: " + checkBox.getPerson());
			if (checkBox.isSelected()) {
				model.addSelectedPerson(person);
			} else {
				model.removeSelectedPerson(person);
				model.update();
			}

		}
	}

	private class PrevButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.prevWeek();
			model.update();
		}

	}

	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.nextWeek();
			model.update();
		}
	}

	public void addNotification(CalEvent calEvent) {
		notificationsPanel.add(new NotificationPanel(calEvent, this, eventsPane.getWidth() - 19));
	}

	public void internalRepaint() {
		repaint();

	}

	public void removePersonCheckBoxes() {
		othersCalPanel.removeAll();
		repaint();
	}
}