package gui.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import gui.GBC;
import model.CalendarModel;
import model.ChronosModel;

public class CalendarWindow extends ChronosWindow {

	private CalendarModel model;
	JButton newEventButton;
	JButton prevButton;
	JButton nextButton;
	JScrollPane eventsPane;
	JPanel eventsPanel;
	JScrollPane calendarPane;
	JPanel calendarPanel;
	JPanel mondayPanel;
	JPanel tuesdayPanel;
	JPanel wednesdayPanel;
	JPanel thursdayPanel;
	JPanel fridayPanel;
	JPanel saturdayPanel;
	JPanel sundayPanel;
	JPanel othersCalPanel;

	public CalendarWindow(ChronosModel model) {
		setModel(model);
		
		
		setForeground(Color.LIGHT_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{124, 0, 282, 385, 0};
		gridBagLayout.rowHeights = new int[]{38, 207, 207, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		newEventButton = new JButton("New event");
		newEventButton.addActionListener(new NewEventListener());
		add(newEventButton, new GBC(0,0).setFill(GridBagConstraints.BOTH).setInsets(0, 0, 5, 5));
		
		prevButton = new JButton("<");
		GridBagConstraints gbc_prevButton = new GridBagConstraints();
		gbc_prevButton.anchor = GridBagConstraints.EAST;
		gbc_prevButton.insets = new Insets(0, 0, 5, 5);
		gbc_prevButton.gridx = 2;
		gbc_prevButton.gridy = 0;
		add(prevButton, gbc_prevButton);
		
		nextButton = new JButton(">");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.anchor = GridBagConstraints.WEST;
		gbc_nextButton.insets = new Insets(0, 0, 5, 0);
		gbc_nextButton.gridx = 3;
		gbc_nextButton.gridy = 0;
		add(nextButton, gbc_nextButton);
		
		eventsPane = new JScrollPane();
		GridBagConstraints gbc_events = new GridBagConstraints();
		gbc_events.fill = GridBagConstraints.BOTH;
		gbc_events.insets = new Insets(0, 0, 5, 5);
		gbc_events.gridx = 0;
		gbc_events.gridy = 1;
		add(eventsPane, gbc_events);
		
		eventsPanel = new JPanel();
		eventsPanel.setBackground(Color.WHITE);
		eventsPane.setViewportView(eventsPanel);
		eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
		
		JLabel lblEvents = new JLabel("Events");
		lblEvents.setHorizontalAlignment(SwingConstants.CENTER);
		eventsPane.setColumnHeaderView(lblEvents);
		
		calendarPane = new JScrollPane();
		GridBagConstraints gbc_calendarPane = new GridBagConstraints();
		gbc_calendarPane.fill = GridBagConstraints.BOTH;
		gbc_calendarPane.gridheight = 2;
		gbc_calendarPane.gridwidth = 2;
		gbc_calendarPane.gridx = 2;
		gbc_calendarPane.gridy = 1;
		add(calendarPane, gbc_calendarPane);
		
		calendarPanel = new JPanel();
		calendarPane.setViewportView(calendarPanel);
		calendarPanel.setBackground(Color.LIGHT_GRAY);
		calendarPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		mondayPanel = new JPanel();
		mondayPanel.setBackground(Color.WHITE);
		calendarPanel.add(mondayPanel);
		mondayPanel.setLayout(new BoxLayout(mondayPanel, BoxLayout.Y_AXIS));
		
		tuesdayPanel = new JPanel();
		tuesdayPanel.setBackground(Color.WHITE);
		calendarPanel.add(tuesdayPanel);
		tuesdayPanel.setLayout(new BoxLayout(tuesdayPanel, BoxLayout.Y_AXIS));
		
		wednesdayPanel = new JPanel();
		wednesdayPanel.setBackground(Color.WHITE);
		calendarPanel.add(wednesdayPanel);
		wednesdayPanel.setLayout(new BoxLayout(wednesdayPanel, BoxLayout.Y_AXIS));
		
		thursdayPanel = new JPanel();
		thursdayPanel.setBackground(Color.WHITE);
		calendarPanel.add(thursdayPanel);
		thursdayPanel.setLayout(new BoxLayout(thursdayPanel, BoxLayout.Y_AXIS));
		
		fridayPanel = new JPanel();
		fridayPanel.setBackground(Color.WHITE);
		calendarPanel.add(fridayPanel);
		fridayPanel.setLayout(new BoxLayout(fridayPanel, BoxLayout.Y_AXIS));
		
		saturdayPanel = new JPanel();
		saturdayPanel.setBackground(Color.WHITE);
		calendarPanel.add(saturdayPanel);
		saturdayPanel.setLayout(new BoxLayout(saturdayPanel, BoxLayout.Y_AXIS));
		
		sundayPanel = new JPanel();
		sundayPanel.setBackground(Color.WHITE);
		calendarPanel.add(sundayPanel);
		sundayPanel.setLayout(new BoxLayout(sundayPanel, BoxLayout.Y_AXIS));
		
		JPanel tid = new JPanel();
		calendarPane.setRowHeaderView(tid);
		tid.setLayout(new BoxLayout(tid, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("00.00");
		tid.add(lblNewLabel);
		
		JPanel weekdays = new JPanel();
		calendarPane.setColumnHeaderView(weekdays);
		weekdays.setLayout(new GridLayout(0, 7, 0, 0));
		
		JLabel lblMonday = new JLabel("Monday");
		weekdays.add(lblMonday);
		
		JLabel lblTuesday = new JLabel("Tuesday");
		weekdays.add(lblTuesday);
		
		JLabel lblWednesday = new JLabel("Wednesday");
		weekdays.add(lblWednesday);
		
		JLabel lblThursday = new JLabel("Thursday");
		weekdays.add(lblThursday);
		
		JLabel lblFriday = new JLabel("Friday");
		weekdays.add(lblFriday);
		
		JLabel lblSaturday = new JLabel("Saturday");
		weekdays.add(lblSaturday);
		
		JLabel lblSunday = new JLabel("Sunday");
		weekdays.add(lblSunday);
		
		JScrollPane calendars = new JScrollPane();
		GridBagConstraints gbc_calendars = new GridBagConstraints();
		gbc_calendars.fill = GridBagConstraints.BOTH;
		gbc_calendars.insets = new Insets(0, 0, 0, 5);
		gbc_calendars.gridx = 0;
		gbc_calendars.gridy = 2;
		add(calendars, gbc_calendars);
		
		othersCalPanel = new JPanel();
		othersCalPanel.setBackground(Color.WHITE);
		calendars.setViewportView(othersCalPanel);
		othersCalPanel.setLayout(new BoxLayout(othersCalPanel, BoxLayout.Y_AXIS));
		
		JLabel lblOthers = new JLabel("Others");
		lblOthers.setLabelFor(calendars);
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthers.setBounds(65, 291, 99, 16);
		calendars.setColumnHeaderView(lblOthers);
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (CalendarModel) model;
	}
	public class NewEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
		}
		
	}
}