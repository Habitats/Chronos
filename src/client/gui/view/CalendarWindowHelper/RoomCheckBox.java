package client.gui.view.CalendarWindowHelper;

import java.awt.Color;

import javax.swing.JCheckBox;

import chronos.Room;

public class RoomCheckBox extends JCheckBox {

	private Room room;
	
	public RoomCheckBox(Room room) {
		this(room, false);
	}

	public RoomCheckBox(Room room, boolean isSelected) {
		super(room.getName());
		this.room = room;
		setBackground(Color.white);
		this.setSelected(isSelected);
	}

	public Room getRoom() {
		return room;
	}
}
