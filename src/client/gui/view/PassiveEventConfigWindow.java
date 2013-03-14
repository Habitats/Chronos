package client.gui.view;

import client.gui.MainFrame;
import client.model.ChronosModel;

public class PassiveEventConfigWindow extends EventConfigWindow {

	public PassiveEventConfigWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		eventNameField.setEditable(false);
		eventDescriptionArea.setEditable(false);
		startTime.setEnabled(false);
		duration.setEnabled(false);
		addParticipantButton.setVisible(false);
		deleteParticipantButton.setVisible(false);
		bookRoomButton.setVisible(false);
		editButton.setVisible(false);
		deleteButton.setVisible(false);
	}

}
