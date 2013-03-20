package client.gui.view.eventConfig;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowParticipant extends EventWindow {

	public EventWindowParticipant(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.PARTICIPANT);
		getAlert().setEnabled(true);
		getStartDate().setEnabled(true);
		getStartTime().setEnabled(true);
		getDuration().setEnabled(true);
		getEventNameField().setEnabled(false);
		getEventDescriptionArea().setEnabled(false);
		applyButton.setText("Update");
	}
}
