package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
import client.gui.view.CalendarWindowHelper.PersonCheckBox;
import client.model.CalendarModel;
import client.model.CalendarModel.Weekday;
import client.model.ChronosModel;
import events.CalEvent;

public class CalendarWindow extends ChronosWindow {

	private CalendarModel model;
	private JButton newEventButton;
	private ChangeWeekButton prevButton, nextButton;
	private JScrollPane eventsPane, othersCalPane;
	private BoxPanel eventsPanel, othersCalPanel;
	private DayPanel mondayPanel, tuesdayPanel, wednesdayPanel, thursdayPanel, fridayPanel, saturdayPanel, sundayPanel;
	private CalLabel dateLbl, weekNumberLbl, mondayLbl, tuesdayLbl, wednesdayLbl, thursdayLbl, fridayLbl, saturdayLbl, sundayLbl;

	public CalendarWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);

		int i = 0;

		newEventButton = new JButton("New event");
		newEventButton.setPreferredSize(new Dimension(140, 30));
		newEventButton.setMinimumSize(new Dimension(140, 30));
		add(newEventButton, new GBC(i, 0));
		newEventButton.addActionListener(new NewEventListener());

		eventsPanel = new BoxPanel();

		CalLabel eventsLbl = new CalLabel("Events");

		eventsPane = new JScrollPane(eventsPanel);
		eventsPane.setColumnHeaderView(eventsLbl);
		eventsPane.setPreferredSize(new Dimension(140, 250));
		eventsPane.setMinimumSize(new Dimension(140, 250));
		add(eventsPane, new GBC(i, 3));

		othersCalPanel = new BoxPanel();

		CalLabel othersLbl = new CalLabel("Others");

		othersCalPane = new JScrollPane(othersCalPanel);
		othersCalPane.setColumnHeaderView(othersLbl);
		othersCalPane.setPreferredSize(new Dimension(140, 250));
		othersCalPane.setMinimumSize(new Dimension(140, 250));
		add(othersCalPane, new GBC(i, 4));

		i++;
		i++;
		i++;

		mondayPanel = new DayPanel();
		add(mondayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		mondayLbl = new CalLabel("Monday");
		add(mondayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		i++;

		tuesdayPanel = new DayPanel();
		add(tuesdayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		tuesdayLbl = new CalLabel("Tuesday");
		add(tuesdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		i++;

		wednesdayPanel = new DayPanel();
		add(wednesdayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		wednesdayLbl = new CalLabel("Wednesday");
		add(wednesdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		prevButton = new ChangeWeekButton("<");
		prevButton.addActionListener(new PrevButtonListener());
		add(prevButton, new GBC(i, 0).setAnchor(GridBagConstraints.EAST).setFill(GridBagConstraints.NONE));

		i++;

		thursdayPanel = new DayPanel();
		add(thursdayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		thursdayLbl = new CalLabel("Thursday");
		add(thursdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		weekNumberLbl = new CalLabel("Week " + this.model.getCurrentDisplayedWeek());
		add(weekNumberLbl, new GBC(i, 0).setAnchor(GridBagConstraints.CENTER));

		i++;

		fridayPanel = new DayPanel();
		add(fridayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		fridayLbl = new CalLabel("Friday");
		add(fridayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		nextButton = new ChangeWeekButton(">");
		nextButton.addActionListener(new NextButtonListener());
		add(nextButton, new GBC(i, 0).setAnchor(GridBagConstraints.WEST).setFill(GridBagConstraints.NONE));

		i++;

		saturdayPanel = new DayPanel();
		add(saturdayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		saturdayLbl = new CalLabel("Saturday");
		add(saturdayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		dateLbl = new CalLabel(this.model.getCurrentDisplayedDateIntervall());
		add(dateLbl, new GBC(i, 0).setSpan(2, 1));

		i++;

		sundayPanel = new DayPanel();
		add(sundayPanel, new GBC(i, 3, Align.NONE).setSpan(1, 2));

		sundayLbl = new CalLabel("Sunday");
		add(sundayLbl, new GBC(i, 2).setAnchor(GridBagConstraints.CENTER));

		updateLabels();
		CalEvent calEvent = new CalEvent("Jostein", new Date(), 30, new Person("Per"), "jososososo");

	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (CalendarModel) model;
		((CalendarModel) model).setView(this);
	}

	public void addOtherPerson(Person person) {
		PersonCheckBox box = new PersonCheckBox(person);
		box.addItemListener(new CheckBoxListener());
		othersCalPanel.add(box);
	}

	public void addEvent(CalEvent event, Weekday weekday) {
		CalEventPanel panel = new CalEventPanel(event);
		eventsPanel.add(new CalEventListPanel(event));
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
		updateUI();
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

	}

	private class NewEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((EventConfigWindow) getFrame().getEventConfigWindow()).getModel().clearModel();
			getFrame().getEventConfigWindow().setVisible(true);
		}
	}

	private class CheckBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			Person person = ((PersonCheckBox) e.getItemSelectable()).getPerson();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				model.addSelectedPerson(person);
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
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
}