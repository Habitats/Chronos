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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import client.gui.GBC;
import client.model.CalendarModel;
import client.model.ChronosModel;


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
	JScrollPane othersCalPane;

	public CalendarWindow(ChronosModel model) {
		setModel(model);
		
		
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		newEventButton = new JButton("New event");
		newEventButton.setPreferredSize(new Dimension(140,30));
		add(newEventButton, new GBC(0,0));
		
		eventsPane = new JScrollPane();
		eventsPane.setPreferredSize(new Dimension(140, 250));
		eventsPane.setMinimumSize(new Dimension(140, 250));
		add(eventsPane, new GBC(0,3));
		
		othersCalPane = new JScrollPane();
		othersCalPane.setPreferredSize(new Dimension(140, 250));
		othersCalPane.setMinimumSize(new Dimension(140, 250));
		add(othersCalPane, new GBC(0,4));
		
		mondayPanel = new JPanel();
		mondayPanel.setPreferredSize(new Dimension(140,500));
		mondayPanel.setMinimumSize(new Dimension(140, 250));
		mondayPanel.setBackground(Color.white);
		add(mondayPanel, new GBC(3,3).setSpan(1, 2));
		
		tuesdayPanel = new JPanel();
		tuesdayPanel.setPreferredSize(new Dimension(140,500));
		tuesdayPanel.setMinimumSize(new Dimension(140, 250));
		tuesdayPanel.setBackground(Color.white);
		add(tuesdayPanel, new GBC(4,3).setSpan(1, 2));
		
		wednesdayPanel = new JPanel();
		wednesdayPanel.setPreferredSize(new Dimension(140,500));
		wednesdayPanel.setMinimumSize(new Dimension(140, 250));
		wednesdayPanel.setBackground(Color.white);
		add(wednesdayPanel, new GBC(5,3).setSpan(1, 2));
		
		thursdayPanel = new JPanel();
		thursdayPanel.setPreferredSize(new Dimension(140,500));
		thursdayPanel.setMinimumSize(new Dimension(140, 250));
		thursdayPanel.setBackground(Color.white);
		add(thursdayPanel, new GBC(6,3).setSpan(1, 2));
		
		fridayPanel = new JPanel();
		fridayPanel.setPreferredSize(new Dimension(140,500));
		fridayPanel.setMinimumSize(new Dimension(140, 250));
		fridayPanel.setBackground(Color.white);
		add(fridayPanel, new GBC(7,3).setSpan(1, 2));
		
		saturdayPanel = new JPanel();
		saturdayPanel.setPreferredSize(new Dimension(140,500));
		saturdayPanel.setMinimumSize(new Dimension(140, 250));
		saturdayPanel.setBackground(Color.white);
		add(saturdayPanel, new GBC(8,3).setSpan(1, 2));
		
		sundayPanel = new JPanel();
		sundayPanel.setPreferredSize(new Dimension(140,500));
		sundayPanel.setMinimumSize(new Dimension(140, 250));
		sundayPanel.setBackground(Color.white);
		add(sundayPanel, new GBC(9,3).setSpan(1, 2));

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