package client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import client.ClientController;
import client.gui.view.CalendarWindow;
import client.gui.view.ChronosWindow;
import client.gui.view.EventConfigWindow;
import client.gui.view.InvitationWindow;
import client.gui.view.LoginWindow;
import client.gui.view.RoomBookingWindow;
import client.model.CalendarModel;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import client.model.InvitationModel;
import client.model.LoginModel;
import client.model.RoomBookingModel;

/**
 * The main GUI frame. Holds a layeredPane, each filled with it's individual
 * view
 * 
 * @author anon
 * 
 */
public class MainFrame extends JFrame {

	private ArrayList<ChronosWindow> panels;
	private final ClientController controller;

	private ChronosWindow calendarWindow;
	private ChronosModel calendarModel;

	private ChronosModel loginModel;
	private ChronosWindow loginWindow;

	private ChronosWindow invitationWindow;
	private InvitationModel invitationModel;

	private ChronosWindow eventConfigWindow;
	private ChronosModel eventConfigModel;

	private ChronosWindow roomBookingWindow;
	private RoomBookingModel roomBookingModel;

	private int frameWidth = 1150;
	private int frameHeight = 620;

	public MainFrame(ClientController controller) {
		this.controller = controller;

		panels = new ArrayList<ChronosWindow>();

		loginModel = new LoginModel(controller);
		loginWindow = new LoginWindow(loginModel, this);

		calendarModel = new CalendarModel(controller);
		calendarWindow = new CalendarWindow(calendarModel, this);

		invitationModel = new InvitationModel(controller);
		invitationWindow = new InvitationWindow(invitationModel, this);

		eventConfigModel = new EventConfigModel(controller);
		eventConfigWindow = new EventConfigWindow(eventConfigModel, this);

		roomBookingModel = new RoomBookingModel(controller);
		roomBookingWindow = new RoomBookingWindow(roomBookingModel, this);
	}

	private JLayeredPane buildLayeredPane() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.black);

		// ADD COMPONENTS
		layeredPane.add(calendarWindow, new Integer(0));
		layeredPane.add(eventConfigWindow, new Integer(2));
		layeredPane.add(invitationWindow, new Integer(4));
		layeredPane.add(loginWindow, new Integer(10));
		layeredPane.add(roomBookingWindow, new Integer(14));

		// SET BOUNDS ON EVERY COMPONENT ADDED DIRECTLY TO A LAYER
		calendarWindow.setBounds(0, 0, frameWidth, frameHeight);

		int eventConfigWidth = frameWidth / 2;
		int eventConfigHeight = frameHeight / 2;
		eventConfigWindow.setBounds((frameWidth - eventConfigWidth) / 2, (frameHeight - eventConfigHeight) / 2, eventConfigWidth, eventConfigHeight);

		int roomBookingWidth = frameWidth / 4;
		int roomBookingHeight = frameHeight / 2;
		roomBookingWindow.setBounds((frameWidth - roomBookingWidth) / 4, (frameHeight - roomBookingHeight) / 2, roomBookingWidth, roomBookingHeight);

		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		layeredPane.setOpaque(true);

		return layeredPane;
	}

	// initialize the GUI here
	public void buildGui() {
		JLayeredPane layeredPane = buildLayeredPane();

		getContentPane().add(layeredPane);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(getRootPane());
		setVisible(true);
		setResizable(false);
	}

	public ChronosWindow getEventConfigWindow() {
		return eventConfigWindow;
	}

	public ChronosWindow getRoomBookingWindow() {
		return roomBookingWindow;
	}
}
