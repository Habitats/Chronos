package client.gui.view.CalendarWindowHelper;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CalLabel extends JLabel {

	public CalLabel(String text) {

		super(text);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		setMinimumSize(new Dimension(140,0));
		setPreferredSize(new Dimension(140,0));
		setMaximumSize(new Dimension(140,0));
		
	}

}
