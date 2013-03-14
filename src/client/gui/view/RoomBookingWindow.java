package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;

import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.RoomBookingModel;

public class RoomBookingWindow extends ChronosWindow implements ActionListener {
	private JList roomList;
	private JButton bookButton, cancelButton, autobookButton;
	private RoomBookingModel model;

	public RoomBookingWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());

		roomList = new JList<>();
		bookButton = new JButton("Book");
		cancelButton = new JButton("Cancel");
		autobookButton = new JButton("Autobook");

		roomList.setPreferredSize(new Dimension(100, 100));
		roomList.setMinimumSize(new Dimension(100, 80));
		bookButton.setMinimumSize(new Dimension(100, 20));

		autobookButton.addActionListener(new AutoBookAction());
		bookButton.addActionListener(new BookAction());
		cancelButton.addActionListener(new CancelAction());

		add(new Label("Book room"), new GBC(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_START));
		add(roomList, new GBC(0, 1).setSpan(1, 6));
		add(autobookButton, new GBC(1, 1));
		add(bookButton, new GBC(0, 7));
		add(cancelButton, new GBC(1, 7));

		roomList.setPreferredSize(new Dimension(100, 100));

		autobookButton.addActionListener(new AutoBookAction());
		bookButton.addActionListener(new BookAction());
		cancelButton.addActionListener(new CancelAction());

		add(new Label("Book room"), new GBC(0, 0).setFill(GridBagConstraints.NONE));
		add(roomList, new GBC(0, 1).setSpan(1, 6));
		add(autobookButton, new GBC(1, 1));
		add(bookButton, new GBC(0, 7));
		add(cancelButton, new GBC(1, 7));

	}

	private class AutoBookAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// autobook room
			getFrame().getRoomBookingWindow().setVisible(false);
		}
	}

	private class BookAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// save booked room
			getFrame().getRoomBookingWindow().setVisible(false);
		}
	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getRoomBookingWindow().setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (RoomBookingModel) model;
		this.model.setView(this);
	}
}
