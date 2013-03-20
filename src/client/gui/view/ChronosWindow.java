package client.gui.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import chronos.Singleton;
import client.gui.MainFrame;
import client.model.ChronosModel;

abstract public class ChronosWindow extends JPanel {
	protected static final long serialVersionUID = 8066589043144643860L;
	private final MainFrame frame;

	abstract public void setModel(ChronosModel model);

	public ChronosWindow(ChronosModel model, MainFrame frame) {
		this.frame = frame;
		setBorder(BorderFactory.createLineBorder(Color.black));
		setFont(Singleton.FONT_BOLD);
	}

	public MainFrame getFrame() {
		return frame;
	}

}
