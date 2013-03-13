package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventPanel extends JPanel {

	public CalEventPanel(CalEvent event) {
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
	}

	private class CalPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);

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