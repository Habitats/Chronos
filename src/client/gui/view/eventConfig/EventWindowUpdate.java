package client.gui.view.eventConfig;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowUpdate extends EventWindowAdmin {

	public EventWindowUpdate(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.UPDATE);
		applyButton.setText("Update");
	}
}
