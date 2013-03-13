package client.gui.view.CalendarWindowHelper;

import java.awt.Dimension;

import javax.swing.JButton;

public class ChangeWeekButton extends JButton {
	
	public ChangeWeekButton(String text) {
		
		super(text);
		this.setPreferredSize(new Dimension(45, 35));
		this.setMinimumSize(new Dimension(45,35));
		
	}

}
