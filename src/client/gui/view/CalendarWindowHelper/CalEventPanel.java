package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.gui.GBC;
import client.gui.view.CalendarWindow;
import client.gui.view.EventConfigWindow;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventPanel extends JPanel {

	private CalEvent calEvent;
	private CalendarWindow view;

	public CalEventPanel(CalEvent event, CalendarWindow view) {
		super();
		setLayout(new GridBagLayout());
		setBackground(Color.pink);
		setPreferredSize(new Dimension(130, 50));
		setMinimumSize(new Dimension(130, 50));
		setMaximumSize(new Dimension(130, 50));
		addMouseListener(new CalPanelMouseAdapter());
		setBorder(BorderFactory.createLineBorder(Color.white, 2));

		JLabel title = new JLabel(event.getTitle());
		title.setMaximumSize(new Dimension(130, 20));
		add(title, new GBC(0, 0).setIpad(0, 0).setWeight(1, 0));

		JLabel startDate = new JLabel(DateManagement.getFormattedDate(event.getStart()));
		add(startDate, new GBC(0, 1).setIpad(0, 0).setWeight(1, 0));

		this.calEvent = event;
		this.view = view;
	}

	private class CalPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).getModel().setCalEvent(calEvent);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).setVisible(true);

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