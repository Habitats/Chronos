package client.gui.view.calendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.gui.GBC;
import client.gui.view.CalendarWindow;
import client.model.CalendarModel;
import client.model.EventConfigModel.ViewType;

import chronos.DateManagement;
import chronos.Singleton;

import events.CalEvent;

public class CalEventPanel extends JPanel {

	private CalEvent calEvent;
	private CalendarWindow view;
	private Color color;
	private Color hoverColor;
	private final CalendarModel model;
	private final CalEvent event;

	public CalEventPanel(CalEvent event, CalendarWindow view, Color personColor, CalendarModel model) {
		super();
		this.event = event;
		this.model = model;
		setLayout(new GridBagLayout());
		color = personColor;
		setBackground(color);
		hoverColor = personColor.darker();
		addMouseListener(new CalPanelMouseAdapter());
		setBorder(BorderFactory.createLineBorder(Color.white, 2));

		JLabel title = new JLabel(event.getTitle());
		title.setMaximumSize(new Dimension(130, 20));
		add(title, new GBC(0, 0).setInsets(5, 10, 0, 5));

		JLabel creator = new JLabel("- " + event.getCreator().toString());
		JLabel startDate = new JLabel(DateManagement.getFormattedFull(event.getStart()));
		add(creator, new GBC(0, 1).setInsets(0, 20, 5, 10));
		add(startDate, new GBC(0, 2).setInsets(3, 10, 5, 5));
		add(new JLabel(), new GBC(0, 3).setWeight(1, 1));

		this.calEvent = event;
		this.view = view;
		tilesLayout();
	}

	private void gridLayout() {
		setPreferredSize(new Dimension(110, 60));

	}

	private void tilesLayout() {
		setPreferredSize(new Dimension(130, 60 + (event.getDuration() * 20)));
		setMinimumSize((new Dimension(130, 60 + (event.getDuration() * 20))));
		setMaximumSize((new Dimension(1000, 60 + (event.getDuration() * 20))));
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}

	private class CalPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			view.getFrame().getEventModel().setCalEvent(calEvent);
			if (calEvent.getCreator().getUsername().toLowerCase().equals(Singleton.getInstance().getSelf().getUsername().toLowerCase()))
				view.getFrame().getEventModel().setView(ViewType.UPDATE);
			else if (calEvent.getParticipants().get(Singleton.getInstance().getSelf().getUsername()) != null && model.getSelectedPersonsEvents().get(Singleton.getInstance().getSelf().getUsername()).contains(calEvent))
				view.getFrame().getEventModel().setView(ViewType.PARTICIPANT);
			else
				view.getFrame().getEventModel().setView(ViewType.OTHER);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(hoverColor);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(color);
		}
	}
}