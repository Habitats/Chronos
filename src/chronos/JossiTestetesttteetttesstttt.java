package chronos;

import gui.view.CalendarWindow;
import gui.view.EventConfigWindow;

import javax.swing.JFrame;

import model.CalendarModel;

public class JossiTestetesttteetttesstttt {
	
	
	
	public static void main(String[] args) {
		JFrame frame1 = new JFrame("MainCalView");
		CalendarWindow panel = new CalendarWindow(new CalendarModel(new ClientController()));
		EventConfigWindow panel1 = new EventConfigWindow();
		frame1.getContentPane().add(panel);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.pack();
		frame1.setVisible(true);
		
	}

}