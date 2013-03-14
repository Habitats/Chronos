package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.gui.view.CalendarWindow;
import client.gui.view.EventConfigWindow;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventListPanel extends JLabel {

	private CalEvent calEvent;
	private CalendarWindow view;

	public CalEventListPanel(CalEvent calEvent, CalendarWindow view, int width) {
		super();
		String text = DateManagement.getFormattedSimple(calEvent.getStart()) + " " + calEvent.getTitle();
		setText(text);
		setHorizontalAlignment(SwingConstants.LEFT);
		setOpaque(true);

		setPreferredSize(new Dimension(width, 0));
		setMinimumSize(new Dimension(width, 0));
		setMaximumSize(new Dimension(width,19));

		setBackground(Color.white);
		addMouseListener(new EventListPanelListener());
		setAlignmentX(LEFT_ALIGNMENT);
		this.calEvent = calEvent;
		this.view = view;
	}

	private class EventListPanelListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).getModel().setCalEvent(calEvent);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).setVisible(true);
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
