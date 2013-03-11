package client.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.view.CalendarWindowHelper.BoxPanel;
import client.gui.view.CalendarWindowHelper.CalLabel;
import client.gui.view.CalendarWindowHelper.ChangeWeekButton;
import client.gui.view.CalendarWindowHelper.DayPanel;
import client.model.CalendarModel;
import client.model.ChronosModel;


public class CalendarWindow extends ChronosWindow {

	private CalendarModel model;
	private JButton newEventButton;
	private ChangeWeekButton prevButton;
	private ChangeWeekButton nextButton;
	private JScrollPane eventsPane;
	private BoxPanel eventsPanel;
	private JScrollPane calendarPane;
	private DayPanel mondayPanel;
	private DayPanel tuesdayPanel;
	private DayPanel wednesdayPanel;
	private DayPanel thursdayPanel;
	private DayPanel fridayPanel;
	private DayPanel saturdayPanel;
	private DayPanel sundayPanel;
	private BoxPanel othersCalPanel;
	private JScrollPane othersCalPane;

	public CalendarWindow(ChronosModel model) {
		setModel(model);
		
		
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		int i = 0;
		
		newEventButton = new JButton("New event");
		newEventButton.setPreferredSize(new Dimension(140,30));
		add(newEventButton, new GBC(i,0));
		
		eventsPanel = new BoxPanel();
		
		CalLabel eventsLbl = new CalLabel("Events");
		
		eventsPane = new JScrollPane(eventsPanel);
		eventsPane.setColumnHeaderView(eventsLbl);
		eventsPane.setPreferredSize(new Dimension(140, 250));
		eventsPane.setMinimumSize(new Dimension(140, 250));
		add(eventsPane, new GBC(i,3));
		
		othersCalPanel = new BoxPanel();
		
		CalLabel othersLbl = new CalLabel("Others");
		
		othersCalPane = new JScrollPane(othersCalPanel);
		othersCalPane.setColumnHeaderView(othersLbl);
		othersCalPane.setPreferredSize(new Dimension(140, 250));
		othersCalPane.setMinimumSize(new Dimension(140, 250));
		add(othersCalPane, new GBC(i,4));
		

		i++;
		i++;
		i++;
		
		
		mondayPanel = new DayPanel();
		add(mondayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel mondayLbl = new CalLabel("Monday");
		add(mondayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		i++;
		
		tuesdayPanel = new DayPanel();
		add(tuesdayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel tuesdayLbl = new CalLabel("Tuesday");
		add(tuesdayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		i++;
		
		wednesdayPanel = new DayPanel();
		add(wednesdayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel wednesdayLbl = new CalLabel("Wednesday");
		add(wednesdayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		prevButton = new ChangeWeekButton("<");
		add(prevButton, new GBC(i,0).setAnchor(GridBagConstraints.EAST).setFill(GridBagConstraints.NONE));
		
		i++;
		
		thursdayPanel = new DayPanel();
		add(thursdayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel thursdayLbl = new CalLabel("Thursday");
		add(thursdayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		JLabel weekNumberLbl = new CalLabel("Week ##");
		add(weekNumberLbl, new GBC(i, 0).setAnchor(GridBagConstraints.CENTER));
		
		i++;
		
		fridayPanel = new DayPanel();
		add(fridayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel fridayLbl = new CalLabel("Friday");
		add(fridayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		nextButton = new ChangeWeekButton(">");
		add(nextButton, new GBC(i,0).setAnchor(GridBagConstraints.WEST).setFill(GridBagConstraints.NONE));
		
		i++;
		
		saturdayPanel = new DayPanel();
		add(saturdayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel saturdayLbl = new CalLabel("Saturday");
		add(saturdayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		CalLabel dateLbl = new CalLabel("feb. 25 - mar 3. 2013");
		add(dateLbl, new GBC(i,0).setSpan(2, 1));
		
		i++;
		
		sundayPanel = new DayPanel();
		add(sundayPanel, new GBC(i,3,Align.NONE).setSpan(1, 2));
		
		JLabel sundayLbl = new CalLabel("Sunday");
		add(sundayLbl, new GBC(i,2).setAnchor(GridBagConstraints.CENTER));
		
		JCheckBox box1 = new JCheckBox("Patrick");
		othersCalPanel.add(box1);
		JCheckBox box2 = new JCheckBox("Patrick");
		othersCalPanel.add(box2);
		JCheckBox box3 = new JCheckBox("Patrick");
		othersCalPanel.add(box3);
		

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