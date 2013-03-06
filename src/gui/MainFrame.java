package gui;

import javax.swing.JFrame;

import model.CalendarModel;
import model.ChronosModel;
import model.LoginModel;

import view.CalendarWindow;
import view.ChronosWindow;
import view.LoginWindow;

import chronos.ClientController;

public class MainFrame extends JFrame {

	private final ClientController controller;

	public MainFrame(ClientController controller) {
		this.controller = controller;
		ChronosModel loginModel = new LoginModel(controller);
		ChronosWindow loginWindow = new LoginWindow(loginModel);

		ChronosModel calendarModel = new CalendarModel(controller);
		ChronosWindow calendarWindow = new CalendarWindow(calendarModel);
	}

	// initialize the GUI here
	public void buildGui() {
	}
}
