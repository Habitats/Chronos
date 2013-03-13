package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventListPanel extends JPanel {

	public CalEventListPanel(CalEvent event) {
		super();
		String text = DateManagement.getFormattedSimple(event.getStart()) + " " + event.getTitle();
		CalLabel label = new CalLabel(text);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(120, 20));
		this.setMinimumSize(new Dimension(120, 20));
		this.setMaximumSize(new Dimension(120, 20));
		this.addMouseListener(new EventListPanelListener());
		this.add(label);

	}

	private class EventListPanelListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(Color.yellow);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(Color.white);
		}

	}
}
