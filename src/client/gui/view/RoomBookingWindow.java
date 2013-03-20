package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import chronos.Room;
import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.RoomBookingModel;

public class RoomBookingWindow extends ChronosWindow {
	private JList<Room> roomList;
	private JButton bookButton, cancelButton, autobookButton;
	private RoomBookingModel model;

	public RoomBookingWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());

		roomList = new JList<Room>();
		roomList.setModel(new DefaultListModel<Room>());
		roomList.setVisible(true);

		bookButton = new JButton("Book");
		cancelButton = new JButton("Cancel");
		autobookButton = new JButton("Autobook");

		// roomList.setPreferredSize(new Dimension(250, 150));
		// roomList.setMinimumSize(new Dimension(100, 80));

		// roomList.setPreferredSize(new Dimension(100, 100));
		// roomList.setMinimumSize(new Dimension(100, 80));
		bookButton.setMinimumSize(new Dimension(100, 20));

		autobookButton.addActionListener(new AutoBookAction());
		bookButton.addActionListener(new BookAction());
		cancelButton.addActionListener(new CancelAction());

		JScrollPane roomListScroll = new JScrollPane(roomList);
		roomListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		roomListScroll.setPreferredSize(new Dimension(150, 100));
		roomListScroll.setMinimumSize(new Dimension(150, 80));

		add(new JLabel("Book room"), new GBC(0, 0).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_START).setInsets(5, 40, 0, 40));
		add(roomListScroll, new GBC(0, 1).setSpan(1, 6).setWeight(1, 1).setInsets(5, 40, 5, 5));
		add(autobookButton, new GBC(1, 1).setInsets(5, 5, 5, 40));
		add(bookButton, new GBC(0, 7).setInsets(5, 40, 5, 5));
		add(cancelButton, new GBC(1, 7).setInsets(5, 5, 5, 40));

	}

	private class AutoBookAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (roomList.getModel().getSize() != 0) {

				roomList.setSelectedValue(model.getBestRoom(), true);

				getFrame().getEventModel().setRoom(roomList.getSelectedValue());
				getFrame().getEventModel().updateRoom();
				getFrame().getRoomBookingWindow().setVisible(false);
			}
		}
	}

	private class BookAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (roomList.getModel().getSize() != 0) {
				getFrame().getEventModel().setRoom(roomList.getSelectedValue());
				getFrame().getEventModel().updateRoom();
				getFrame().getRoomBookingWindow().setVisible(false);
			}
		}
	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getRoomBookingWindow().setVisible(false);
		}
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (RoomBookingModel) model;
		this.model.setView(this);
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (aFlag)
			model.getRooms();
	}

	public Room addRoom(final Room room) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				((DefaultListModel<Room>) (roomList.getModel())).addElement(room);
				roomList.setSelectedIndex(roomList.getLastVisibleIndex());
			}
		});
		return room;
	}

	public JList<Room> getRoomPanel() {
		return roomList;
	}
}
