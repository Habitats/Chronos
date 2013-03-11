package client.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import client.ClientController;
import client.gui.view.CalendarWindow;
import client.gui.view.ChronosWindow;
import client.gui.view.InvitationWindow;
import client.gui.view.LoginWindow;
import client.model.CalendarModel;
import client.model.ChronosModel;
import client.model.InvitationModel;
import client.model.LoginModel;




/**
 * The main GUI frame. Holds a layeredPane, each filled with it's individual view
 * @author anon
 *
 */
public class MainFrame extends JFrame {

	private ArrayList<ChronosWindow> panels;
	private final ClientController controller;
	private JLayeredPane layeredPane;
	
	private ChronosModel loginModel;
	
	private ChronosWindow calendarWindow;
	private ChronosWindow loginWindow;
	
	private ChronosModel calendarModel;
	private ChronosWindow invitationWindow;
	private InvitationModel invitationModel;
	
	public MainFrame(ClientController controller) {
		this.controller = controller;
		
		panels = new ArrayList<ChronosWindow>();
		
		loginModel = new LoginModel(controller);
		loginWindow = new LoginWindow(loginModel);

		calendarModel = new CalendarModel(controller);
		calendarWindow = new CalendarWindow(calendarModel);
		
		invitationModel = new InvitationModel(controller);
		invitationWindow = new InvitationWindow(invitationModel);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.black);
	}

	// initialize the GUI here
	public void buildGui() {
		
		layeredPane.add(calendarWindow,new Integer(0));
		calendarWindow.setBounds(0, 0, 900, 600);
		layeredPane.setSize(123, 123);
		layeredPane.setOpaque(true);
//		setSize(calendarWindow.getWidth(), calendarWindow.getHeight());
//		layeredPane.add(loginWindow, new Integer(1));
		
		getContentPane().add(layeredPane);
		
		setLocationRelativeTo(getRootPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
}
