package gui;

import java.awt.GridBagLayout;

import gui.view.CalendarWindow;
import gui.view.LoginWindow;
import gui.view.ChronosWindow;

import javax.swing.JFrame;

import model.CalendarModel;
import model.ChronosModel;
import model.LoginModel;


import chronos.ClientController;

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
