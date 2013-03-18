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
import client.gui.GBC.Align;
import client.gui.view.CalendarWindow;
import client.gui.view.EventConfigWindow;

import chronos.DateManagement;

import events.CalEvent;
import events.CalEvent.CalEventType;

public class CalEventPanel extends JPanel {

	private CalEvent calEvent;
	private CalendarWindow view;
	private Color color;

	public CalEventPanel(CalEvent event, CalendarWindow view, int personColorNumber) {
		super();
		setLayout(new GridBagLayout());
		color = Color.getHSBColor((float) personColorNumber/20, 1, 1);
		setBackground(color);
		setPreferredSize(new Dimension(130, 50));
		setMinimumSize(new Dimension(130, 50));
		setMaximumSize(new Dimension(130, 50));
		addMouseListener(new CalPanelMouseAdapter());
		setBorder(BorderFactory.createLineBorder(Color.white, 2));

		JLabel title = new JLabel(event.getTitle());
		title.setMaximumSize(new Dimension(130, 20));
		add(title, new GBC(0, 0, Align.FULL_WIDTH).setWeight(1, 0));

		JLabel startDate = new JLabel(DateManagement.getFormattedDate(event.getStart()) + " - 13:37");
		add(startDate, new GBC(0, 1, Align.FULL_WIDTH_BOTTOM).setWeight(1, 0));

		this.calEvent = event;
		this.view = view;
	}
//	Method for creating n differen colors
//	public Color[] createColors(int n) {
//		Color[] cols = new Color[n];
//		for (int i = 0; i < n; i++)
//			cols[i] = Color.getHSBColor((float) i / n, 1, 1);
//		return cols;
//	}

	private class CalPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).getModel().setCalEvent(calEvent);
			((EventConfigWindow) view.getFrame().getEventConfigWindow()).setVisible(true, CalEventType.UPDATE);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(Color.pink);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(color);
		}
	}
}