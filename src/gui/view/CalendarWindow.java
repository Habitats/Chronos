package gui.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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
	JScrollPane kalenderPane;
	JPanel kalender;
	JPanel mandagPanel;
	JPanel tirsdagPanel;
	JPanel onsdagPanel;
	JPanel torsdagPanel;
	JPanel fredagPanel;
	JPanel lordagPanel;
	JPanel sondagPanel;
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
		
		newEventButton = new JButton("Ny avtale");
		add(newEventButton, new GBC(0,0).setFill(GridBagConstraints.BOTH).setInsets(0, 0, 5, 5));
		
		prevButton = new JButton("<");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 0;
		add(prevButton, gbc_button);
		
		nextButton = new JButton(">");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		add(nextButton, gbc_btnNewButton);
		
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
		
		kalenderPane = new JScrollPane();
		GridBagConstraints gbc_kalenderPane = new GridBagConstraints();
		gbc_kalenderPane.fill = GridBagConstraints.BOTH;
		gbc_kalenderPane.gridheight = 2;
		gbc_kalenderPane.gridwidth = 2;
		gbc_kalenderPane.gridx = 2;
		gbc_kalenderPane.gridy = 1;
		add(kalenderPane, gbc_kalenderPane);
		
		kalender = new JPanel();
		kalenderPane.setViewportView(kalender);
		kalender.setBackground(Color.LIGHT_GRAY);
		kalender.setLayout(new GridLayout(0, 7, 0, 0));
		
		mandagPanel = new JPanel();
		mandagPanel.setBackground(Color.WHITE);
		kalender.add(mandagPanel);
		mandagPanel.setLayout(new BoxLayout(mandagPanel, BoxLayout.Y_AXIS));
		
		tirsdagPanel = new JPanel();
		tirsdagPanel.setBackground(Color.WHITE);
		kalender.add(tirsdagPanel);
		tirsdagPanel.setLayout(new BoxLayout(tirsdagPanel, BoxLayout.Y_AXIS));
		
		onsdagPanel = new JPanel();
		onsdagPanel.setBackground(Color.WHITE);
		kalender.add(onsdagPanel);
		onsdagPanel.setLayout(new BoxLayout(onsdagPanel, BoxLayout.Y_AXIS));
		
		torsdagPanel = new JPanel();
		torsdagPanel.setBackground(Color.WHITE);
		kalender.add(torsdagPanel);
		torsdagPanel.setLayout(new BoxLayout(torsdagPanel, BoxLayout.Y_AXIS));
		
		fredagPanel = new JPanel();
		fredagPanel.setBackground(Color.WHITE);
		kalender.add(fredagPanel);
		fredagPanel.setLayout(new BoxLayout(fredagPanel, BoxLayout.Y_AXIS));
		
		lordagPanel = new JPanel();
		lordagPanel.setBackground(Color.WHITE);
		kalender.add(lordagPanel);
		lordagPanel.setLayout(new BoxLayout(lordagPanel, BoxLayout.Y_AXIS));
		
		sondagPanel = new JPanel();
		sondagPanel.setBackground(Color.WHITE);
		kalender.add(sondagPanel);
		sondagPanel.setLayout(new BoxLayout(sondagPanel, BoxLayout.Y_AXIS));
		
		JPanel tid = new JPanel();
		kalenderPane.setRowHeaderView(tid);
		tid.setLayout(new BoxLayout(tid, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("00.00");
		tid.add(lblNewLabel);
		
		JPanel ukedager = new JPanel();
		kalenderPane.setColumnHeaderView(ukedager);
		ukedager.setLayout(new GridLayout(0, 7, 0, 0));
		
		JLabel lblMandag = new JLabel("Mandag");
		ukedager.add(lblMandag);
		
		JLabel lblTirsdag = new JLabel("Tirsdag");
		ukedager.add(lblTirsdag);
		
		JLabel lblOnsdag = new JLabel("Onsdag");
		ukedager.add(lblOnsdag);
		
		JLabel lblTorsdag = new JLabel("Torsdag");
		ukedager.add(lblTorsdag);
		
		JLabel lblFredag = new JLabel("Fredag");
		ukedager.add(lblFredag);
		
		JLabel lblLordag = new JLabel("Lordag");
		ukedager.add(lblLordag);
		
		JLabel lblSondag = new JLabel("Sondag");
		ukedager.add(lblSondag);
		
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
}