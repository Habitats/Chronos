package client.gui.view.eventConfig;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowOther extends EventWindow {

	public EventWindowOther(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.OTHER);
		applyButton.setVisible(false);

		eventNameField.setEnabled(false);
		eventDescriptionArea.setEnabled(false);
		startDate.setEnabled(false);
		startTime.setEnabled(false);

		alert.setEnabled(false);
		duration.setEnabled(false);
		startTime.setEnabled(false);
	}
}
