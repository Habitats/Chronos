package client.gui.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import client.gui.MainFrame;
import client.model.ChronosModel;

abstract public class ChronosWindow extends JPanel {

	private ChronosModel model;
	private final MainFrame frame;

	abstract public void setModel(ChronosModel model);

	public ChronosWindow(ChronosModel model, MainFrame frame) {
		this.frame = frame;
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	protected MainFrame getFrame() {
		return frame;
	}
	
}
