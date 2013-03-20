package client.gui.view.calendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.gui.view.CalendarWindow;

public class CalLabel extends JLabel {

	private final CalendarWindow calendarWindow;

	public CalLabel(String text) {
		this(text, null);
	}

	public CalLabel(String text, CalendarWindow calendarWindow) {
		super(text);
//		setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.calendarWindow = calendarWindow;
		setPreferredSize(new Dimension(130, 20));
		setMaximumSize(new Dimension(130, 20));
		setMinimumSize(new Dimension(130, 20));

		this.setHorizontalAlignment(SwingConstants.CENTER);

	}
}
