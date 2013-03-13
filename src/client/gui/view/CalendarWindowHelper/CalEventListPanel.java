package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import chronos.DateManagement;

import events.CalEvent;

public class CalEventListPanel extends JLabel {

	public CalEventListPanel(CalEvent event) {
		this.setText(DateManagement.getFormattedSimple(event.getStart()) + " " + event.getTitle());
		this.setBackground(Color.white);

	}

	private class EventListPanelListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(Color.blue);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(Color.white);
		}

	}
}
