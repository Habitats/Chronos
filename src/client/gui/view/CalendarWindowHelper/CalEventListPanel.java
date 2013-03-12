package client.gui.view.CalendarWindowHelper;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventListPanel extends JLabel {

	public CalEventListPanel(CalEvent event) {
		this.setText(DateManagement.getFormattedSimple(event.getStart()));
	}

}
