package client.gui.view.CalendarWindowHelper;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NotificationPanel extends JPanel {

	JButton attendingButton;
	JButton notGoingToMakeItToTheMeetingButton;

	public NotificationPanel() {
		attendingButton = new JButton("Ja");
		notGoingToMakeItToTheMeetingButton = new JButton("Nei");
		add(attendingButton);
		add(notGoingToMakeItToTheMeetingButton);
	}

}
