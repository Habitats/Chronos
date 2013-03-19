package client.gui.view;

import java.awt.Dimension;

import javax.swing.JButton;

public class EditConfigButton extends JButton {
	public EditConfigButton(String name) {
		this(name, null);
	}

	public EditConfigButton(String name, Dimension dim) {
		setText(name);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
	}
}
