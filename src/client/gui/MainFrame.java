package client.gui;

import java.awt.GridBagLayout;


import javax.swing.JFrame;

import client.ClientController;
import client.gui.view.CalendarWindow;
import client.gui.view.ChronosWindow;
import client.gui.view.LoginWindow;
import client.model.CalendarModel;
import client.model.ChronosModel;
import client.model.LoginModel;




/**
 * The main GUI frame. Holds a layeredPane, each filled with it's individual view
 * @author anon
 *
 */
public class MainFrame extends JFrame {

	private final ClientController controller;

	public MainFrame(ClientController controller) {
		this.controller = controller;
		ChronosModel loginModel = new LoginModel(controller);
		ChronosWindow loginWindow = new LoginWindow(loginModel);

		ChronosModel calendarModel = new CalendarModel(controller);
		ChronosWindow calendarWindow = new CalendarWindow(calendarModel);
		
		setLayout(new GridBagLayout());
		add(calendarWindow, new GBC(0,0));
	}

	// initialize the GUI here
	public void buildGui() {
	}
}
