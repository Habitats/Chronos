package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DayPanel extends JPanel {

	public DayPanel() {
		super();
		this.setPreferredSize(new Dimension(140,500-19));
		this.setMinimumSize(new Dimension(140,500-19));
		this.setBackground(Color.white);
		this.setLayout(new GridBagLayout());
	}

}
