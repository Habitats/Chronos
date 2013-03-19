package client.gui.view.calendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.gui.view.CalendarWindow;
import client.model.EventConfigModel.ViewType;

import chronos.DateManagement;
import chronos.Singleton;

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

		setPreferredSize(new Dimension(width, 19));
		setMinimumSize(new Dimension(width, 19));
		setMaximumSize(new Dimension(width, 19));

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
			view.getFrame().getEventModel().setCalEvent(calEvent);
			if (calEvent.getCreator().getUsername().toLowerCase().equals(Singleton.getInstance().getSelf().getUsername()))
				view.getFrame().getEventModel().setView(ViewType.UPDATE);
			else
				view.getFrame().getEventModel().setView(ViewType.OTHER);
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
