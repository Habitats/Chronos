package client.gui.view.calendarWindowHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.gui.GBC;
import client.gui.view.CalendarWindow;

public class DayPanel extends JPanel {

	private final CalendarWindow calendarWindow;
	private boolean gridLayout;
	private boolean tilesLayout;

	public DayPanel(JPanel calendarWindow) {
		super();
		this.calendarWindow = (CalendarWindow) calendarWindow;
		setBackground(Color.white);

		tilesLayout();
//		gridLayout();
	}

	private void tilesLayout() {
		tilesLayout = true;
		this.setPreferredSize(new Dimension(140, 60 * 25));
		this.setMaximumSize(new Dimension(140, 60 * 25));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	private void gridLayout() {
		gridLayout = true;
		this.setMinimumSize(new Dimension(140, 60 * 25));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridBagLayout());
	}

	public void defineGrid() {
		if (!gridLayout)
			return;

		for (int i = 0; i <= 24; i++) {
			JLabel hourGrid = new JLabel();
			hourGrid.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
			hourGrid.setPreferredSize(new Dimension(20, 60));
			hourGrid.setMinimumSize(new Dimension(20, 60));
			hourGrid.setMaximumSize(new Dimension(20, 60));
			hourGrid.setText(" " + Integer.toString(i));
			add(hourGrid, new GBC(0, i).setInsets(0, 0, 0, 0));
		}
	}

}
