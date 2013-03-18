package client.gui.view;

import client.gui.MainFrame;
import client.model.ChronosModel;

public class NotificationWindow extends EventConfigWindow {

	public NotificationWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		remove(addParticipantButton);
		remove(deleteParticipantButton);
		remove(applyButton);
		remove(cancelButton);
		remove(bookRoomButton);
		remove(deleteButton);
		remove(editButton);
		dateField.setEditable(false);
		duration.setEnabled(false);
		eventDescriptionArea.setEditable(false);
		eventNameField.setEditable(false);
		startTime.setEnabled(false);

	}
}
