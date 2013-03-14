package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.gui.view.CalendarWindow;
import client.gui.view.EventConfigWindow;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventPanel extends JPanel {
	
	private CalEvent calEvent;
	private CalendarWindow view;
	
	public CalEventPanel(CalEvent event, CalendarWindow view) {
		super();
		CalLabel title = new CalLabel(event.getTitle());
		title.setPreferredSize(new Dimension(130, 20));
		title.setMinimumSize(new Dimension(130, 20));
		//title.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(title);
		this.add(new CalLabel(DateManagement.getFormattedDate(event.getStart())));
		this.setBackground(Color.pink);
		this.setPreferredSize(new Dimension(130, 50));
		this.setMinimumSize(new Dimension(130, 50));
		this.setMaximumSize(new Dimension(130, 50));
		this.addMouseListener(new CalPanelMouseAdapter());
		this.calEvent = event;
		this.view = view;
		
		setBorder(BorderFactory.createLineBorder(Color.white, 2));
	}

	private class CalPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((EventConfigWindow)view.getFrame().getEventConfigWindow()).getModel().setCalEvent(calEvent);
			((EventConfigWindow)view.getFrame().getEventConfigWindow()).setVisible(true);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(Color.red);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(Color.pink);
		}
	}
}