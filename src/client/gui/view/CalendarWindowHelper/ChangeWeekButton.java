package client.gui.view.calendarWindowHelper;

import java.awt.Dimension;

import javax.swing.JButton;

public class ChangeWeekButton extends JButton {

	public ChangeWeekButton(String text) {
		super(text);
		this.setPreferredSize(new Dimension(45, 25));
		this.setMinimumSize(new Dimension(45, 25));
	}
}
