package client.gui.view.CalendarWindowHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chronos.DateManagement;

import events.CalEvent;

public class CalEventPanel extends JPanel {
	
	
	
	public CalEventPanel(CalEvent event) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel(event.getTitle()));
		this.add(new JLabel(DateManagement.getFormatted(event.getStart())));
		this.setBackground(Color.pink);
		this.setPreferredSize(new Dimension(130,50));
		this.addMouseListener(new CalPanelMouseAdapter());
	}
	private class CalPanelMouseAdapter extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		
	}
	@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			setBackground(Color.red);
			
		}
	@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			setBackground(Color.pink);
		}
}
}