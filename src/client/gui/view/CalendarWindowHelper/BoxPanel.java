package client.gui.view.CalendarWindowHelper;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoxPanel extends JPanel {
	
	public BoxPanel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
	}

}
